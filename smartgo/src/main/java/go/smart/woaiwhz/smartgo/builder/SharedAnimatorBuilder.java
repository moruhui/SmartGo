package go.smart.woaiwhz.smartgo.builder;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import go.smart.woaiwhz.smartgo.base.ActivityAnimate;
import go.smart.woaiwhz.smartgo.base.Box;

/**
 * Created by huazhou.whz on 2016/8/24.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class SharedAnimatorBuilder<M> {
    private final Box<ActivityOptionsCompat> mBox;
    private final List<Pair> mCollection;
    private final M mOriginal;
    private final Activity mActivity;

    public SharedAnimatorBuilder(@NonNull Box<ActivityOptionsCompat> box, @NonNull M original, @NonNull Activity activity){
        mBox = box;
        mCollection = new ArrayList<>();
        mActivity = activity;
        mOriginal = original;
    }

    public SharedAnimatorBuilder<M> like(@NonNull View view){
        like(view,view.getTransitionName());

        return this;
    }

    public SharedAnimatorBuilder<M> like(@NonNull View view, @NonNull String transition){
        mCollection.add(Pair.create(view,transition));

        return this;
    }

    public SharedAnimatorBuilder<M> append(@NonNull Pair<View,String>... pairs){
        mCollection.addAll(Arrays.asList(pairs));

        return this;
    }

    public M withSystemUI(){
        return withSystemUI(true);
    }

    public M withSystemUI(boolean includeStatusBar){
        final View decor = mActivity.getWindow().getDecorView();

        if (decor == null){
            return then();
        }

        if(includeStatusBar) {
            final View statusBar = decor.findViewById(android.R.id.statusBarBackground);
            addToCollection(statusBar);
        }

        final View navBar = decor.findViewById(android.R.id.navigationBarBackground);
        addToCollection(navBar);

        return then();
    }

    private void addToCollection(View view){
        if(view == null){
            return;
        }

        like(view);
    }

    @SuppressWarnings("unchecked")
    public M then(){
        if(!mCollection.isEmpty()) {
            final Pair[] pairs = new Pair[mCollection.size()];
            mCollection.toArray(pairs);
            ActivityAnimate.animate(mActivity,mBox,pairs);
        }

        return mOriginal;
    }
}
