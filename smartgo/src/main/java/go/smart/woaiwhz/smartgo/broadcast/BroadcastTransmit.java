package go.smart.woaiwhz.smartgo.broadcast;

import android.app.Activity;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import go.smart.woaiwhz.smartgo.BaseTransmit;
import go.smart.woaiwhz.smartgo.BuildConfig;

/**
 * Created by huazhou.whz on 2016/8/12.
 */
public class BroadcastTransmit extends BaseTransmit<BroadcastTransmit> {
    private static final int NORMAL = 1;
    private static final int ORDER = 1 << 1;

    @IntDef({NORMAL,ORDER})
    @Retention(RetentionPolicy.SOURCE)
    private @interface BroadcastType{}

    private final String mAction;
    private String mOrderPermission;

    @BroadcastType
    private int mType = NORMAL;

    public BroadcastTransmit(@NonNull Activity from,@NonNull String action) {
        super(from);
        mAction = action;
    }

    public BroadcastTransmit order(@NonNull String orderPermission){
        mType = ORDER;
        mOrderPermission = orderPermission;

        return me;
    }

    @Override
    public void go() {
        prepare2Go();

        try {
            sendBroadcast();
        }catch (Exception e){
            if(BuildConfig.DEBUG){
                Log.e(TAG, "unable to sent broadcast = " + getIntent().getComponent() +
                ";error message = " + e.getMessage());
            }
        }
    }

    private void sendBroadcast(){
        switch (mType){
            case NORMAL:
                mFrom.sendBroadcast(getIntent());
                break;

            case ORDER:
                mFrom.sendOrderedBroadcast(getIntent(),mOrderPermission);
                break;

            default:
                break;
        }
    }

    private void prepare2Go(){
        getIntent().setAction(mAction);
    }
}
