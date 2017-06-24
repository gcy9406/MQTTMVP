package cn.netrelay.mqttmvp.module;

import cn.netrelay.mqttmvp.present.Presenter;
import cn.netrelay.mqttmvp.view.IView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by gcy on 2017/6/23.
 */
@Module
public class MyModule {
    private IView view;

    public MyModule(IView view) {
        this.view = view;
    }

    @Provides
    Presenter providePresent(){
        return new Presenter(view);
    }
}
