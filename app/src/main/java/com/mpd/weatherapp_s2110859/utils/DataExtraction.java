/*
Name                 Emmanuel Bauma Murairi
Student ID           s2110859
Programme of Study   Computing
*/

package com.mpd.weatherapp_s2110859.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataExtraction {

    public static Map<String, String> extractWeatherDetails(String description) {
        Map<String, String> details = new HashMap<>();

        // Define regular expressions for each detail
        String tempRegex = "Temperature: ([^,]+)";
        String windDirRegex = "Wind Direction: ([^,]+)";
        String windSpeedRegex = "Wind Speed: ([^,]+)";
        String humidityRegex = "Humidity: ([^,]+)";
        String pressureRegex = "Pressure: ([^,]+)";
        String visibilityRegex = "Visibility: ([^,]+)";

        String maxTempRegex = "Maximum Temperature: ([^,]+)";
        String minTempRegex = "Minimum Temperature: ([^,]+)";
        String uvRiskRegex = "UV Risk: ([^,]+)";
        String pollutionRegex = "Pollution: ([^,]+)";
        String sunriseRegex = "Sunrise: ([^,]+)";
        String sunsetRegex = "Sunset: ([^,]+)";

        // Extract and put each detail in the map
        details.put("Temperature", findDetail(tempRegex, description));
        details.put("Wind Direction", findDetail(windDirRegex, description));
        details.put("Wind Speed", findDetail(windSpeedRegex, description));
        details.put("Humidity", findDetail(humidityRegex, description));
        details.put("Pressure", findDetail(pressureRegex, description));
        details.put("Visibility", findDetail(visibilityRegex, description));
        details.put("Maximum Temperature", findDetail(maxTempRegex, description));
        details.put("Minimum Temperature", findDetail(minTempRegex, description));
        details.put("UV Risk", findDetail(uvRiskRegex, description));
        details.put("Pollution", findDetail(pollutionRegex, description));
        details.put("Sunrise", findDetail(sunriseRegex, description));
        details.put("Sunset", findDetail(sunsetRegex, description));

        return details;
    }

    // Helper method to match and extract detail using regex
    private static String findDetail(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "Not available";
    }
}
