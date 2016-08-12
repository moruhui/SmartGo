package go.smart.woaiwhz.smartgo.service;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.ref.WeakReference;

import go.smart.woaiwhz.smartgo.BaseTransmit;
import go.smart.woaiwhz.smartgo.BuildConfig;

/**
 * Created by huazhou.whz on 2016/8/11.
 */
public final class ServiceTransmit extends BaseTransmit<ServiceTransmit> {
    private ComponentName mComponent;
    private ServiceConnection mConnection;
    private int mServiceFlag;

    public ServiceTransmit(@NonNull Activity from,@NonNull Class<? extends Service> to) {
        super(from);

        mComponent = new ComponentName(mFrom,to);
    }

    public ServiceTransmit bind(@NonNull ServiceConnection connection,int flag){
        if(mConnection != null){
            mConnection = connection;
            mServiceFlag = flag;
        }

        return this;
    }

    // TODO: 2016/8/11
    @SuppressWarnings("unchecked")
    public BindingBuilder<ServiceTransmit> bind(){
        return new BindingBuilder(this);
    }

    public static class BindingBuilder<M extends ServiceTransmit>{
        private  int mFlag;
        private ConnectedService mConnected;
        private DisconnectedService mDisconnected;
        private final M external;

        public BindingBuilder(M external) {
            this.external = external;
        }

        public BindingBuilder<M> withFlag(int flag){
            mFlag = flag;

            return this;
        }

        public BindingBuilder<M> whenConnected(ConnectedService connected){
            mConnected = connected;

            return this;
        }

        public BindingBuilder<M> whenDisconnected(DisconnectedService disconnected){
            mDisconnected = disconnected;

            return this;
        }

        public M fine(){
            external.bind(new InnerServiceConnection(mConnected,mDisconnected),mFlag);

            return external;
        }
    }

    public static class InnerServiceConnection implements ServiceConnection{
        private final WeakReference<ConnectedService> mConnected;
        private final WeakReference<DisconnectedService> mDisconnected;

        public InnerServiceConnection(ConnectedService connected, DisconnectedService disconnected){
            mConnected = new WeakReference<>(connected);
            mDisconnected = new WeakReference<>(disconnected);
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            final ConnectedService bind = mConnected.get();
            if(bind != null){
                bind.onServiceConnected(name,service);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            final DisconnectedService bind = mDisconnected.get();
            if (bind != null){
                bind.onServiceDisconnected(name);
            }
        }
    }

    public interface ConnectedService {
        void onServiceConnected(ComponentName name, IBinder service);
    }

    public interface DisconnectedService {
        void onServiceDisconnected(ComponentName name);
    }

    @Override
    public void go() {
        prepare2Go();

        try {
            startService();
        }catch (Exception e){
            if(BuildConfig.DEBUG){
                Log.e(TAG, "unable to start service = " + getIntent().getComponent() +
                        ";error message = " + e.getMessage());
            }
        }
    }

    private void startService(){
        if(mConnection == null){
            mFrom.startService(getIntent());
        }else {
            mFrom.bindService(getIntent(),mConnection,mServiceFlag);
        }
    }

    protected void prepare2Go(){
        getIntent().setComponent(mComponent);
    }

    private void test(){

    }
}
