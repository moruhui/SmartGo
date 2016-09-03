package go.smart.woaiwhz.smartgo.broadcast;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import go.smart.woaiwhz.smartgo.base.BaseLauncher;
import go.smart.woaiwhz.smartgo.base.SmartGoLog;
import go.smart.woaiwhz.smartgo.builder.BundleBuilder;

/**
 * Created by huazhou.whz on 2016/8/12.
 */
public class BroadcastLauncher extends BaseLauncher {

    private final String mAction;
    private String mPermission;
    private boolean mIsOrder;

    public BroadcastLauncher(@NonNull Context from, @NonNull String action) {
        super(from);

        mAction = action;
        mIsOrder = false;
    }

    public BroadcastLauncher order(){
        mIsOrder = true;

        return this;
    }

    public BroadcastLauncher permission(@NonNull String receiverPermission){
        mPermission = receiverPermission;

        return this;
    }

    public BundleBuilder<BroadcastLauncher> extras(){
        return extras(this);
    }

    public BroadcastLauncher flag(final int flag){
        return flag(this,flag);
    }

    @Override
    public void goReally(final Context context,final Intent intent) {
        intent.setAction(mAction);

        try {
            sendBroadcast(context,intent);
        }catch (Exception e){
            SmartGoLog.e("unable to sent broadcast = " + intent.getComponent() +
            ";error message = " + e.getMessage());
        }
    }

    private void sendBroadcast(final Context context,final Intent intent){
        if(mIsOrder){
            context.sendOrderedBroadcast(intent,mPermission);
        }else {
            if(TextUtils.isEmpty(mPermission)){
                context.sendBroadcast(intent);
            }else {
                context.sendBroadcast(intent,mPermission);
            }
        }
    }
}
