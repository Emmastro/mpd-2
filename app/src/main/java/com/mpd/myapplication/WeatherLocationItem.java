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
        return data.get("Temperature")
    }
}
