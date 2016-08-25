package go.smart.woaiwhz.smartgo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

/**
 * Created by huazhou.whz on 2016/8/24.
 */
public class ActivityAnimateBuilder<M> {
    private final M mOriginal;
    private final Box<ActivityOptionsCompat> mBox;
    private final Context mContext;

    public ActivityAnimateBuilder(@NonNull Box<ActivityOptionsCompat> box, @NonNull M original, @NonNull Context context){
        mOriginal = original;
        mContext = context;
        mBox = box;
    }

    public M animate(@AnimRes int enterResId, @AnimRes int exitResId){
        final ActivityOptionsCompat compat = ActivityOptionsCompat
                .makeCustomAnimation(mContext,enterResId,exitResId);
        mBox.setItem(compat);

        return mOriginal;
    }

    public M animate(@NonNull View source, int startX, int startY,
                     int startWidth, int startHeight){
        final ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(
                source, startX, startY, startWidth, startHeight);
        mBox.setItem(compat);

        return mOriginal;
    }

    public M animate(@NonNull View sharedElement,@NonNull String sharedElementName){
        if(mContext instanceof Activity) {
            final Activity activity = (Activity) mContext;
            final ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, sharedElement, sharedElementName);
            mBox.setItem(compat);
        }else {
            L.e(mContext + " isn't an Activity.");
        }

        return mOriginal;
    }

    public M animate(@NonNull Pair<View, String>... sharedElements){
        if(mContext instanceof Activity) {
            final Activity activity = (Activity) mContext;
            final ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, sharedElements);
            mBox.setItem(compat);
        }else {
            L.e(mContext + " isn't an Activity.");
        }

        return mOriginal;
    }

    public M animate(@NonNull View source, Bitmap thumbnail, int startX, int startY){
        final ActivityOptionsCompat compat = ActivityOptionsCompat.makeThumbnailScaleUpAnimation(
                source, thumbnail, startX, startY);
        mBox.setItem(compat);

        return mOriginal;
    }

    public M then(){
        return mOriginal;
    }
}
