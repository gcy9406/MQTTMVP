package cn.netrelay.mqttmvp.bean;

/**
 * Created by gcy on 2017/6/3.
 */

public class PostInfo {
    private String data;
    private String time;
    private int type;
    public PostInfo() {
    }

    public PostInfo(String data, String time, int type) {
        this.data = data;
        this.time = time;
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PostInfo{" +
                "data='" + data + '\'' +
                ", time='" + time + '\'' +
                ", type=" + type +
                '}';
    }
}
