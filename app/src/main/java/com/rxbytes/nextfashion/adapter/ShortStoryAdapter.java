package com.rxbytes.nextfashion.adapter;

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
import com.rxbytes.nextfashion.R;
import com.rxbytes.nextfashion.models.ShortStory;
import com.squareup.picasso.Picasso;

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

class ShortStoryViewHolder extends RecyclerView.ViewHolder {
    private View mRootView;
    private ImageView image;
    private TextView title;
    private TextView desc;
    private Button followBtn;

    public ShortStoryViewHolder(View itemView) {
        super(itemView);
        this.mRootView = itemView;
        image = (ImageView) mRootView.findViewById(R.id.image);
        title = (TextView) mRootView.findViewById(R.id.title);
        desc = (TextView) mRootView.findViewById(R.id.desc);
        followBtn = (Button) mRootView.findViewById(R.id.btn);
    }

    public void bind(ShortStory shortStory) {
        if (! TextUtils.isEmpty(shortStory.getImage())) {
            String imageLink = shortStory.getImage();
            Log.e(ShortStoryAdapter.class.getSimpleName(), "loading image link: " + imageLink);
            Picasso.with(mRootView.getContext())
                    .load(imageLink)
                    .fit()
                    .into(image);
        }

        String titleText = shortStory.getTitle() != null ? shortStory.getTitle() + "" : "";
        title.setText(titleText);

        String descText = shortStory.getDesc() != null ? shortStory.getDesc()  + "" : "";
        desc.setText(descText);

        followBtn.setText("Follow");
        followBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
