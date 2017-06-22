package com.swpuiot.ws.entities.response;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/22 09:34
 * Version: 1.0
 */

public class TomorrowForestResponse {


    /**
     * id : 1
     * flag : 100
     * description : 晴
     * maxTemperature : 35
     * minTemperature : 19
     * meanTemperature : 0
     * windForce : 2级
     * windDirection : 东南
     * time : 2017-06-21
     */

    private int id;
    private int flag;
    private String description;
    private int maxTemperature;
    private int minTemperature;
    private int meanTemperature;
    private String windForce;
    private String windDirection;
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAxTemperature() {
        return maxTemperature;
    }

    public void setAxTemperature(int axTemperature) {
        maxTemperature = axTemperature;
    }

    public int getInTemperature() {
        return minTemperature;
    }

    public void setInTemperature(int inTemperature) {
        minTemperature = inTemperature;
    }

    public int getEanTemperature() {
        return meanTemperature;
    }

    public void setEanTemperature(int eanTemperature) {
        meanTemperature = eanTemperature;
    }

    public String getWindForce() {
        return windForce;
    }

    public void setWindForce(String windForce) {
        this.windForce = windForce;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
