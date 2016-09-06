package go.smart.woaiwhz.smartgo.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.support.annotation.NonNull;

import go.smart.woaiwhz.smartgo.base.BaseLauncher;
import go.smart.woaiwhz.smartgo.base.Box;
import go.smart.woaiwhz.smartgo.base.SmartGoLog;
import go.smart.woaiwhz.smartgo.builder.BundleBuilder;

/**
 * Created by huazhou.whz on 2016/8/11.
 */
public class ServiceLauncher extends BaseLauncher {
    private final ComponentName mComponent;
    private int mFlag;
    private final Box<ServiceConnection> mServiceConnectionBox;

    public ServiceLauncher(@NonNull Context from, @NonNull Class<? extends Service> to) {
        super(from);

        mComponent = new ComponentName(from,to);
        mServiceConnectionBox = new Box<>();
    }

    public ServiceLauncher flag(final int flag){
        return flag(this,flag);
    }

    @Override
    protected void preGo(@NonNull Intent intent) {
        intent.setComponent(mComponent);

        super.preGo(intent);
    }

    @Override
    public void goReally(final Context context,final Intent intent) {
        try {
            startService(context,intent);
        }catch (Exception e){
                SmartGoLog.e("unable to start service = " + intent +
                        ";error message = " + e.getMessage());
        }
    }

    public ServiceLauncher bind(@NonNull ServiceConnection connection,final int flag){
        mServiceConnectionBox.set(connection);
        mFlag = flag;

        return this;
    }

    public BundleBuilder<ServiceLauncher> extras(){
        return extras(this);
    }

    protected void startService(final Context context,final Intent intent){
        final ServiceConnection connection = mServiceConnectionBox.get();

        if(connection != null){
            context.bindService(intent,connection, mFlag);
        }else {
            context.startService(intent);
        }
    }
}
