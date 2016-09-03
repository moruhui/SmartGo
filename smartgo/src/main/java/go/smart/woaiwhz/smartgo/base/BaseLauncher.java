package go.smart.woaiwhz.smartgo.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import go.smart.woaiwhz.smartgo.builder.BundleBuilder;

/**
 * Created by huazhou.whz on 2016/9/3.
 */
public abstract class BaseLauncher {
    private final Context mContext;
    private final Box<Bundle> mBundleBox;
    private int mFlag;

    public BaseLauncher(Context from) {
        mBundleBox = new Box<>();

        mContext = from;
    }

    protected  <T extends BaseLauncher> BundleBuilder<T> extras(final T son){
        return new BundleBuilder<>(mBundleBox,son);
    }

    protected  <T extends BaseLauncher> T flag(final T son, final int flag){
        mFlag |= flag;

        return son;
    }

    final public void go(){
        final Intent intent = new Intent();

        preGo(intent);
        goReally(mContext,intent);
    }

    @CallSuper
    protected void preGo(@NonNull final Intent intent){
        intent.setFlags(mFlag);

        final Bundle bundle = mBundleBox.get();
        if(bundle != null){
            intent.putExtras(bundle);
        }
    }

    protected abstract void goReally(final Context context,final Intent intent);
}
