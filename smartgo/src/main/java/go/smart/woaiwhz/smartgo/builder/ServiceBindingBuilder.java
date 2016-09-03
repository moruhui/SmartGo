package go.smart.woaiwhz.smartgo.builder;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.NonNull;

import go.smart.woaiwhz.smartgo.base.Box;

/**
 * Created by huazhou.whz on 2016/9/3.
 */
public class ServiceBindingBuilder<M> {
    private final M mExternal;
    private final Box<ServiceConnection> mBox;

    private ConnectedService mConnected;
    private DisconnectedService mDisconnected;

    public ServiceBindingBuilder(@NonNull M external,@NonNull Box<ServiceConnection> box) {
        mExternal = external;
        mBox = box;
    }

    public ServiceBindingBuilder<M> connected(ConnectedService connected){
        mConnected = connected;

        return this;
    }

    public ServiceBindingBuilder<M> disconnected(DisconnectedService disconnected){
        mDisconnected = disconnected;

        return this;
    }

    public M then(){
        mBox.set(new InnerServiceConnection(mConnected,mDisconnected));

        return mExternal;
    }

    private static class InnerServiceConnection implements ServiceConnection {
        private ConnectedService mConnected;
        private DisconnectedService mDisconnected;

        public InnerServiceConnection(ConnectedService connected, DisconnectedService disconnected){
            mConnected = connected;
            mDisconnected = disconnected;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if(mConnected != null){
                mConnected.onServiceConnected(name,service);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            if (mDisconnected != null){
                mDisconnected.onServiceDisconnected(name);
            }

            mConnected = null;
            mDisconnected = null;
        }
    }

    public interface ConnectedService {
        void onServiceConnected(ComponentName name, IBinder service);
    }

    public interface DisconnectedService {
        void onServiceDisconnected(ComponentName name);
    }
}
