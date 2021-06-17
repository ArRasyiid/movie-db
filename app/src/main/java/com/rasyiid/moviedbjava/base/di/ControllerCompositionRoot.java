package com.rasyiid.moviedbjava.base.di;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import com.rasyiid.moviedbjava.networking.MovieDbAPI;
import com.rasyiid.moviedbjava.screen.ViewFactory;

public class ControllerCompositionRoot {
    private final ActivityCompositionRoot activityCompositionRoot;

    public ControllerCompositionRoot(ActivityCompositionRoot activityCompositionRoot) {
        this.activityCompositionRoot = activityCompositionRoot;
    }

    private Activity getActivity(){return activityCompositionRoot.getActivity();}

    private Context getContext(){return getActivity();}

    private LayoutInflater getLayoutInflater(){return LayoutInflater.from(getContext());}

    public ViewFactory getViewFactory(){return new ViewFactory(getLayoutInflater());}

    public MovieDbAPI getMovieDbAPI(){return activityCompositionRoot.getMovieDbAPI();}
}
