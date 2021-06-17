package com.rasyiid.moviedbjava.screen.movie.trailerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.rasyiid.moviedbjava.R;
import java.util.ArrayList;
import java.util.List;

public class TrailerViewImpl implements TrailerView {

    private final List<Listener> listeners = new ArrayList<>(1);
    private final View rootView;
    private YouTubePlayerView youTubePlayerView;

    public TrailerViewImpl(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.rootView = layoutInflater.inflate(R.layout.trailer_view, viewGroup, false);

        youTubePlayerView = findViewById(R.id.youtube_player);
        youTubePlayerView.addFullScreenListener(new YouTubePlayerFullScreenListener() {
            @Override
            public void onYouTubePlayerEnterFullScreen() {
                for (Listener listener: listeners) {
                    listener.onYouTubePlayerEnterFullScreen();
                }
                youTubePlayerView.enterFullScreen();
            }

            @Override
            public void onYouTubePlayerExitFullScreen() {
                for (Listener listener: listeners) {
                    listener.onYouTubePlayerExitFullScreen();
                }
                youTubePlayerView.exitFullScreen();
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
    public void setYoutubeId(String key){
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                youTubePlayer.loadVideo(key, 0);
            }
        });
    }
}
