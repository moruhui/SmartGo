package go.smart.woaiwhz.smartgo.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.view.View;

import go.smart.woaiwhz.smartgo.builder.BundleBuilder;
import go.smart.woaiwhz.smartgo.builder.SharedAnimatorBuilder;

/**
 * Created by huazhou.whz on 2016/8/23.
 */
public class Explicit extends BaseActivityLauncher {
    private final ComponentName mComponent;

    public Explicit(Context from, Class<? extends Activity> to){
        super(from);

        mComponent = new ComponentName(from,to);
    }

    public Explicit requestCode(int requestCode) {
        return requestCode(this,requestCode);
    }

    public Explicit flag(final int flag){
        return flag(this,flag);
    }

    public Explicit animate(@AnimRes int enterResId, @AnimRes int exitResId){
        return animate(this,enterResId,exitResId);
    }

    public Explicit animate(@NonNull View source, int startX, int startY,
                            int startWidth, int startHeight){
        return animate(this,source,startX,startY,startWidth,startHeight);
    }

    public Explicit animate(@NonNull View sharedElement, @NonNull String sharedElementName){
        return animate(this,sharedElement,sharedElementName);
    }

    public Explicit animate(@NonNull View source,
                            @NonNull Bitmap thumbnail, int startX, int startY){
        return animate(this,source,thumbnail,startX,startY);
    }

    public Explicit animate(@NonNull Pair<View, String>... sharedElements){
        return animate(this,sharedElements);
    }

    public SharedAnimatorBuilder<Explicit> shareElements(){
        return shareElements(this);
    }

    public BundleBuilder<Explicit> extras(){
        return extras(this);
    }

    @Override
    public void preGo(@NonNull Intent intent) {
        intent.setComponent(mComponent);
        super.preGo(intent);
    }
}
