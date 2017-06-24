package cn.netrelay.mqttmvp.component;

import cn.netrelay.mqttmvp.module.SPModule;
import cn.netrelay.mqttmvp.utils.SharedP;
import dagger.Component;

/**
 * Created by gcy on 2017/6/23.
 */
@Component(modules = SPModule.class)
public interface SPComponent {
    SharedP config();
}
