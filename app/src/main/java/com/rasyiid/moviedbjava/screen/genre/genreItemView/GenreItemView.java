package com.rasyiid.moviedbjava.screen.genre.genreItemView;

import android.view.View;

import com.rasyiid.moviedbjava.model.Genre;

public interface GenreItemView {
    interface Listener{
        void onGenreItemClicked(Genre genre);
    }

    void registerListener(Listener listener);
    void unregisterListener(Listener listener);
    View getRootView();
    void bindGenre(Genre genre);
}
