package com.rxbytes.nextfashion.app;

import android.app.Application;
import com.rxbytes.nextfashion.state.State;

/**
 * Created by pnagarjuna on 30/05/16.
 */
public class NextFashion extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        State.init(getApplicationContext());
    }
}
