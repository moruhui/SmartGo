package go.smart.woaiwhz.smartgo;

import android.app.Activity;
import android.support.annotation.NonNull;

/**
 * Created by huazhou.whz on 2016/8/9.
 */
public class SmartGo {

    private SmartGo(){
        //do nothing
    }

    public static Dispatcher from(@NonNull Activity from){
        return new Dispatcher(from);
    }
}
