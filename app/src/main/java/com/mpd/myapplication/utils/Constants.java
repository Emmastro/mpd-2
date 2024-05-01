/*
Name                 Emmanuel Bauma Murairi
Student ID           s2110859
Programme of Study   Computing
*/

package com.mpd.myapplication.utils;
import com.mpd.myapplication.R;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public Map<String, Integer> weatherMap = new HashMap<>();

    public Constants(){
        weatherMap.put("Light Rain", R.drawable.rain);
        weatherMap.put("Sunny Intervals", R.drawable.day_partial_cloud);
        weatherMap.put("Light Cloud", R.drawable.cloudy);
        weatherMap.put("Partly Cloudy", R.drawable.day_partial_cloud);

        weatherMap.put("Clear Night", R.drawable.night_clear);
        weatherMap.put("Partly Cloudy Night", R.drawable.night_partial_cloud);
        weatherMap.put("Heavy Rain", R.drawable.rain_thunder);
        weatherMap.put("Snow", R.drawable.snow);
        weatherMap.put("Heavy Snow", R.drawable.snow_thunder);
        weatherMap.put("Sleet", R.drawable.sleet);
        weatherMap.put("Fog", R.drawable.fog);
        weatherMap.put("Overcast", R.drawable.overcast);
        weatherMap.put("Thunderstorms", R.drawable.thunder);
        weatherMap.put("Tornado", R.drawable.tornado);
//        weatherMap.put("Mist", R.drawable.mist);
        weatherMap.put("Sunny", R.drawable.day_clear);


    }

}
