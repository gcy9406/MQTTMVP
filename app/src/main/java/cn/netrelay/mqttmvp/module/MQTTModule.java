package cn.netrelay.mqttmvp.module;

import cn.netrelay.mqttmvp.utils.MQTTHelp;
import dagger.Module;
import dagger.Provides;

/**
 * Created by gcy on 2017/6/23.
 */
@Module
public class MQTTModule {
    public MQTTModule() {

    }

    @Provides
    MQTTHelp provideMQTT(){
        return new MQTTHelp();
    }
}
