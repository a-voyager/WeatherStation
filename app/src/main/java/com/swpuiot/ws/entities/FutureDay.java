package com.swpuiot.ws.entities;

/**
 * Created by 羊荣毅_L on 2017/6/18.
 */
public class FutureDay {
    public int imageName;
    public String weekday;
    public String dayWeather;
    public String dayTemp;

    public FutureDay(int imageName, String weekday, String dayWeather, String dayTemp) {
        this.imageName = imageName;
        this.weekday = weekday;
        this.dayWeather = dayWeather;
        this.dayTemp = dayTemp;
    }

    public int getImageName() {
        return imageName;
    }

    public void setImageName(int imageName) {
        this.imageName = imageName;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getDayWeather() {
        return dayWeather;
    }

    public void setDayWeather(String dayWeather) {
        this.dayWeather = dayWeather;
    }

    public String getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(String dayTemp) {
        this.dayTemp = dayTemp;
    }
}
