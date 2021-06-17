package com.rasyiid.moviedbjava.screen.movie.movieItemView;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.rasyiid.moviedbjava.R;
import com.rasyiid.moviedbjava.model.Movie;
import java.util.ArrayList;
import java.util.List;

public class MovieItemViewImpl implements MovieItemView {

    private final List<Listener> listeners = new ArrayList<>(1);
    private final View rootView;
    private ImageView poster;
    private TextView title;
    private Movie movie;
    private ProgressBar progressBar;

    public MovieItemViewImpl(LayoutInflater layoutInflater, ViewGroup viewGroup){
        this.rootView = layoutInflater.inflate(R.layout.movie_item, viewGroup, false);
        poster = findViewById(R.id.poster);
        title = findViewById(R.id.title);
        progressBar = findViewById(R.id.progressBar);

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener: listeners) {
                    listener.onMovieItemClicked(movie);
                }
            }
        });
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
    public void bindMovieItem(Movie movie){
        this.movie = movie;
        title.setVisibility(View.VISIBLE);
        title.setText(movie.getOriginal_title());

        poster.setVisibility(View.VISIBLE);
        Glide.with(getRootView().getContext())
                .load("https://image.tmdb.org/t/p/w500"+movie.getPoster_path())
                .centerCrop()
                .apply(
                        new RequestOptions()
                        .placeholder(R.drawable.ic_baseline_image_24)
                        .dontAnimate()
                        .skipMemoryCache(false)
                )
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
                .error(R.drawable.ic_baseline_broken_image_24_black)
                .into(poster);
    }

    private <T extends View>T findViewById(int id){
        return getRootView().findViewById(id);
    }

    @Override
    public View getRootView() {
        return rootView;
    }
}
