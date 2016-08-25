package go.smart.woaiwhz.smartgo;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.support.annotation.NonNull;

import go.smart.woaiwhz.smartgo.activity.Explicit;
import go.smart.woaiwhz.smartgo.activity.Explicit2;
import go.smart.woaiwhz.smartgo.activity.Implicit;
import go.smart.woaiwhz.smartgo.broadcast.BroadcastTransmit;
import go.smart.woaiwhz.smartgo.service.ServiceTransmit;

/**
 * Created by huazhou.whz on 2016/8/10.
 */
public class Dispatcher {

    private final Context mFrom;

    public Dispatcher(Context from) {
        this.mFrom = from;
    }

    public Explicit2 to(@NonNull Class<? extends Activity> to){
        return new Explicit2(mFrom,to);
    }

    public Implicit to(@NonNull String action){
        return new Implicit(mFrom,action);
    }

    public ServiceTransmit run(@NonNull Class<? extends Service> to){
        return new ServiceTransmit(mFrom,to);
    }

    public BroadcastTransmit send(@NonNull String action){
        return new BroadcastTransmit(mFrom,action);
    }
}
