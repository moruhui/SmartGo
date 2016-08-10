package go.smart.woaiwhz.smartgo;

import android.app.Activity;
import android.support.annotation.NonNull;

/**
 * Created by huazhou.whz on 2016/8/10.
 */
public class Dispatcher {

    private final Activity mFrom;

    public Dispatcher(Activity from) {
        this.mFrom = from;
    }

    public Explicit to(@NonNull Class<? extends Activity> to){
        return new Explicit(mFrom,to);
    }
}
