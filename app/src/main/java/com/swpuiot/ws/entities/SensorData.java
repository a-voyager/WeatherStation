package com.swpuiot.ws.entities;

/**
 * Created by wuhaojie on 17-6-21.
 */
public class SensorData {

    private double temperature;

    private double humidity;

    private double windDirectionValue;

    private String windDirectionText;

    private double windSpeedValue;

    private double light;

    public SensorData(double temperature, double humidity, double windDirectionValue, String windDirectionText, double windSpeedValue, double light) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windDirectionValue = windDirectionValue;
        this.windDirectionText = windDirectionText;
        this.windSpeedValue = windSpeedValue;
        this.light = light;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindDirectionValue() {
        return windDirectionValue;
    }

    public String getWindDirectionText() {
        return windDirectionText;
    }

    public double getWindSpeedValue() {
        return windSpeedValue;
    }

    public double getLight() {
        return light;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", windDirectionValue=" + windDirectionValue +
                ", windDirectionText='" + windDirectionText + '\'' +
                ", windSpeedValue=" + windSpeedValue +
                ", light=" + light +
                '}';
    }
}
