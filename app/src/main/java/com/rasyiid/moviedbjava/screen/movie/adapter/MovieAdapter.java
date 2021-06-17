package com.rasyiid.moviedbjava.screen.movie.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rasyiid.moviedbjava.model.Movie;
import com.rasyiid.moviedbjava.screen.ViewFactory;
import com.rasyiid.moviedbjava.screen.movie.movieItemView.MovieItemView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> implements MovieItemView.Listener {

    public interface Listener{
        void onMovieItemClicked(Movie movie);
    }
    private final ViewFactory viewFactory;
    private final Listener listener;
    private List<Movie> movies = new ArrayList<>();

    public MovieAdapter(ViewFactory viewFactory, Listener listener) {
        this.viewFactory = viewFactory;
        this.listener = listener;
    }

    public void bindMovies(List<Movie> movies){
        /*Movie temp = null;
        for (int i = 0; i < movies.size(); i++) {
            if(i == 3){
                temp = movies.get(i);
                temp.setPoster_path("");
                this.movies.add(temp);
            }else{
                this.movies.add(movies.get(i));
            }
        }*/
        //this.movies = new ArrayList<>(movies);
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemView movieItemView = viewFactory.getMovieItemView(parent);
        movieItemView.registerListener(this);
        return new MyViewHolder(movieItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.movieItemView.bindMovieItem(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public void onMovieItemClicked(Movie movie) {
        listener.onMovieItemClicked(movie);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        private final MovieItemView movieItemView;

        public MyViewHolder(@NonNull MovieItemView movieItemView) {
            super(movieItemView.getRootView());
            this.movieItemView = movieItemView;
        }
    }
}
