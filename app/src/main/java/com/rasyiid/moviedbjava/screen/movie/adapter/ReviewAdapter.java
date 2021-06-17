package com.rasyiid.moviedbjava.screen.movie.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rasyiid.moviedbjava.model.Review;
import com.rasyiid.moviedbjava.screen.ViewFactory;
import com.rasyiid.moviedbjava.screen.movie.reviewItemView.ReviewItemView;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> implements ReviewItemView.Listener {

    @Override
    public void onReviewItemClicked(Review review) {
        listener.onReviewItemClicked(review);
    }

    public interface Listener{
        void onReviewItemClicked(Review review);
    }
    private final ViewFactory viewFactory;
    private final Listener listener;
    private List<Review> reviewList = new ArrayList<>();

    public ReviewAdapter(ViewFactory viewFactory, Listener listener) {
        this.viewFactory = viewFactory;
        this.listener = listener;
    }

    public void setReviewList(List<Review> reviewList){
        if(reviewList.size()>0){
            //this.reviewList = new ArrayList<>(reviewList);
            this.reviewList.addAll(reviewList);
        }else{
            Review review = new Review();
            review.setAuthor("No reviews are available");
            review.setUpdated_at("");
            review.setContent("");
            this.reviewList = new ArrayList<>();
            this.reviewList.add(review);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ReviewItemView reviewItemView = viewFactory.getReviewItemView(parent);
        reviewItemView.registerListener(this);
        return new MyViewHolder(reviewItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.reviewItemView.bindReview(reviewList.get(position));
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private final ReviewItemView reviewItemView;
        public MyViewHolder(@NonNull ReviewItemView reviewItemView) {
            super(reviewItemView.getRootView());
            this.reviewItemView = reviewItemView;
        }
    }
}
