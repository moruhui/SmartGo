package go.smart.woaiwhz.smartgo.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.view.View;

import go.smart.woaiwhz.smartgo.base.ActivityAnimate;
import go.smart.woaiwhz.smartgo.base.BaseLauncher;
import go.smart.woaiwhz.smartgo.base.Box;
import go.smart.woaiwhz.smartgo.base.SmartGoLog;
import go.smart.woaiwhz.smartgo.builder.SharedAnimatorBuilder;

/**
 * Created by huazhou.whz on 2016/9/3.
 */
public abstract class BaseActivityLauncher extends BaseLauncher {
    protected static final int INIT_REQUEST_CODE = -1;

    protected int mRequestCode = INIT_REQUEST_CODE;
    protected final Context mFrom;

    private final Box<ActivityOptionsCompat> mActivityOptionsBox;

    public BaseActivityLauncher(final Context from){
        super(from);

        mFrom = from;
        mActivityOptionsBox = new Box<>();
    }

    protected <T extends BaseActivityLauncher> T requestCode(final T son, final int requestCode){
        mRequestCode = requestCode;

        return son;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected <T extends BaseActivityLauncher> SharedAnimatorBuilder<T> shareElements(final T son){

        assertIsFromActivity();

        return new SharedAnimatorBuilder<>(mActivityOptionsBox,son,(Activity) mFrom);
    }

    protected <T extends BaseActivityLauncher> T animate(final T son, @AnimRes int enterResId, @AnimRes int exitResId){
        ActivityAnimate.animate(mFrom,mActivityOptionsBox,enterResId,exitResId);

        return son;
    }

    protected <T extends BaseActivityLauncher> T animate(final T son, @NonNull View source, int startX, int startY,
                                                         int startWidth, int startHeight){
        ActivityAnimate.animate(mActivityOptionsBox,source,startX,startY,startWidth,startHeight);

        return son;
    }

    protected <T extends BaseActivityLauncher> T animate(final T son,
                                                         @NonNull View sharedElement, @NonNull String sharedElementName){
        assertIsFromActivity();
        ActivityAnimate.animate((Activity)mFrom,mActivityOptionsBox,sharedElement,sharedElementName);

        return son;
    }

    protected <T extends BaseActivityLauncher> T animate(final T son, @NonNull View source,
                                                         @NonNull Bitmap thumbnail, int startX, int startY){
        ActivityAnimate.animate(mActivityOptionsBox,source,thumbnail,startX,startY);

        return son;
    }

    protected <T extends BaseActivityLauncher> T animate(final T son,
                                                         @NonNull Pair<View, String>... sharedElements){
        assertIsFromActivity();
        ActivityAnimate.animate((Activity)mFrom,mActivityOptionsBox,sharedElements);

        return son;
    }

    protected void assertIsFromActivity(){
        if(!(mFrom instanceof Activity)){
            throw new IllegalArgumentException(mFrom + " isn't an Activity");
        }
    }

    @Override
    public void goReally(final Context context,final Intent intent){
        if(isAvailable(context,intent)) {
            final ActivityOptionsCompat options = mActivityOptionsBox.get();
            Bundle optionsBundle = null;
            if(options != null) {
                optionsBundle = options.toBundle();
            }

            if(context instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) context, intent, mRequestCode, optionsBundle);
            }else {
                ContextCompat.startActivities(context,new Intent[]{intent},optionsBundle);
            }
        }else{
            SmartGoLog.e("have no resolved activity : " + intent);
        }
    }

    private boolean isAvailable(final Context context,final Intent intent){
        final PackageManager manager = context.getPackageManager();

        final ResolveInfo info = manager.resolveActivity(
                intent, PackageManager.MATCH_DEFAULT_ONLY);
        return info != null;
    }
}
