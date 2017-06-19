package com.swpuiot.ws.entities.response;

import java.util.List;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/19 16:29
 * Version: 1.0
 */

public class HourlyResponse {


    private List<HeWeather5Bean> HeWeather5;

    public List<HeWeather5Bean> getHeWeather5() {
        return HeWeather5;
    }

    public void setHeWeather5(List<HeWeather5Bean> heWeather5) {
        HeWeather5 = heWeather5;
    }

    public static class HeWeather5Bean {
        /**
         * basic : {"city":"成都","cnty":"中国","id":"CN101270101","lat":"30.65946198","lon":"104.06573486","update":{"loc":"2017-06-19 15:51","utc":"2017-06-19 07:51"}}
         * hourly_forecast : [{"cond":{"code":"100","txt":"晴"},"date":"2017-06-19 16:00","hum":"59","pop":"0","pres":"1003","tmp":"27","wind":{"deg":"193","dir":"西南风","sc":"微风","spd":"11"}},{"cond":{"code":"100","txt":"晴"},"date":"2017-06-19 19:00","hum":"68","pop":"0","pres":"1003","tmp":"26","wind":{"deg":"180","dir":"南风","sc":"微风","spd":"9"}},{"cond":{"code":"100","txt":"晴"},"date":"2017-06-19 22:00","hum":"82","pop":"0","pres":"1005","tmp":"23","wind":{"deg":"166","dir":"东南风","sc":"微风","spd":"8"}}]
         * status : ok
         */

        private BasicBean basic;
        private String status;
        private List<HourlyForecastBean> hourly_forecast;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<HourlyForecastBean> getHourly_forecast() {
            return hourly_forecast;
        }

        public void setHourly_forecast(List<HourlyForecastBean> hourly_forecast) {
            this.hourly_forecast = hourly_forecast;
        }

        public static class BasicBean {
            /**
             * city : 成都
             * cnty : 中国
             * id : CN101270101
             * lat : 30.65946198
             * lon : 104.06573486
             * update : {"loc":"2017-06-19 15:51","utc":"2017-06-19 07:51"}
             */

            private String city;
            private String cnty;
            private String id;
            private String lat;
            private String lon;
            private UpdateBean update;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public UpdateBean getUpdate() {
                return update;
            }

            public void setUpdate(UpdateBean update) {
                this.update = update;
            }

            public static class UpdateBean {
                /**
                 * loc : 2017-06-19 15:51
                 * utc : 2017-06-19 07:51
                 */

                private String loc;
                private String utc;

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }
            }
        }

        public static class HourlyForecastBean {
            /**
             * cond : {"code":"100","txt":"晴"}
             * date : 2017-06-19 16:00
             * hum : 59
             * pop : 0
             * pres : 1003
             * tmp : 27
             * wind : {"deg":"193","dir":"西南风","sc":"微风","spd":"11"}
             */

            private CondBean cond;
            private String date;
            private String hum;
            private String pop;
            private String pres;
            private String tmp;
            private WindBean wind;

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class CondBean {
                /**
                 * code : 100
                 * txt : 晴
                 */

                private String code;
                private String txt;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class WindBean {
                /**
                 * deg : 193
                 * dir : 西南风
                 * sc : 微风
                 * spd : 11
                 */

                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
    }
}
