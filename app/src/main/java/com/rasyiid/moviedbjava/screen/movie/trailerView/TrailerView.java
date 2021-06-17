package com.rasyiid.moviedbjava.screen.movie.trailerView;

import android.view.View;

import com.rasyiid.moviedbjava.model.Review;
import com.rasyiid.moviedbjava.screen.movie.reviewItemView.ReviewItemView;

public interface TrailerView {
    interface Listener{
        void onYouTubePlayerEnterFullScreen();
        void onYouTubePlayerExitFullScreen();
    }
    void registerListener(Listener listener);
    void unregisterListener(Listener listener);
    View getRootView();
    void setYoutubeId(String key);
}
