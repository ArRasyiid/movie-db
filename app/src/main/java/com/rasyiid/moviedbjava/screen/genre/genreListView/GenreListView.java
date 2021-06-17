package com.rasyiid.moviedbjava.screen.genre.genreListView;

import android.app.ActionBar;
import android.view.View;
import com.rasyiid.moviedbjava.model.Genre;
import java.util.List;

public interface GenreListView {
    interface Listener{
        void onGenreItemClicked(Genre genre);
    }

    void registerListener(Listener listener);
    void unregisterListener(Listener listener);
    View getRootView();
    void bindGenres(List<Genre> genres);
}
