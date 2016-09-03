package go.smart.woaiwhz.smartgo.base;

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
public class ActivityAnimate{

    private ActivityAnimate(){}

    public static void animate(final Context context,final @NonNull Box<ActivityOptionsCompat> box,
                               @AnimRes int enterResId, @AnimRes int exitResId){
        final ActivityOptionsCompat compat = ActivityOptionsCompat
                .makeCustomAnimation(context,enterResId,exitResId);
        box.set(compat);
    }

    public static void animate(final @NonNull Box<ActivityOptionsCompat> box,
                        @NonNull View source, int startX, int startY,
                        int startWidth, int startHeight){
        final ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(
                source, startX, startY, startWidth, startHeight);
        box.set(compat);
    }

    public static void animate(final Activity activity,final @NonNull Box<ActivityOptionsCompat> box,
                        @NonNull View sharedElement,@NonNull String sharedElementName){
            final ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, sharedElement, sharedElementName);
            box.set(compat);
    }

    public static void animate(final @NonNull Box<ActivityOptionsCompat> box,
                               @NonNull View source, Bitmap thumbnail, int startX, int startY){
        final ActivityOptionsCompat compat = ActivityOptionsCompat.makeThumbnailScaleUpAnimation(
                source, thumbnail, startX, startY);
        box.set(compat);
    }

    // TODO: 2016/9/3 看看这里的 lint 提示是什么意思
    public static void animate(final Activity activity,final @NonNull Box<ActivityOptionsCompat> box,
                        @NonNull Pair<View, String>... sharedElements){
        final ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, sharedElements);
        box.set(compat);
    }
}
