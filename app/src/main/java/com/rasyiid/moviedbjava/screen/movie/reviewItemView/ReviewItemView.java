package com.rasyiid.moviedbjava.screen.movie.reviewItemView;

import android.view.View;

import com.rasyiid.moviedbjava.model.Review;
import com.rasyiid.moviedbjava.screen.movie.movieItemView.MovieItemView;

public interface ReviewItemView {
    interface Listener{
        void onReviewItemClicked(Review review);
    }
    void registerListener(Listener listener);
    void unregisterListener(Listener listener);
    View getRootView();
    void bindReview(Review review);
}
