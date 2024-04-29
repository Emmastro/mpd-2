/*
Name                 Emmanuel Bauma Murairi
Student ID           s2110859
Programme of Study   Computing
*/

package com.mpd.myapplication;

import java.util.Map;

public class WeatherLocationItem {
    private Map<String, String>  data;
    public int id = 1;

//    public WeatherLocationItem(int id, Map<String, String> data){
//        this.data = data;
//        this.id = id;
//    }
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
}
