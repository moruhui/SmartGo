package go.smart.woaiwhz.smartgo;

import android.util.Log;

/**
 * Created by huazhou.whz on 2016/8/24.
 */
public class L {
    private static final String TAG = "SMARTGO_LOG";

    public static void e(String msg){
        if(SmartGo.canDebug()){
            Log.e(TAG, msg);
        }
    }

}
