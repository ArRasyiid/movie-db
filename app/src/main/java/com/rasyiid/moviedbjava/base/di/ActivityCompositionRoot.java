package com.rasyiid.moviedbjava.base.di;

import android.app.Activity;

import com.rasyiid.moviedbjava.networking.MovieDbAPI;

import retrofit2.Retrofit;

public class ActivityCompositionRoot {

    private final CompositionRoot compositionRoot;
    private final Activity activity;

    public ActivityCompositionRoot(CompositionRoot compositionRoot, Activity activity) {
        this.compositionRoot = compositionRoot;
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }

    public MovieDbAPI getMovieDbAPI(){return compositionRoot.getMovieDbAPI();}
}
