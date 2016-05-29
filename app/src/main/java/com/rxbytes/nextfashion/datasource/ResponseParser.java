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

                    ShortStory shortStory = new ShortStory();

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
}