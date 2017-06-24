package cn.netrelay.mqttmvp.view;

/**
 * Created by gcy on 2017/6/23.
 */

public interface IView {
    void setAddress(String address);
    void setPort(String port);
    void setSendTopic(String topic);
    void setSendCmd(String cmd);
    void setRecvTopic(String topic);
    void setShowMsg(boolean show);
}
