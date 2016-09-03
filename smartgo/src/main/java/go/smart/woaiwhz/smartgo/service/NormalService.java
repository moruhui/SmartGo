package go.smart.woaiwhz.smartgo.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import go.smart.woaiwhz.smartgo.base.BaseLauncher;
import go.smart.woaiwhz.smartgo.base.SmartGoLog;

/**
 * Created by huazhou.whz on 2016/8/11.
 */
public abstract class NormalService extends BaseLauncher {
    private final ComponentName mComponent;
    protected final Context mFrom;

    public NormalService(@NonNull Context from, @NonNull Class<? extends Service> to) {
        super();

        mFrom = from;
        mComponent = new ComponentName(mFrom,to);
    }

    public void goReally(@NonNull Intent intent) {
        intent.setComponent(mComponent);

        try {
            startService(intent);
        }catch (Exception e){
                SmartGoLog.e("unable to start service = " + intent +
                        ";error message = " + e.getMessage());
        }
    }

    protected void startService(final Intent intent){
        mFrom.startService(intent);
    }
}
