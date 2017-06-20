package com.swpuiot.ws.entities.response;

import java.util.List;

/**
 * Author: wuhaojie
 * E-mail: w19961009@126.com
 * Date: 2017/06/19 02:23
 * Version: 1.0
 */

public class ForecastResponse {


    /*
    * {
    "HeWeather5": [
        {
            "basic": { //基本信息
                "city": "北京",  //城市名称
                "cnty": "中国",   //国家
                "id": "CN101010100",  //城市ID
                "lat": "39.904000", //城市维度
                "lon": "116.391000", //城市经度
                "prov": "北京",  //城市所属省份（仅限国内城市）
                "update": {  //更新时间
                    "loc": "2016-08-31 11:52",  //当地时间
                    "utc": "2016-08-31 03:52" //UTC时间
                }
            },
            "daily_forecast": [  //7-10天天气预报
                {
                    "astro": {   //天文数值
                        "mr": "04:19",   //月升时间
                        "ms": "18:07",   //月落时间
                        "sr": "05:41",   //日出时间
                        "ss": "18:47"   //日落时间
                    },
                    "cond": {   //天气状况
                        "code_d": "100",   //白天天气状况代码
                        "code_n": "104",  //夜间天气状况代码
                        "txt_d": "晴",   //白天天气状况描述
                        "txt_n": "阴"   //夜间天气状况描述
                    },
                    "date": "2016-08-31",  //预报日期
                    "hum": "17",  //相对湿度（%）
                    "pcpn": "0.0",  //降水量（mm）
                    "pop": "1",  //降水概率
                    "pres": "997",  //气压
                    "tmp": {   //温度
                        "max": "33",   //最高温度
                        "min": "19"   //最低温度
                    },
                    "vis": "10",   //能见度（km）
                    "wind": {   //风力风向
                        "deg": "342",   //风向（360度）
                        "dir": "北风",  //风向
                        "sc": "3-4",   //风力等级
                        "spd": "10"   //风速（kmph）
                    }
                }
            ],
            "status": "ok"   //接口状态
        }
    ]
}

    *
    * */

    private List<HeWeather5Bean> HeWeather5;

    public List<HeWeather5Bean> getHeWeather5() {
        return HeWeather5;
    }

    public void setHeWeather5(List<HeWeather5Bean> heWeather5) {
        HeWeather5 = heWeather5;
    }

    public static class HeWeather5Bean {
        /**
         * basic : {"city":"成都","cnty":"中国","id":"CN101270101","lat":"30.65946198","lon":"104.06573486","update":{"loc":"2017-06-19 01:51","utc":"2017-06-18 17:51"}}
         * daily_forecast : [{"astro":{"mr":"02:07","ms":"14:47","sr":"06:02","ss":"20:08"},"cond":{"code_d":"100","code_n":
         * "101","txt_d":"晴","txt_n":"多云"},"date":"2017-06-19","hum":
         * "81","pcpn":"0.0","pop":"0","pres":"1005","tmp":{"max":"28","min":"19"},"uv":"11","vis":"18","wind":{"deg":"186","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"mr":"02:47","ms":"15:51","sr":"06:02","ss":"20:08"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2017-06-20","hum":"75","pcpn":"0.0","pop":"36","pres":"1003","tmp":{"max":"31","min":"20"},"uv":"12","vis":"19","wind":{"deg":"237","dir":"无持续风向","sc":"微风","spd":"8"}},{"astro":{"mr":"03:30","ms":"16:58","sr":"06:02","ss":"20:09"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2017-06-21","hum":"74","pcpn":"0.1","pop":"19","pres":"1002","tmp":{"max":"30","min":"21"},"uv":"11","vis":"18","wind":{"deg":"178","dir":"无持续风向","sc":"微风","spd":"7"}}]
         * status : ok
         */

        private BasicBean basic;
        private String status;
        private List<DailyForecastBean> daily_forecast;

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

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public static class BasicBean {
            /**
             * city : 成都
             * cnty : 中国
             * id : CN101270101
             * lat : 30.65946198
             * lon : 104.06573486
             * update : {"loc":"2017-06-19 01:51","utc":"2017-06-18 17:51"}
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
                 * loc : 2017-06-19 01:51
                 * utc : 2017-06-18 17:51
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

        public static class DailyForecastBean {
            /**
             * astro : {"mr":"02:07","ms":"14:47","sr":"06:02","ss":"20:08"}
             * cond : {"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"}
             * date : 2017-06-19
             * hum : 81
             * pcpn : 0.0
             * pop : 0
             * pres : 1005
             * tmp : {"max":"28","min":"19"}
             * uv : 11
             * vis : 18
             * wind : {"deg":"186","dir":"无持续风向","sc":"微风","spd":"7"}
             */

            private AstroBean astro;
            private CondBean cond;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            private TmpBean tmp;
            private String uv;
            private String vis;
            private WindBean wind;

            public AstroBean getAstro() {
                return astro;
            }

            public void setAstro(AstroBean astro) {
                this.astro = astro;
            }

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

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
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

            public TmpBean getTmp() {
                return tmp;
            }

            public void setTmp(TmpBean tmp) {
                this.tmp = tmp;
            }

            public String getUv() {
                return uv;
            }

            public void setUv(String uv) {
                this.uv = uv;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class AstroBean {
                /**
                 * mr : 02:07
                 * ms : 14:47
                 * sr : 06:02
                 * ss : 20:08
                 */

                private String mr;
                private String ms;
                private String sr;
                private String ss;

                public String getR() {
                    return mr;
                }

                public void setR(String r) {
                    mr = r;
                }

                public String getS() {
                    return ms;
                }

                public void setS(String s) {
                    ms = s;
                }

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class CondBean {
                /**
                 * code_d : 100
                 * code_n : 101
                 * txt_d : 晴
                 * txt_n : 多云
                 */

                private String code_d;
                private String code_n;
                private String txt_d;
                private String txt_n;

                public String getCode_d() {
                    return code_d;
                }

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public String getCode_n() {
                    return code_n;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getTxt_d() {
                    return txt_d;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }

                public String getTxt_n() {
                    return txt_n;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }
            }

            public static class TmpBean {
                /**
                 * max : 28
                 * min : 19
                 */

                private String max;
                private String min;

                public String getAx() {
                    return max;
                }

                public void setAx(String ax) {
                    max = ax;
                }

                public String getIn() {
                    return min;
                }

                public void setIn(String in) {
                    min = in;
                }
            }

            public static class WindBean {
                /**
                 * deg : 186
                 * dir : 无持续风向
                 * sc : 微风
                 * spd : 7
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

                @Override
                public String toString() {
                    return "WindBean{" +
                            "deg='" + deg + '\'' +
                            ", dir='" + dir + '\'' +
                            ", sc='" + sc + '\'' +
                            ", spd='" + spd + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "DailyForecastBean{" +
                        "astro=" + astro +
                        ", cond=" + cond +
                        ", date='" + date + '\'' +
                        ", hum='" + hum + '\'' +
                        ", pcpn='" + pcpn + '\'' +
                        ", pop='" + pop + '\'' +
                        ", pres='" + pres + '\'' +
                        ", tmp=" + tmp +
                        ", uv='" + uv + '\'' +
                        ", vis='" + vis + '\'' +
                        ", wind=" + wind +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "HeWeather5Bean{" +
                    "basic=" + basic +
                    ", status='" + status + '\'' +
                    ", daily_forecast=" + daily_forecast +
                    '}';
        }

    }


    @Override
    public String toString() {
        return "ForecastResponse{" +
                "HeWeather5=" + HeWeather5 +
                '}';
    }
}
