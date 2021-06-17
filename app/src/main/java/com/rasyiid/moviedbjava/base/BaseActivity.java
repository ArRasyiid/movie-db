package com.rasyiid.moviedbjava.base;

import androidx.appcompat.app.AppCompatActivity;
import com.rasyiid.moviedbjava.base.di.ActivityCompositionRoot;
import com.rasyiid.moviedbjava.base.di.ControllerCompositionRoot;

public class BaseActivity extends AppCompatActivity {
    private ActivityCompositionRoot activityCompositionRoot;
    private ControllerCompositionRoot controllerCompositionRoot;

    private ActivityCompositionRoot getActivityCompositionRoot(){
        if(activityCompositionRoot == null){
            activityCompositionRoot = new ActivityCompositionRoot(
                    ((CustomApplication)getApplication()).getCompositionRoot(),
                    this
            );
        }
        return activityCompositionRoot;
    }

    protected ControllerCompositionRoot getControllerCompositionRoot(){
        if(controllerCompositionRoot == null){
            controllerCompositionRoot = new ControllerCompositionRoot(getActivityCompositionRoot());
        }
        return controllerCompositionRoot;
    }
}
