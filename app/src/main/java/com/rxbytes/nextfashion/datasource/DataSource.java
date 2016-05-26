package com.rxbytes.nextfashion.datasource;

import android.content.Context;
import android.support.annotation.Nullable;
import com.rxbytes.nextfashion.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by pnagarjuna on 26/05/16.
 */
public class DataSource {

    @Nullable
    public static JSONObject getJson(final Context context) {
        InputStream is = context.getResources().openRawResource(R.raw.data);
        Scanner scan = new Scanner(is);
        String data = "";
        while (scan.hasNext()) {
            String line = scan.nextLine();
            data += line;
        }
        scan.close();
        try {
            JSONObject payload = new JSONObject(data);
            return payload;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
