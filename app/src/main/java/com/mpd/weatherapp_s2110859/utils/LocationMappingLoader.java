/*
Name                 Emmanuel Bauma Murairi
Student ID           s2110859
Programme of Study   Computing
*/

package com.mpd.weatherapp_s2110859.utils;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class LocationMappingLoader {

    public static List<JSONObject> loadJsonFile(Context context, String filename) {
        List<JSONObject> list = new ArrayList<>();
        StringBuilder contentBuilder = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                contentBuilder.append(currentLine).append("\n");
            }
            br.close();

            JSONArray jsonArray = new JSONArray(contentBuilder.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                System.out.println(jsonObject);
                list.add(jsonObject);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
