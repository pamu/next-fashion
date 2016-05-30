package com.rxbytes.nextfashion.datasource;

import android.content.Context;
import com.rxbytes.nextfashion.models.ShortStory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pnagarjuna on 29/05/16.
 */
public class ResponseParser {
    public static List<ShortStory> getShortStories(final Context context) {

        JSONArray data = DataSource.getJson(context);
        List<ShortStory> shortStoryList = new ArrayList<>();
        if (data != null) {
            for (int i = 0; i < data.length(); i++) {
                try {
                    JSONObject item = data.getJSONObject(i);
                    ShortStory shortStory = getShortStory(item);
                    try {
                        shortStoryList.add(shortStory);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return shortStoryList;
    }

    public static ShortStory getShortStory(JSONObject item) {
        ShortStory shortStory = new ShortStory();
        shortStory.setPayload(item.toString());

        try {
            shortStory.setImage(item.getString("si"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            shortStory.setDesc(item.getString("description"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            shortStory.setAuthor(item.getString("username"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            shortStory.setTitle(item.getString("title"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            shortStory.setDb(item.getString("db"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            shortStory.setLikesCount(item.getInt("likes_count"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            shortStory.setCommentsCount(item.getInt("comment_count"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            boolean isNormal = item.has("db");
            shortStory.setAuthorProfile(!isNormal);

            if (!isNormal) {

                try {
                    shortStory.setTitle(item.getString("handle"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    shortStory.setImage(item.getString("image"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return shortStory;
    }
}