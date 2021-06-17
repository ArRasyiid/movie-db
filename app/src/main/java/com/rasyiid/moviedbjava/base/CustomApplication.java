package com.rasyiid.moviedbjava.base;

import android.app.Application;

import com.rasyiid.moviedbjava.base.di.CompositionRoot;

public class CustomApplication extends Application {

    private int page = 1;
    private int reviewPage = 1;
    private CompositionRoot compositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        compositionRoot = new CompositionRoot();
    }

    public int getPage(){return page;}
    public void setPage(int page){this.page = page;}

    public int getReviewPage() {
        return reviewPage;
    }

    public void setReviewPage(int reviewPage) {
        this.reviewPage = reviewPage;
    }

    public CompositionRoot getCompositionRoot() {
        return compositionRoot;
    }
}
