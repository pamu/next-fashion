package com.rxbytes.nextfashion;

import android.os.*;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rxbytes.nextfashion.adapter.ShortStoryAdapter;
import com.rxbytes.nextfashion.datasource.DataSource;
import com.rxbytes.nextfashion.datasource.ResponseParser;
import com.rxbytes.nextfashion.models.ShortStory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ShortStoryAdapter mAdapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ShortStoryAdapter shortStoryAdapter = new ShortStoryAdapter();
        this.mAdapter = shortStoryAdapter;
        recyclerView.setAdapter(shortStoryAdapter);

        new AsyncTask<Void, Void, List<ShortStory>>() {
            @Override
            protected List<ShortStory> doInBackground(Void... voids) {
                List<ShortStory> shortStoryList = ResponseParser.getShortStories(getContext());
                return shortStoryList;
            }

            @Override
            protected void onPostExecute(List<ShortStory> shortStories) {
                super.onPostExecute(shortStories);
                mAdapter.addAll(shortStories);
            }
        }.execute();


        return rootView;
    }
}
