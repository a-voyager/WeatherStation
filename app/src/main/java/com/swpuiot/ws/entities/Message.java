package com.swpuiot.ws.entities;

/**
 * Created by wuhaojie on 17-6-21.
 */
public class Message {

    /**
     * 远程设备
     * 0 表示来自家庭网关
     * 1 表示来自手机终端
     */
    private int origin;

    private SensorData data;

    public Message() {
    }

    public Message(int origin, SensorData data) {
        this.origin = origin;
        this.data = data;
    }

    public int getOrigin() {
        return origin;
    }

    public SensorData getData() {
        return data;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public void setData(SensorData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "origin=" + origin +
                ", data=" + data +
                '}';
    }
}
