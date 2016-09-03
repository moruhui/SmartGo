package go.smart.woaiwhz.smartgo.base;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.support.annotation.NonNull;

import go.smart.woaiwhz.smartgo.activity.Explicit;
import go.smart.woaiwhz.smartgo.activity.Implicit;
import go.smart.woaiwhz.smartgo.broadcast.BroadcastLauncher;
import go.smart.woaiwhz.smartgo.service.BindService;

/**
 * Created by huazhou.whz on 2016/8/10.
 */
public class Dispatcher {

    private final Context mFrom;

    public Dispatcher(Context from) {
        mFrom = from;
    }

    public Explicit to(@NonNull Class<? extends Activity> to){
        return new Explicit(mFrom,to);
    }

    public Implicit to(@NonNull String action){
        return new Implicit(mFrom,action);
    }

    public BindService run(@NonNull Class<? extends Service> to){
        return new BindService(mFrom,to);
    }

    public BroadcastLauncher send(@NonNull String action){
        return new BroadcastLauncher(mFrom,action);
    }
}
