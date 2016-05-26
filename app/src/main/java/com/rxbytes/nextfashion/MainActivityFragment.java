package com.rxbytes.nextfashion;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rxbytes.nextfashion.adapter.ShortStoryAdapter;
import com.rxbytes.nextfashion.datasource.DataSource;
import com.rxbytes.nextfashion.models.ShortStory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ShortStoryAdapter mAdapter;

    public MainActivityFragment() {
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
                case DATA_READY :
                    List<ShortStory> list = (List<ShortStory>)msg.obj;
                    mAdapter.addAll(list);
                    break;
                default:
            }
        }
    };

    public static final int DATA_READY = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ShortStoryAdapter shortStoryAdapter = new ShortStoryAdapter();
        this.mAdapter = shortStoryAdapter;
        recyclerView.setAdapter(shortStoryAdapter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONArray data = DataSource.getJson(getContext());
                    List<ShortStory> shortStoryList = new ArrayList<>();
                    if (data != null) {
                        for(int i = 0; i < data.length(); i ++) {
                            try {
                                JSONObject item = data.getJSONObject(i);
                                ShortStory shortStory = new ShortStory();
                                shortStory.setImage(item.getString("image"));
                                shortStory.setDesc(item.getString("about"));
                                shortStory.setAuthor(item.getString("username"));
                                shortStory.setTitle(item.getString("handle"));
                                shortStoryList.add(shortStory);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mHandler.dispatchMessage(Message.obtain(mHandler, DATA_READY, shortStoryList));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();


        return rootView;
    }
}
