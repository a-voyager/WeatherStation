package com.swpuiot.ws.entities.response;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/21 17:59
 * Version: 1.0
 */

public class FullInfoResponse {


    /**
     * message : 获取成功！
     * code : 1
     * date : {"id":1,"max_tem":35,"min_tem":24,"mean_tem":29,"max_hum":185,"min_hum":58,"mean_hum":125,"max_ill":9582,"min_ill":54,"mean_ill":7821,"wind_direction":85,"wind_des":"东偏南","wind_speed":27,"description":"气温偏高，适合家里休息","time":"2017-06-21"}
     */

    private String message;
    private int code;
    private DateBean date;

    public String getEssage() {
        return message;
    }

    public void setEssage(String essage) {
        message = essage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DateBean getDate() {
        return date;
    }

    public void setDate(DateBean date) {
        this.date = date;
    }

    public static class DateBean {
        /**
         * id : 1
         * max_tem : 35.0
         * min_tem : 24.0
         * mean_tem : 29.0
         * max_hum : 185.0
         * min_hum : 58.0
         * mean_hum : 125.0
         * max_ill : 9582.0
         * min_ill : 54.0
         * mean_ill : 7821.0
         * wind_direction : 85.0
         * wind_des : 东偏南
         * wind_speed : 27.0
         * description : 气温偏高，适合家里休息
         * time : 2017-06-21
         */

        private int id;
        private double max_tem;
        private double min_tem;
        private double mean_tem;
        private double max_hum;
        private double min_hum;
        private double mean_hum;
        private double max_ill;
        private double min_ill;
        private double mean_ill;
        private double wind_direction;
        private String wind_des;
        private double wind_speed;
        private String description;
        private String time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getAx_tem() {
            return max_tem;
        }

        public void setAx_tem(double ax_tem) {
            max_tem = ax_tem;
        }

        public double getIn_tem() {
            return min_tem;
        }

        public void setIn_tem(double in_tem) {
            min_tem = in_tem;
        }

        public double getEan_tem() {
            return mean_tem;
        }

        public void setEan_tem(double ean_tem) {
            mean_tem = ean_tem;
        }

        public double getAx_hum() {
            return max_hum;
        }

        public void setAx_hum(double ax_hum) {
            max_hum = ax_hum;
        }

        public double getIn_hum() {
            return min_hum;
        }

        public void setIn_hum(double in_hum) {
            min_hum = in_hum;
        }

        public double getEan_hum() {
            return mean_hum;
        }

        public void setEan_hum(double ean_hum) {
            mean_hum = ean_hum;
        }

        public double getAx_ill() {
            return max_ill;
        }

        public void setAx_ill(double ax_ill) {
            max_ill = ax_ill;
        }

        public double getIn_ill() {
            return min_ill;
        }

        public void setIn_ill(double in_ill) {
            min_ill = in_ill;
        }

        public double getEan_ill() {
            return mean_ill;
        }

        public void setEan_ill(double ean_ill) {
            mean_ill = ean_ill;
        }

        public double getWind_direction() {
            return wind_direction;
        }

        public void setWind_direction(double wind_direction) {
            this.wind_direction = wind_direction;
        }

        public String getWind_des() {
            return wind_des;
        }

        public void setWind_des(String wind_des) {
            this.wind_des = wind_des;
        }

        public double getWind_speed() {
            return wind_speed;
        }

        public void setWind_speed(double wind_speed) {
            this.wind_speed = wind_speed;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
