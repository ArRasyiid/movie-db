package com.rasyiid.moviedbjava;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.rasyiid.moviedbjava.base.BaseActivity;
import com.rasyiid.moviedbjava.model.Discover;
import com.rasyiid.moviedbjava.model.Genre;
import com.rasyiid.moviedbjava.model.GenreList;
import com.rasyiid.moviedbjava.screen.genre.genreListView.GenreListView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements GenreListView.Listener {

    private GenreListView genreListView;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        genreListView = getControllerCompositionRoot().getViewFactory().getGenreListView(null);
        setTitle("Genres");
        compositeDisposable.add(getControllerCompositionRoot().getMovieDbAPI().getGenres()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GenreList>() {
                    @Override
                    public void accept(GenreList genreList) throws Exception {
                        bindGenres(genreList.getGenres());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }));
        setContentView(genreListView.getRootView());
    }

    @Override
    protected void onResume() {
        super.onResume();
        genreListView.registerListener(this);
    }

    @Override
    protected void onStop() {
        genreListView.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    private void bindGenres(List<Genre> genres){
        genreListView.bindGenres(genres);
    }

    @Override
    public void onGenreItemClicked(Genre genre) {
        Intent intent = new Intent(MainActivity.this, GridMovieActivity.class);
        intent.putExtra(GridMovieActivity.GENRE, genre);
        startActivity(intent);
    }
}