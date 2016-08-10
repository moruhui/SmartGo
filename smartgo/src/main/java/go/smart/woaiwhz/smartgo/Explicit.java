package go.smart.woaiwhz.smartgo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;

/**
 * Created by huazhou.whz on 2016/8/10.
 */
public class Explicit extends ActivityTransmit<Explicit> {
    private final ComponentName mComponent;

    public Explicit(Activity from, Class<? extends Activity> to) {
        super(from);

        mComponent = new ComponentName(from,to);
    }

    public void go(){
        final Intent intent = getIntent();

        if(mComponent == null){
            throw new NullPointerException();
        }

        intent.setComponent(mComponent);

//            if(isAvailable()) {
        ActivityCompat.startActivityForResult(mFrom,mIntent,mRequestCode,mOption.toBundle());
//                mFrom.startActivityForResult(intent, mRequestCode);
//            }
    }

//        private boolean isAvailable(){
//            final PackageManager manager = mFrom.getPackageManager();
//
//            return mIntent.resolveActivity(manager) != null;
//        }
}
