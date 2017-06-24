package cn.netrelay.mqttmvp.model;

import cn.netrelay.mqttmvp.utils.SharedP;

/**
 * Created by gcy on 2017/6/23.
 */

public interface IModel {
    interface ICallBack{
        void doAddress(String address);
        void doPort(String prt);
        void doSendTopic(String topic);
        void doSendCmd(String cmd);
        void doRecvTopic(String topic);
        void doShow(boolean show);
    }
    void getShared(SharedP sharedP,ICallBack callBack);
    void mqttConnect(String address,String port);
    void mqttSend(String topic,String cmd);
    void mqttSubscribe(String topic);
    void mqttDisConnect();

    void setShared(String type,String content, SharedP sharedP);
}
