package cn.netrelay.mqttmvp.dagger.module;

import android.content.Context;

import cn.netrelay.mqttmvp.adapter.MyAdapter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by gcy on 2017/6/24.
 */
@Module
public class MyAdapterModule {
    private Context context;
    public MyAdapterModule(Context context) {
        this.context = context;
    }

    @Provides
    public MyAdapter provideMyAdapter(){
        return new MyAdapter(context);
    }
}
