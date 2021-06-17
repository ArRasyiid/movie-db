package com.rasyiid.moviedbjava.screen;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.rasyiid.moviedbjava.screen.genre.genreItemView.GenreItemView;
import com.rasyiid.moviedbjava.screen.genre.genreItemView.GenreItemViewImpl;
import com.rasyiid.moviedbjava.screen.genre.genreListView.GenreListView;
import com.rasyiid.moviedbjava.screen.genre.genreListView.GenreListViewImpl;
import com.rasyiid.moviedbjava.screen.movie.movieDetailsView.MovieDetailsView;
import com.rasyiid.moviedbjava.screen.movie.movieDetailsView.MovieDetailsViewImpl;
import com.rasyiid.moviedbjava.screen.movie.movieGridView.MovieGridView;
import com.rasyiid.moviedbjava.screen.movie.movieGridView.MovieGridViewImpl;
import com.rasyiid.moviedbjava.screen.movie.movieItemView.MovieItemView;
import com.rasyiid.moviedbjava.screen.movie.movieItemView.MovieItemViewImpl;
import com.rasyiid.moviedbjava.screen.movie.reviewItemView.ReviewItemView;
import com.rasyiid.moviedbjava.screen.movie.reviewItemView.ReviewItemViewImpl;
import com.rasyiid.moviedbjava.screen.movie.trailerView.TrailerView;
import com.rasyiid.moviedbjava.screen.movie.trailerView.TrailerViewImpl;

public class ViewFactory {
    private final LayoutInflater layoutInflater;

    public ViewFactory(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public MovieGridView getMovieGridView(@Nullable ViewGroup parent){
        return new MovieGridViewImpl(layoutInflater, parent, this);
    }

    public MovieItemView getMovieItemView(@Nullable ViewGroup parent){
        return new MovieItemViewImpl(layoutInflater, parent);
    }

    public GenreListView getGenreListView(@Nullable ViewGroup parent){
        return new GenreListViewImpl(layoutInflater, parent, this);
    }

    public GenreItemView getGenreItemView(@Nullable ViewGroup parent){
        return new GenreItemViewImpl(layoutInflater, parent);
    }

    public MovieDetailsView getMovieDetailsView(@Nullable ViewGroup parent){
        return new MovieDetailsViewImpl(layoutInflater, parent, this);
    }

    public ReviewItemView getReviewItemView(@Nullable ViewGroup parent){
        return new ReviewItemViewImpl(layoutInflater, parent);
    }

    public TrailerView getTrailerView(@Nullable ViewGroup parent){
        return new TrailerViewImpl(layoutInflater, parent);
    }
}
