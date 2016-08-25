package go.smart.woaiwhz.smartgo;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by huazhou.whz on 2016/8/24.
 */
public class SharedAnimatorBuilder<M> {
//    private final Intent mIntent;
    private final Box<ActivityOptionsCompat> mBox;
    private final List<Pair> mCollection;
    private final M mOriginal;
    private final Context mContext;

    public SharedAnimatorBuilder(@NonNull Box<ActivityOptionsCompat> box, @NonNull M original, @NonNull Context context){
        mBox = box;
        mCollection = new ArrayList<>();
        mContext = context;
        mOriginal = original;
    }

    public SharedAnimatorBuilder<M> like(@NonNull View view){
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            like(view,view.getTransitionName());
        }

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
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return then();
        }

        if(!(mContext instanceof Activity)){
            return then();
        }

        final Activity activity = (Activity) mContext;
        final View decor = activity.getWindow().getDecorView();

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
            Pair[] pairs = new Pair[mCollection.size()];
            mCollection.toArray(pairs);
            final ActivityAnimateBuilder<M> animateBuilder = new ActivityAnimateBuilder<>(mBox,mOriginal,mContext);
            return (M) animateBuilder.animate(pairs);
        }

        return mOriginal;
    }
}
