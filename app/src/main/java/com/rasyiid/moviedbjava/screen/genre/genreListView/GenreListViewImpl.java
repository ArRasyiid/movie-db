package com.rasyiid.moviedbjava.screen.genre.genreListView;

import android.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import androidx.recyclerview.widget.RecyclerView;
import com.rasyiid.moviedbjava.R;
import com.rasyiid.moviedbjava.model.Genre;
import com.rasyiid.moviedbjava.screen.ViewFactory;
import com.rasyiid.moviedbjava.screen.genre.adapter.GenreAdapter;

import java.util.ArrayList;
import java.util.List;

public class GenreListViewImpl implements GenreListView, GenreAdapter.Listener {

    private final List<Listener> listeners = new ArrayList<>(1);
    private final View rootView;
    private RecyclerView recycler_view_genre;
    private GenreAdapter genreAdapter;
    private ProgressBar progressBar;

    public GenreListViewImpl(LayoutInflater layoutInflater, ViewGroup viewGroup, ViewFactory viewFactory) {
        this.rootView = layoutInflater.inflate(R.layout.genre_list_view, viewGroup, false);

        recycler_view_genre = findViewById(R.id.recycler_view_genre);
        genreAdapter = new GenreAdapter(viewFactory, this);
        recycler_view_genre.setAdapter(genreAdapter);
        progressBar = findViewById(R.id.progressBar);
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
    public void bindGenres(List<Genre> genres) {
        progressBar.setVisibility(View.GONE);
        genreAdapter.bindGenres(genres);
    }

    @Override
    public void onGenreItemClicked(Genre genre) {
        for (Listener listener : listeners) {
            listener.onGenreItemClicked(genre);
        }
    }
}
