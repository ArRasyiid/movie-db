package com.rasyiid.moviedbjava.screen.movie.reviewItemView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rasyiid.moviedbjava.R;
import com.rasyiid.moviedbjava.model.Review;
import java.util.ArrayList;
import java.util.List;

public class ReviewItemViewImpl implements ReviewItemView {

    private final List<Listener> listeners = new ArrayList<>(1);
    private final View rootView;
    private Review review;
    private TextView author, updated_at, content;
    private int count = 0;

    public ReviewItemViewImpl(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.rootView = layoutInflater.inflate(R.layout.review_item, viewGroup, false);

        author = findViewById(R.id.author);
        updated_at = findViewById(R.id.updated_at);
        content = findViewById(R.id.content);

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Listener listener:listeners) {
                    listener.onReviewItemClicked(review);
                }
                count++;
                if(count%2 == 1){
                    content.setMaxLines(1000);
                }else{
                    content.setMaxLines(3);
                }
            }
        });
    }

    private <T extends View>T findViewById(int id){
        return getRootView().findViewById(id);
    }

    @Override
    public void registerListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void bindReview(Review review){
        this.review = review;
        author.setText(review.getAuthor());
        String[] temp = review.getUpdated_at().split("T");
        updated_at.setText(temp[0]);
        content.setText(review.getContent());
    }
}
