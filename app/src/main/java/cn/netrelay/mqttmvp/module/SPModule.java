package cn.netrelay.mqttmvp.module;

import android.content.Context;

import cn.netrelay.mqttmvp.utils.SharedP;
import dagger.Module;
import dagger.Provides;

/**
 * Created by gcy on 2017/6/23.
 */
@Module
public class SPModule {
    private Context context;
    public SPModule(Context context){
        this.context = context;
    }

    @Provides
    public SharedP provideSP(){
        return SharedP.getInstance(context);
    }
}
