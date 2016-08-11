package go.smart.woaiwhz.smartgo.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;

/**
 * Created by huazhou.whz on 2016/8/10.
 */
public class Explicit extends ActivityTransmit<Explicit> {
    private final ComponentName mComponent;

    public Explicit(Activity from, Class<? extends Activity> to) {
        super(from);

        mComponent = new ComponentName(from,to);
    }

    public void prepare2Go(){
        final Intent intent = getIntent();

        if(mComponent == null){
            throw new NullPointerException();
        }

        intent.setComponent(mComponent);
    }
}
