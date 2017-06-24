package cn.netrelay.mqttmvp.component;

import cn.netrelay.mqttmvp.module.MyModule;
import cn.netrelay.mqttmvp.view.MainActivity;
import dagger.Component;

@Component(dependencies = SPComponent.class,modules = MyModule.class)
public interface MyComponent {
    void inject(MainActivity mainActivity);
}