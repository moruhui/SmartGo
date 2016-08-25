package go.smart.woaiwhz.smartgo;

import android.content.Context;
import android.content.Intent;

/**
 * Created by huazhou.whz on 2016/8/24.
 */
public class IntentBuilder {

    public static Intent build(Context context,Class<?> clazz){
        return new Intent(context,clazz);
    }
}
