package com.rasyiid.moviedbjava;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.rasyiid.moviedbjava.base.BaseActivity;
import com.rasyiid.moviedbjava.base.CustomApplication;
import com.rasyiid.moviedbjava.model.Discover;
import com.rasyiid.moviedbjava.model.Genre;
import com.rasyiid.moviedbjava.model.Movie;
import com.rasyiid.moviedbjava.screen.movie.movieGridView.MovieGridView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GridMovieActivity extends BaseActivity implements MovieGridView.Listener {

    public static final String GENRE = "GENRE";
    private MovieGridView movieGridView;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Genre genre;
    private int page = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieGridView = getControllerCompositionRoot().getViewFactory().getMovieGridView(null);
        genre = getIntent().getParcelableExtra(GENRE);
        ((CustomApplication)getApplication()).setPage(page);
        setTitle(genre.getName());
        fetchMovies();
        setContentView(movieGridView.getRootView());
    }

    @Override
    protected void onResume() {
        super.onResume();
        page = ((CustomApplication)getApplication()).getPage();
        movieGridView.registerListener(this);
    }

    @Override
    protected void onStop() {
        ((CustomApplication)getApplication()).setPage(page);
        movieGridView.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    private void bindDiscoveredMovies(Discover discover, boolean cannotScroll){
        movieGridView.bindDiscoveredMovies(discover, cannotScroll);
    }

    @Override
    public void onMovieItemClicked(Movie movie) {
        Intent intent = new Intent(GridMovieActivity.this, MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsActivity.MOVIE_ID, movie.getId());
        intent.putExtra(MovieDetailsActivity.MOVIE_TITLE, movie.getTitle());
        startActivity(intent);
    }

    @Override
    public void fetchMovies() {
        compositeDisposable.add(getControllerCompositionRoot().getMovieDbAPI().getMovies(genre.getId(), page)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Discover>() {
                    @Override
                    public void accept(Discover discover) throws Exception {
                        if(page<discover.getTotal_pages()){
                            bindDiscoveredMovies(discover, false);
                            page++;
                        }else{
                            bindDiscoveredMovies(discover, true);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(GridMovieActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }));
    }
}
