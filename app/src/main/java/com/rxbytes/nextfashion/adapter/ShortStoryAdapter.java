package com.rxbytes.nextfashion.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import com.rxbytes.nextfashion.models.ShortStory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pnagarjuna on 26/05/16.
 */
public class ShortStoryAdapter extends RecyclerView.Adapter<ShortStoryViewHolder> {

    private List<ShortStory> shortStoryList = new ArrayList<>();

    public ShortStoryAdapter() {}

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
        return null;
    }

    @Override
    public void onBindViewHolder(ShortStoryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}

class ShortStoryViewHolder extends RecyclerView.ViewHolder {
    private View mRootView;

    public ShortStoryViewHolder(View itemView) {
        super(itemView);
        this.mRootView = itemView;
    }


}
