package com.rxbytes.nextfashion.state;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by pnagarjuna on 30/05/16.
 */
public class Prefs {
    private final Context mContext;
    private SharedPreferences mPrefs;

    public Prefs(final Context context) {
        this.mContext = context;
        mPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public void setFollowingState(String id, boolean flag) {
        mPrefs.edit().putBoolean(id, flag).commit();
    }

    public boolean getFollowState(String id) {
        return mPrefs.getBoolean(id, false);
    }

    public void clearPrefs() {
        mPrefs.edit().clear().commit();
    }

}
