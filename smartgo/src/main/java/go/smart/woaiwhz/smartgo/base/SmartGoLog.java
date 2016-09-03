package go.smart.woaiwhz.smartgo.base;

import android.util.Log;

import go.smart.woaiwhz.smartgo.SmartGo;

/**
 * Created by huazhou.whz on 2016/8/24.
 */
public class SmartGoLog {
    private static final String TAG = SmartGo.TAG + ": LOG";

    public static void e(String msg){
        if(SmartGo.canDebug()){
            Log.e(TAG, msg);
        }
    }
}
