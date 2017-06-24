package cn.netrelay.mqttmvp.component;

import cn.netrelay.mqttmvp.model.Model;
import cn.netrelay.mqttmvp.module.MQTTModule;
import dagger.Component;

/**
 * Created by gcy on 2017/6/23.
 */
@Component(modules = MQTTModule.class)
public interface MQTTComponent {
    void inject(Model model);
}
