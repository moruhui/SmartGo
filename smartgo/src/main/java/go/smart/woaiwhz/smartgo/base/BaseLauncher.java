package go.smart.woaiwhz.smartgo.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import go.smart.woaiwhz.smartgo.builder.BundleBuilder;

/**
 * Created by huazhou.whz on 2016/9/3.
 */
public abstract class BaseLauncher {
    private final Box<Bundle> mBundleBox;

    public BaseLauncher() {
        mBundleBox = new Box<>();
    }

    protected  <T extends BaseLauncher> BundleBuilder<T> extras(final T son){
        return new BundleBuilder<>(mBundleBox,son);
    }

    final public void go(){
        final Intent intent = new Intent();

        preGo(intent);
        goReally(intent);
    }

    @CallSuper
    protected void preGo(@NonNull final Intent intent){
        final Bundle bundle = mBundleBox.get();
        if(bundle != null){
            intent.putExtras(bundle);
        }
    }

    protected abstract void goReally(final Intent intent);
}
