package cn.netrelay.mqttmvp.present;

import cn.netrelay.mqttmvp.model.IModel;
import cn.netrelay.mqttmvp.model.Model;
import cn.netrelay.mqttmvp.utils.SharedP;
import cn.netrelay.mqttmvp.view.IView;

/**
 * Created by gcy on 2017/6/23.
 */
public class Presenter implements IPresenter{
    private IView view;
    private IModel model;
    public Presenter(IView view){
        this.view = view;
        model = new Model();
    }
    @Override
    public void getShared(SharedP sharedP) {
        model.getShared(sharedP,new IModel.ICallBack() {
            @Override
            public void doAddress(String address) {
                view.setAddress(address);
            }

            @Override
            public void doPort(String prt) {
                view.setPort(prt);
            }

            @Override
            public void doSendTopic(String topic) {
                view.setSendTopic(topic);
            }

            @Override
            public void doSendCmd(String cmd) {
                view.setSendCmd(cmd);
            }

            @Override
            public void doRecvTopic(String topic) {
                view.setRecvTopic(topic);
            }

            @Override
            public void doShow(boolean show) {
                view.setShowMsg(show);
            }
        });
    }

    @Override
    public void mqttConnect(String address, String port) {
        model.mqttConnect(address,port);
    }

    @Override
    public void mqttSend(String topic, String cmd) {
        model.mqttSend(topic,cmd);
    }

    @Override
    public void mqttSubscribe(String topic) {
        model.mqttSubscribe(topic);
    }

    @Override
    public void mqttDisconnect() {
        model.mqttDisConnect();
    }

    @Override
    public void setShared(String type,String content,  SharedP sharedP) {
        model.setShared(type,content,sharedP);
    }
}
