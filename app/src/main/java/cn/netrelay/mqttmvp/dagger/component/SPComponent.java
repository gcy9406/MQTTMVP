package cn.netrelay.mqttmvp.dagger.component;

import cn.netrelay.mqttmvp.dagger.module.SPModule;
import cn.netrelay.mqttmvp.utils.SharedP;
import dagger.Component;

/**
 * Created by gcy on 2017/6/23.
 */
@Component(modules = SPModule.class)
public interface SPComponent {
    SharedP config();
}
