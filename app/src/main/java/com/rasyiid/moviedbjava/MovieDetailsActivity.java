package com.rasyiid.moviedbjava;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import com.rasyiid.moviedbjava.base.BaseActivity;
import com.rasyiid.moviedbjava.base.CustomApplication;
import com.rasyiid.moviedbjava.model.Discover;
import com.rasyiid.moviedbjava.model.Genre;
import com.rasyiid.moviedbjava.model.Movie;
import com.rasyiid.moviedbjava.model.MovieDetails;
import com.rasyiid.moviedbjava.model.Review;
import com.rasyiid.moviedbjava.model.Reviews;
import com.rasyiid.moviedbjava.model.Video;
import com.rasyiid.moviedbjava.model.Videos;
import com.rasyiid.moviedbjava.screen.movie.movieDetailsView.MovieDetailsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailsActivity extends BaseActivity implements MovieDetailsView.Listener {

    public static final String MOVIE_ID = "MOVIE_ID";
    public static final String MOVIE_TITLE = "MOVIE_TITLE";
    private MovieDetailsView movieDetailsView;
    private int movieId;
    private String movieTitle;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Disposable disposableMovieDetails, disposableReviews, disposableVideos;
    private int reviewPage = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieDetailsView = getControllerCompositionRoot().getViewFactory().getMovieDetailsView(null);
        movieId = getIntent().getIntExtra(MOVIE_ID, -1);
        setTitle("NoTitle");
        movieTitle = getIntent().getStringExtra(MOVIE_TITLE);
        setTitle(movieTitle);
        ((CustomApplication)getApplication()).setReviewPage(reviewPage);
        disposableMovieDetails = getControllerCompositionRoot().getMovieDbAPI().getMovieDetails(movieId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieDetails>() {
                    @Override
                    public void accept(MovieDetails movieDetails) throws Exception {
                        bindMovieDetails(movieDetails);
                    }
                },new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MovieDetailsActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(disposableMovieDetails);

        fetchReviews();

        disposableVideos = getControllerCompositionRoot().getMovieDbAPI().getMovieVideos(movieId)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Videos>() {
                    @Override
                    public void accept(Videos videos) throws Exception {
                        bindMovieVideos(videos);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MovieDetailsActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(disposableVideos);
        setContentView(movieDetailsView.getRootView());
    }

    private void bindMovieDetails(MovieDetails movieDetails){
        movieDetailsView.bindMovieDetails(movieDetails);
    }

    private void bindMovieReviews(Reviews reviews, boolean cannotScroll){
        movieDetailsView.bindMovieReviews(reviews, cannotScroll);
    }

    private void bindMovieVideos(Videos videos){
        movieDetailsView.bindMovieVideos(videos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reviewPage = ((CustomApplication)getApplication()).getReviewPage();
        movieDetailsView.registerListener(this);
    }

    @Override
    protected void onStop() {
        movieDetailsView.unregisterListener(this);
        ((CustomApplication)getApplication()).setReviewPage(reviewPage);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void onReviewItemClicked(Review review) {
        //Toast.makeText(MovieDetailsActivity.this, review.getAuthor(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTrailerButtonClicked(Video video, String title) {
        Intent intent = new Intent(MovieDetailsActivity.this, TrailerActivity.class);
        intent.putExtra(TrailerActivity.KEY, video.getKey());
        intent.putExtra(TrailerActivity.TITLE, title);
        startActivity(intent);
    }

    @Override
    public void fetchReviews() {
        disposableReviews = getControllerCompositionRoot().getMovieDbAPI().getMovieReviews(movieId, reviewPage)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Reviews>() {
                    @Override
                    public void accept(Reviews reviews) throws Exception {
                        if(reviewPage<reviews.getTotal_pages()){
                            bindMovieReviews(reviews, false);
                            reviewPage++;
                        }else{
                            bindMovieReviews(reviews, true);
                        }
                    }
                },new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MovieDetailsActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(disposableReviews);
    }
}
