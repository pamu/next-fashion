package com.rxbytes.nextfashion.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.rxbytes.nextfashion.R;
import com.rxbytes.nextfashion.fullstory.FullStoryActivity;
import com.rxbytes.nextfashion.models.ShortStory;
import com.rxbytes.nextfashion.state.State;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pnagarjuna on 26/05/16.
 */
public class ShortStoryAdapter extends RecyclerView.Adapter<ShortStoryViewHolder> {

    private List<ShortStory> shortStoryList = new ArrayList<>();

    public ShortStoryAdapter() {
    }

    public ShortStoryAdapter(List<ShortStory> shortStories) {
        shortStoryList.addAll(shortStories);
    }

    public void clear() {
        shortStoryList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<ShortStory> shortStories) {
        shortStoryList.addAll(shortStories);
        notifyDataSetChanged();
    }

    @Override
    public ShortStoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.short_story_layout_item, parent, false);
        ShortStoryViewHolder shortStoryViewHolder = new ShortStoryViewHolder(rootView);
        return shortStoryViewHolder;
    }

    @Override
    public void onBindViewHolder(ShortStoryViewHolder holder, int position) {
        holder.bind(shortStoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return shortStoryList.size();
    }

}