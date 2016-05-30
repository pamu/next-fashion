package com.rxbytes.nextfashion.state;

import android.content.Context;

/**
 * Created by pnagarjuna on 30/05/16.
 */
public class State {
    private static Prefs mPrefs;

    public static void init(final Context context) {
        if (mPrefs == null) {
            mPrefs = new Prefs(context);
        }
    }

    public static Prefs getPrefs() {
        return mPrefs;
    }

}
