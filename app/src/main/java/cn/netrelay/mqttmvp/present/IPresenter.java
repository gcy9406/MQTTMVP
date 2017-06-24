package cn.netrelay.mqttmvp.present;

import cn.netrelay.mqttmvp.utils.SharedP;

/**
 * Created by gcy on 2017/6/23.
 */

public interface IPresenter {
    void getShared(SharedP sharedP);
    void mqttConnect(String address,String port);
    void mqttSend(String topic,String cmd);
    void mqttSubscribe(String topic);
    void mqttDisconnect();
    void setShared(String type,String content, SharedP sharedP);
}
