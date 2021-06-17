package com.rasyiid.moviedbjava.base.di;

import android.view.LayoutInflater;

import com.rasyiid.moviedbjava.base.Constants;
import com.rasyiid.moviedbjava.networking.MovieDbAPI;
import com.rasyiid.moviedbjava.screen.ViewFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompositionRoot {

    private Retrofit retrofit;

    public CompositionRoot(){ }

    private Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public MovieDbAPI getMovieDbAPI(){return getRetrofit().create(MovieDbAPI.class);}
}
