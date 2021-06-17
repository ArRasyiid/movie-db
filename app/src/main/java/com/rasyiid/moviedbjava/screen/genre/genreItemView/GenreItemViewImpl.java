package com.rasyiid.moviedbjava.screen.genre.genreItemView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rasyiid.moviedbjava.R;
import com.rasyiid.moviedbjava.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class GenreItemViewImpl implements GenreItemView{

    private final List<Listener> listeners = new ArrayList<>(1);
    private final View rootView;
    private TextView genre_item_name;
    private Genre genre;

    public GenreItemViewImpl(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.rootView = layoutInflater.inflate(R.layout.genre_item, viewGroup, false);

        genre_item_name = findViewById(R.id.genre_item_name);

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Listener listener : listeners){
                    listener.onGenreItemClicked(genre);
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
    public void bindGenre(Genre genre){
        this.genre = genre;
        genre_item_name.setText(this.genre.getName());
    }
}
