package go.smart.woaiwhz.smartgo.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.support.annotation.NonNull;

import go.smart.woaiwhz.smartgo.base.Box;
import go.smart.woaiwhz.smartgo.builder.BundleBuilder;
import go.smart.woaiwhz.smartgo.builder.ServiceBindingBuilder;

/**
 * Created by huazhou.whz on 2016/9/3.
 */
public class BindService extends NormalService {
    private int mServiceFlag;
    private final Box<ServiceConnection> mServiceConnectionBox;

    public BindService(@NonNull Context from, @NonNull Class<? extends Service> to) {
        super(from, to);

        mServiceConnectionBox = new Box<>();
    }

    public BindService bind(@NonNull ServiceConnection connection){
        mServiceConnectionBox.set(connection);

        return this;
    }
    public BundleBuilder<BindService> extras(){
        return extras(this);
    }

    public BindService flag(final int flag){
        mServiceFlag = flag;

        return this;
    }

    public ServiceBindingBuilder<BindService> bind(){
        return new ServiceBindingBuilder<>(this,mServiceConnectionBox);
    }

    protected void startService(final Intent intent){
        final ServiceConnection connection = mServiceConnectionBox.get();

        if(connection != null){
            mFrom.bindService(intent,connection,mServiceFlag);
        }else {
            //如果不是就自动降级为普通方式启动 Service
            super.startService(intent);
        }
    }
}
