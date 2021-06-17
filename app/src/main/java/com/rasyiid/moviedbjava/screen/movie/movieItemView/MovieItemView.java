package com.rasyiid.moviedbjava.screen.movie.movieItemView;

import android.view.View;

import com.rasyiid.moviedbjava.model.Genre;
import com.rasyiid.moviedbjava.model.Movie;
import com.rasyiid.moviedbjava.screen.genre.genreItemView.GenreItemView;

public interface MovieItemView {
    interface Listener{
        void onMovieItemClicked(Movie movie);
    }

    void registerListener(Listener listener);
    void unregisterListener(Listener listener);
    void bindMovieItem(Movie movie);
    View getRootView();
}
