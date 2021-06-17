package com.rasyiid.moviedbjava.screen.movie.movieDetailsView;

import android.view.View;
import com.rasyiid.moviedbjava.model.MovieDetails;
import com.rasyiid.moviedbjava.model.Review;
import com.rasyiid.moviedbjava.model.Reviews;
import com.rasyiid.moviedbjava.model.Video;
import com.rasyiid.moviedbjava.model.Videos;
import com.rasyiid.moviedbjava.screen.movie.reviewItemView.ReviewItemView;

public interface MovieDetailsView {
    interface Listener{
        void onReviewItemClicked(Review review);
        void onTrailerButtonClicked(Video video, String title);
        void fetchReviews();
    }
    void registerListener(Listener listener);
    void unregisterListener(Listener listener);
    View getRootView();
    void bindMovieDetails(MovieDetails movieDetails);
    void bindMovieReviews(Reviews reviews, boolean cannotScroll);
    void bindMovieVideos(Videos videos);
}
