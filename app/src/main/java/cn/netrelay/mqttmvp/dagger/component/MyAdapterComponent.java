package cn.netrelay.mqttmvp.dagger.component;

import cn.netrelay.mqttmvp.adapter.MyAdapter;
import cn.netrelay.mqttmvp.dagger.module.MyAdapterModule;
import dagger.Component;

/**
 * Created by gcy on 2017/6/24.
 */
@Component(modules = MyAdapterModule.class)
public interface MyAdapterComponent {
    MyAdapter configMyAdapter();
}
