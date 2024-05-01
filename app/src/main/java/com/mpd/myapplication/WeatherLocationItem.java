/*
Name                 Emmanuel Bauma Murairi
Student ID           s2110859
Programme of Study   Computing
*/

package com.mpd.myapplication;

import java.util.Map;

public class WeatherLocationItem {
    private Map<String, String>  data;
    public Integer id;
    public String urlId;
    public String day = "today";
    public String description;
    public double longitude;
    public double latitude;
    public void setDetails( Map<String, String> data) {
        this.data = data;

    }

    public Map<String, String> getData() {
        return data;
    }

    public String getTemperature(){
        return data.get("Temperature");
    }

    public String getPressure(){
        return data.get("Pressure");
    }

    public String getLocation(){
        return data.get("location");
    }
    public void setLocation(String location){

        this.data.put("location", location);
    }

    public String getId() {
        return Integer.toString(this.id);
    }

    public void setUrlId(String id){
        this.urlId = id;
    }

    public String getUrlId(){
        return urlId;
    }


    public String getTemperatureMaximum() {
        return data.get("Maximum Temperature");
    }

    public String getTemperatureMinimum(){
        return data.get("Minimum Temperature");
    }

    public String getWindDirection() {
        return data.get("Wind Direction");
    }

    public String getWindSpeed() {
        return data.get("Wind Speed");
    }

    public String getHumidity() {
        return data.get("Humidity");
    }

    public String getVisibility() {
        return data.get("Visibility");
    }

    public String getUVRisk() {
        return data.get("UV Risk");
    }

    public String getPollution() {
        return data.get("Pollution");
    }

    public String getSunrise() {
        return data.get("Sunrise");
    }

    public String getSunset() {
        return data.get("Sunset");
    }

    public void setLongitude(String longitude) {
        this.longitude = Double.parseDouble(longitude);
    }

    public void setLatitude(String latitude) {
        this.latitude = Double.parseDouble(latitude);
    }

    /*
    * Returns all data points in a formated manner, with each in a row  with end of line at the end*/
    public String getFormatedAll(){
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            builder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return builder.toString();
    }
}
