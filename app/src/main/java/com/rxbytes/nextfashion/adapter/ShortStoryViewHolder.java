package com.rxbytes.nextfashion.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.rxbytes.nextfashion.R;
import com.rxbytes.nextfashion.fullstory.FullStoryActivity;
import com.rxbytes.nextfashion.models.ShortStory;
import com.rxbytes.nextfashion.state.State;
import com.squareup.picasso.Picasso;

public class ShortStoryViewHolder extends RecyclerView.ViewHolder {
    private View mRootView;
    private ImageView image;
    private TextView title;
    private TextView desc;
    private Button followBtn;

    public static final String FOLLOW_KEY = "FOLLOW_STRING";
    public static final String DB_KEY = "DB_KEY";
    public static final String UPDATE_VIEW_ACTION = "UPDATE_VIEW_ACTION";

    private static final String TAG = ShortStoryViewHolder.class.getSimpleName();

    public ShortStoryViewHolder(View itemView) {
        super(itemView);
        this.mRootView = itemView;
        image = (ImageView) mRootView.findViewById(R.id.image);
        title = (TextView) mRootView.findViewById(R.id.title);
        desc = (TextView) mRootView.findViewById(R.id.desc);
        followBtn = (Button) mRootView.findViewById(R.id.btn);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (!TextUtils.isEmpty(action) && action.equals(UPDATE_VIEW_ACTION)) {
                    Bundle extras = intent.getExtras();
                    if (extras != null && extras.containsKey(FOLLOW_KEY) && extras.containsKey(DB_KEY)) {
                        Log.e(TAG, FOLLOW_KEY + " => " + extras.getString(FOLLOW_KEY));
                        String tag = (String) followBtn.getTag();
                        if (!TextUtils.isEmpty(tag)) {
                            if (tag.equals(extras.getString(DB_KEY))) {
                                followBtn.setText(extras.getString(FOLLOW_KEY));
                            }
                        }
                    }
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter(UPDATE_VIEW_ACTION);
        mRootView.getContext().registerReceiver(receiver, intentFilter);
    }

    public void bind(final ShortStory shortStory) {
        if (!TextUtils.isEmpty(shortStory.getImage())) {
            String imageLink = shortStory.getImage();
            Log.e(ShortStoryAdapter.class.getSimpleName(), "loading image link: " + imageLink);
            Picasso.with(mRootView.getContext())
                    .load(imageLink)
                    .fit()
                    .into(image);
        }


        String titleText = shortStory.getTitle() != null ? shortStory.getTitle().trim() + "" : "";
        title.setVisibility(View.VISIBLE);
        title.setText(titleText);
        if (TextUtils.isEmpty(titleText)) {
            title.setVisibility(View.GONE);
        }

        String descText = shortStory.getDesc() != null ? shortStory.getDesc().trim() + "" : "";
        desc.setVisibility(View.VISIBLE);
        desc.setText(descText);
        if (TextUtils.isEmpty(descText)) {
            desc.setVisibility(View.GONE);
        }

        if (!shortStory.isAuthorProfile()) {
            followBtn.setTag(shortStory.getDb());
            followBtn.setVisibility(View.VISIBLE);
            if (State.getPrefs().getFollowState(shortStory.getDb())) {
                followBtn.setText("following");
            } else {
                followBtn.setText("follow");
            }
            followBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String str;
                    if (followBtn.getText().equals("follow")) {
                        State.getPrefs().setFollowingState(shortStory.getDb(), true);
                        followBtn.setText("following");
                        str = "following";
                    } else {
                        State.getPrefs().setFollowingState(shortStory.getDb(), false);
                        followBtn.setText("follow");
                        str = "follow";
                    }

                    Intent intent = new Intent(UPDATE_VIEW_ACTION);
                    intent.putExtra(FOLLOW_KEY, str);
                    intent.putExtra(DB_KEY, shortStory.getDb());
                    mRootView.getContext().sendBroadcast(intent);
                }
            });
        }

        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mRootView.getContext(), "opening full story", Toast.LENGTH_SHORT).show();
                final Context context = view.getContext();
                Intent fullStoryIntent = new Intent(context, FullStoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("payload", shortStory.getPayload());
                fullStoryIntent.putExtras(bundle);
                context.startActivity(fullStoryIntent);
            }
        });


        if (shortStory.isAuthorProfile()) {
            mRootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mRootView.getContext(), "Nothing to open", Toast.LENGTH_SHORT).show();
                }
            });
            String text = shortStory.getTitle() != null ? shortStory.getTitle() + "" : "";
            title.setText(text);
            desc.setVisibility(View.GONE);
            followBtn.setVisibility(View.GONE);
        }

    }
}
