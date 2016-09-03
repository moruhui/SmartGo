package go.smart.woaiwhz.smartgo;

import android.content.Context;
import android.support.annotation.NonNull;

import go.smart.woaiwhz.smartgo.base.Dispatcher;

/**
 * Created by huazhou.whz on 2016/8/9.
 */
public class SmartGo {
    public static final String TAG = SmartGo.class.getSimpleName();
    public static boolean DEBUG = true;

    private SmartGo(){
        //do nothing
    }

    public static boolean canDebug(){
        return DEBUG;
    }

    public static void enableDebug(){
        DEBUG = true;
    }

    public static Dispatcher from(@NonNull Context from){
        return new Dispatcher(from);
    }
}
