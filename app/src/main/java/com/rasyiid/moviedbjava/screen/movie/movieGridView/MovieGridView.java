package com.rasyiid.moviedbjava.screen.movie.movieGridView;

import android.view.View;

import com.rasyiid.moviedbjava.model.Discover;
import com.rasyiid.moviedbjava.model.Movie;
import com.rasyiid.moviedbjava.screen.movie.movieItemView.MovieItemView;

public interface MovieGridView {
    interface Listener{
        void onMovieItemClicked(Movie movie);
        void fetchMovies();
    }
    void registerListener(Listener listener);
    void unregisterListener(Listener listener);
    View getRootView();
    void bindDiscoveredMovies(Discover discover, boolean canScroll);
}
