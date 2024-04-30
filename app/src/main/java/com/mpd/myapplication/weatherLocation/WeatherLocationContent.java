package com.mpd.myapplication.weatherLocation;

import android.content.Context;
import android.os.AsyncTask;

import com.mpd.myapplication.WeatherLocationAdapter;
import com.mpd.myapplication.WeatherLocationItem;
import com.mpd.myapplication.utils.DataExtraction;
import com.mpd.myapplication.utils.LocationMappingLoader;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherLocationContent {

    private final WeatherLocationAdapter adapter;

    public WeatherLocationContent(WeatherLocationAdapter adapter){
        this.adapter = adapter;

    }
    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public class FetchWeatherTask extends AsyncTask<String, Void, List<WeatherLocationItem>> {
        @Override
        protected List<WeatherLocationItem> doInBackground(String... params) {
            List<WeatherLocationItem> items = new ArrayList<>();
            try {
                URL url = new URL("https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/" + params[0]);
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(url.openConnection().getInputStream(), null);

                int eventType = parser.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG && "item".equals(parser.getName())) {
                        WeatherLocationItem weatherLocationItem = new WeatherLocationItem();
                        while (!(eventType == XmlPullParser.END_TAG && "item".equals(parser.getName()))) {

                            if ("description".equals(parser.getName())) {
                                String description = parser.nextText();
                                weatherLocationItem.setDetails(DataExtraction.extractWeatherDetails(description));
                                weatherLocationItem.setUrlId(params[0]);
                                weatherLocationItem.setLocation(params[1]);
                                break;
                            }
                            eventType = parser.next();
                        }
                        items.add(weatherLocationItem);
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return items;
        }

        @Override
        protected void onPostExecute(List<WeatherLocationItem> result) {
            super.onPostExecute(result);
            if (!result.isEmpty()) {
                adapter.mValues.addAll(result);
                adapter.notifyDataSetChanged();
            }
    }}

    public void loadWeatherData(Context context) throws JSONException {

        List<JSONObject> locationIds = LocationMappingLoader.loadJsonFile(context, "locations.json");
        adapter.mValues.clear();
        for (JSONObject id : locationIds) {
            new FetchWeatherTask().execute(
                    (String) id.get("locationId"),
                    (String) id.get("location"));
        }
    }
}

