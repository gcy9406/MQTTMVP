package cn.netrelay.mqttmvp.utils;

import android.util.Log;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.Listener;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;
import org.greenrobot.eventbus.EventBus;

import java.net.URISyntaxException;

/**
 * Created by gcy on 2017/6/5.
 */

public class MQTTHelp {

    private static final String TAG = "@@@";
    private MQTT mqtt;
    private CallbackConnection connection;

    public MQTTHelp() {
        mqtt = new MQTT();
    }

    public void connect(String address, String port) {
        try {
            mqtt.setHost("tcp://"+address+":"+port);//设置地址
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "connect: ================");
        connection = mqtt.callbackConnection();

        if (connection != null) {
            //开始链接
            connection.connect(new Callback<Void>() {
                @Override
                public void onSuccess(Void value) {

                }

                @Override
                public void onFailure(Throwable value) {

                }
            });
            //订阅
            connection.listener(new Listener() {
                @Override
                public void onConnected() {

                }

                @Override
                public void onDisconnected() {

                }

                /**
                 * 得到订阅的结果，发送出去
                 */
                @Override
                public void onPublish(UTF8Buffer topic, Buffer body, Runnable ack) {
                    Log.d(TAG, "onPublish: "+body.utf8().toString());
                    EventBus.getDefault().post(body.utf8().toString());
                }

                @Override
                public void onFailure(Throwable value) {

                }
            });
        }
    }

    public void publish(String topic,String cmd) {
        if (connection != null) {
            connection.publish(topic, cmd.getBytes(), QoS.AT_MOST_ONCE, false, new Callback<Void>() {
                @Override
                public void onSuccess(Void value) {

                }

                @Override
                public void onFailure(Throwable value) {

                }
            });
        }
    }

    public void subscription(String topic) {
        if (connection != null) {
            Topic[] topics = {new Topic(topic, QoS.AT_MOST_ONCE)};
            connection.subscribe(topics, new Callback<byte[]>() {
                @Override
                public void onSuccess(byte[] value) {

                }

                @Override
                public void onFailure(Throwable value) {

                }
            });
        }
    }

    public void unsubscribe(String topic) {
        UTF8Buffer topicU = new UTF8Buffer(topic);
        UTF8Buffer[] tArr = new UTF8Buffer[1];
        tArr[0] = topicU;
        connection.unsubscribe(tArr, new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {

            }

            @Override
            public void onFailure(Throwable value) {

            }
        });
    }

    public void disconnect() {
        connection.disconnect(new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {

            }

            @Override
            public void onFailure(Throwable value) {

            }
        });
    }
}
