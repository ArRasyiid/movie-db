package com.rasyiid.moviedbjava;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rasyiid.moviedbjava.base.BaseActivity;
import com.rasyiid.moviedbjava.screen.movie.trailerView.TrailerView;

public class TrailerActivity extends BaseActivity implements TrailerView.Listener {

    public static final String KEY = "KEY";
    public static final String TITLE = "TITLE";
    private String key, title;
    private TrailerView trailerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        key = getIntent().getStringExtra(KEY);
        title = getIntent().getStringExtra(TITLE);
        setTitle(title);
        trailerView = getControllerCompositionRoot().getViewFactory().getTrailerView(null);
        setContentView(trailerView.getRootView());
    }

    @Override
    protected void onResume() {
        super.onResume();
        trailerView.setYoutubeId(key);
        trailerView.registerListener(this);
    }

    @Override
    protected void onStop() {
        trailerView.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onYouTubePlayerEnterFullScreen() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public void onYouTubePlayerExitFullScreen() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            hideSystemUI();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            showSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}
