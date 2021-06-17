package com.rasyiid.moviedbjava.screen.movie.movieGridView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rasyiid.moviedbjava.R;
import com.rasyiid.moviedbjava.model.Discover;
import com.rasyiid.moviedbjava.model.Movie;
import com.rasyiid.moviedbjava.screen.movie.adapter.MovieAdapter;
import com.rasyiid.moviedbjava.screen.ViewFactory;

import java.util.ArrayList;
import java.util.List;

public class MovieGridViewImpl implements MovieGridView, MovieAdapter.Listener {

    private final View rootView;
    private final ViewFactory viewFactory;
    private final List<Listener> listeners = new ArrayList<>(1);
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private boolean cannotScroll = false;
    private int pastVisibleItems, visibleItemCount, totalItemCount;
    private GridLayoutManager gridLayoutManager;

    public MovieGridViewImpl(LayoutInflater layoutInflater, ViewGroup parent, ViewFactory viewFactory){
        this.rootView = layoutInflater.inflate(R.layout.activity_main, parent, false);
        this.viewFactory = viewFactory;
        recyclerView = findViewById(R.id.recycler_view_movie_list);
        //recyclerView.setLayoutManager(new GridLayoutManager(getRootView().getContext(), 2));
        movieAdapter = new MovieAdapter(this.viewFactory, this);
        recyclerView.setAdapter(movieAdapter);
        gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = gridLayoutManager.getChildCount();
                    totalItemCount = gridLayoutManager.getItemCount();
                    pastVisibleItems = gridLayoutManager.findFirstVisibleItemPosition();

                    if (!cannotScroll) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            cannotScroll = true;
                            for (Listener listener: listeners) {
                                listener.fetchMovies();
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
    public void bindDiscoveredMovies(Discover discover, boolean cannotScroll) {
        this.cannotScroll = cannotScroll;
        movieAdapter.bindMovies(discover.getResults());
    }

    @Override
    public void onMovieItemClicked(Movie movie) {
        for (Listener listener: listeners) {
            listener.onMovieItemClicked(movie);
        }
    }
}
