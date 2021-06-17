package com.rasyiid.moviedbjava.screen.movie.movieDetailsView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.rasyiid.moviedbjava.MovieDetailsActivity;
import com.rasyiid.moviedbjava.R;
import com.rasyiid.moviedbjava.TrailerActivity;
import com.rasyiid.moviedbjava.model.Genre;
import com.rasyiid.moviedbjava.model.MovieDetails;
import com.rasyiid.moviedbjava.model.Review;
import com.rasyiid.moviedbjava.model.Reviews;
import com.rasyiid.moviedbjava.model.Video;
import com.rasyiid.moviedbjava.model.Videos;
import com.rasyiid.moviedbjava.screen.ViewFactory;
import com.rasyiid.moviedbjava.screen.movie.adapter.ReviewAdapter;
import com.rasyiid.moviedbjava.screen.movie.movieGridView.MovieGridView;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsViewImpl implements MovieDetailsView, ReviewAdapter.Listener {

    private final List<Listener> listeners = new ArrayList<>(1);
    private final View rootView;
    private ImageView imageView;
    private TextView tvTitle;
    private TextView tvGenres;
    private TextView tvOverview;
    private TextView textView2;
    private TextView tvDate;
    private ReviewAdapter reviewAdapter;
    private RecyclerView recycler_view_review;
    private Button trailer;
    private Video video;
    private MovieDetails movieDetails;
    private boolean cannotScroll = false;
    private int pastVisibleItems, visibleItemCount, totalItemCount;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;
    private LinearLayout top, bottom;
    private TextView errorResult;

    public MovieDetailsViewImpl(LayoutInflater layoutInflater, ViewGroup viewGroup, ViewFactory viewFactory) {
        this.rootView = layoutInflater.inflate(R.layout.movie_details, viewGroup, false);

        progressBar = findViewById(R.id.pict_progressBar);
        imageView = findViewById(R.id.iv_poster2);
        tvTitle = findViewById(R.id.tv_title);
        tvGenres = findViewById(R.id.tv_genres);
        tvOverview = findViewById(R.id.tv_overview);
        textView2 = findViewById(R.id.textView2);
        tvDate = findViewById(R.id.tv_date);
        recycler_view_review = findViewById(R.id.recycler_view_review);
        trailer = findViewById(R.id.trailer);
        top = findViewById(R.id.top);
        bottom = findViewById(R.id.bottom_layout);
        bottom.setVisibility(View.INVISIBLE);

        reviewAdapter = new ReviewAdapter(viewFactory, this);
        recycler_view_review.setAdapter(reviewAdapter);
        linearLayoutManager = (LinearLayoutManager)recycler_view_review.getLayoutManager();

        trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener:listeners) {
                    listener.onTrailerButtonClicked(video, movieDetails.getTitle());
                }
            }
        });

        recycler_view_review.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (!cannotScroll) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            cannotScroll = true;
                            for (Listener listener: listeners) {
                                listener.fetchReviews();
                            }
                        }
                    }
                }
            }
        });
    }

    private <T extends View>T findViewById(int id){
        return getRootView().findViewById(id);
    }

    @Override
    public void registerListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void bindMovieDetails(MovieDetails movieDetails) {
        this.movieDetails = movieDetails;
        showInformation(movieDetails.getTitle(),
                movieDetails.getRelease_date(),
                movieDetails.getOverview(),
                movieDetails.getGenres(),
                movieDetails.getPoster_path());
    }

    @Override
    public void bindMovieReviews(Reviews reviews, boolean cannotScroll) {
        this.cannotScroll = cannotScroll;
        reviewAdapter.setReviewList(reviews.getResults());
    }

    @Override
    public void bindMovieVideos(Videos videos) {
        if(videos.getResults().size() > 0){
            for (int i = 0; i < videos.getResults().size(); i++) {
                if(videos.getResults().get(i).getType().equals("Trailer")){
                    video = videos.getResults().get(i);
                    if(video.getKey()!=null && !video.getKey().equals("") && this.movieDetails != null){
                        trailer.setEnabled(true);
                    }else{
                        trailer.setEnabled(false);
                    }
                }
            }
        }
    }

    private void showInformation(String title,
                                 String date,
                                 String overview,
                                 ArrayList<Genre> genres,
                                 String posterPath){
        bottom.setVisibility(View.VISIBLE);
        String imageUri = "https://image.tmdb.org/t/p/w500"+posterPath;
        Glide.with(getRootView().getContext()).load(imageUri.trim()).centerCrop()
                .error(R.drawable.ic_baseline_broken_image_24_black)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imageView);
        tvTitle.setText(title);
        tvDate.setText(date);
        tvOverview.setText(overview);
        tvOverview.setContentDescription(overview);
        tvOverview.setMovementMethod(new ScrollingMovementMethod());

        String myGenres = "";
        for(int index=0;index<genres.size();index++){
            myGenres = myGenres+" "+genres.get(index).getName();
        }
        tvGenres.setText(myGenres.trim());

        textView2.setText(getRootView().getContext().getText(R.string.overview));
    }

    @Override
    public void onReviewItemClicked(Review review) {
        for (Listener listener:listeners) {
            listener.onReviewItemClicked(review);
        }
    }
}
