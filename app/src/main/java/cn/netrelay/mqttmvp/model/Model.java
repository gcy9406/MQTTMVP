package cn.netrelay.mqttmvp.model;

import javax.inject.Inject;

import cn.netrelay.mqttmvp.component.DaggerMQTTComponent;
import cn.netrelay.mqttmvp.module.MQTTModule;
import cn.netrelay.mqttmvp.utils.MQTTHelp;
import cn.netrelay.mqttmvp.utils.SharedP;

/**
 * Created by gcy on 2017/6/23.
 */

public class Model implements IModel{
    @Inject
    MQTTHelp mqttHelp;
    public Model() {
        DaggerMQTTComponent.builder()
                .mQTTModule(new MQTTModule())
                .build()
                .inject(this);
    }

    @Override
    public void getShared(SharedP sharedP,ICallBack callBack) {
        callBack.doAddress(sharedP.getString("mqtt_address","182.61.18.191"));
        callBack.doPort(sharedP.getString("mqtt_port","1883"));
        callBack.doSendTopic(sharedP.getString("mqtt_send","0150982661293172state"));
        callBack.doSendCmd(sharedP.getString("mqtt_cmd","setr=3xxxxxxxxx"));
        callBack.doRecvTopic(sharedP.getString("mqtt_recv","0150982661293172ctr"));
        callBack.doShow(sharedP.getBoolean("mqtt_show",true));
    }

    @Override
    public void mqttConnect(String address, String port) {
        mqttHelp.connect(address,port);
    }

    @Override
    public void mqttSend(String topic, String cmd) {
        mqttHelp.publish(topic,cmd);
    }

    @Override
    public void mqttSubscribe(String topic) {
        mqttHelp.subscription(topic);
    }

    @Override
    public void mqttDisConnect() {
        mqttHelp.disconnect();
    }

    @Override
    public void setShared(String type,String content, SharedP sharedP) {
        if (!type.equals("mqtt_show")){
            sharedP.putString(type,content);
        }else {
            sharedP.putBoolean(type,content.equals("show"));
        }
    }
}
