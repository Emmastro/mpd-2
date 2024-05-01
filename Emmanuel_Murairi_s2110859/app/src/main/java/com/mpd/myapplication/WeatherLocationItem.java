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

}
