package cn.netrelay.mqttmvp.dagger.component;

import cn.netrelay.mqttmvp.dagger.module.MainModule;
import cn.netrelay.mqttmvp.view.MainActivity;
import dagger.Component;

@Component(dependencies = {SPComponent.class,MyAdapterComponent.class},modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}