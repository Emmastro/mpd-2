package com.mpd.myapplication.weatherLocation;


import com.mpd.myapplication.WeatherLocationItem;
import com.mpd.myapplication.utils.DataExtraction;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherLocationContentForcast {

    public static final Map<String, WeatherLocationItem> WEATHER_DETAILS = new HashMap<>();

    public WeatherLocationContentForcast(){
    }

    public List<WeatherLocationItem> fetchWeatherData(String locationId) {
        List<WeatherLocationItem> items = new ArrayList<>();
        try {
            URL url = new URL("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/" + locationId);
            InputStream inputStream = url.openConnection().getInputStream();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(inputStream, null);

            int eventType = parser.getEventType();
            WeatherLocationItem weatherLocationItem = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String tagName = parser.getName();
                        if ("item".equals(tagName)) {
                            weatherLocationItem = new WeatherLocationItem();
                        } else if (weatherLocationItem != null) {
                            if ("title".equals(tagName)) {
                                String title = parser.nextText();
                                weatherLocationItem.day = title.split(":", 2)[0];
                                weatherLocationItem.description = title.split(":", 2)[1].split(",", 2)[0];
                            } else if ("description".equals(tagName)) {
                                String description = parser.nextText();
                                weatherLocationItem.setDetails(DataExtraction.extractWeatherDetails(description));
                                weatherLocationItem.setLocation("Location");
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("item".equals(parser.getName()) && weatherLocationItem != null) {
                            items.add(weatherLocationItem);
                            weatherLocationItem = null; // Reset for the next item
                        }
                        break;
                }
                eventType = parser.next();
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }
}

