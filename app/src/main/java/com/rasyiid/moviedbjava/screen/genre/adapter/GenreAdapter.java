package com.rasyiid.moviedbjava.screen.genre.adapter;

import android.text.style.AlignmentSpan;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rasyiid.moviedbjava.model.Genre;
import com.rasyiid.moviedbjava.screen.ViewFactory;
import com.rasyiid.moviedbjava.screen.genre.genreItemView.GenreItemView;
import java.util.ArrayList;
import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.MyViewHolder> implements GenreItemView.Listener {

    @Override
    public void onGenreItemClicked(Genre genre) {
        listener.onGenreItemClicked(genre);
    }

    public interface Listener{
        void onGenreItemClicked(Genre genre);
    }

    private final ViewFactory viewFactory;
    private final Listener listener;
    private List<Genre> genres = new ArrayList<>();

    public GenreAdapter (ViewFactory viewFactory, Listener listener){
        this.viewFactory = viewFactory;
        this.listener = listener;
    }

    public void bindGenres(List<Genre> genres){
        this.genres = new ArrayList<>(genres);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GenreItemView genreItemView = viewFactory.getGenreItemView(parent);
        genreItemView.registerListener(this);
        return new MyViewHolder(genreItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.genreItemView.bindGenre(genres.get(position));
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        private final GenreItemView genreItemView;

        public MyViewHolder(@NonNull GenreItemView genreItemView) {
            super(genreItemView.getRootView());
            this.genreItemView = genreItemView;
        }
    }
}
