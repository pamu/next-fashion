package com.rxbytes.nextfashion.fullstory;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.rxbytes.nextfashion.R;
import com.rxbytes.nextfashion.adapter.ShortStoryAdapter;
import com.rxbytes.nextfashion.datasource.ResponseParser;
import com.rxbytes.nextfashion.models.ShortStory;
import com.rxbytes.nextfashion.state.State;
import com.squareup.picasso.Picasso;
import org.json.JSONObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class FullStoryActivityFragment extends Fragment {

    public FullStoryActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_full_story, container, false);

        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null && extras.containsKey("payload")) {
            try {
                String payload = extras.getString("payload");
                JSONObject item = new JSONObject(payload);
                final ShortStory shortStory = ResponseParser.getShortStory(item);
                ImageView image = (ImageView) mRootView.findViewById(R.id.image);
                TextView title = (TextView) mRootView.findViewById(R.id.title);
                TextView desc = (TextView) mRootView.findViewById(R.id.desc);
                final Button followBtn = (Button) mRootView.findViewById(R.id.btn);

                followBtn.setVisibility(View.VISIBLE);
                if (State.getPrefs().getFollowState(shortStory.getDb())) {
                    followBtn.setText("following");
                } else {
                    followBtn.setText("follow");
                }

                TextView comments = (TextView) mRootView.findViewById(R.id.comments);
                comments.setText(shortStory.getCommentsCount() + " comments");
                TextView likes = (TextView) mRootView.findViewById(R.id.likes);
                likes.setText(shortStory.getLikesCount() + " likes");

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

                followBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (followBtn.getText().equals("follow")) {
                            State.getPrefs().setFollowingState(shortStory.getDb(), true);
                            followBtn.setText("following");
                        } else {
                            State.getPrefs().setFollowingState(shortStory.getDb(), false);
                            followBtn.setText("follow");
                        }
                    }
                });
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else getActivity().finish();


        return mRootView;
    }
}
