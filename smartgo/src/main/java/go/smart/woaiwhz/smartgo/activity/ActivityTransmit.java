package go.smart.woaiwhz.smartgo.activity;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import go.smart.woaiwhz.smartgo.BaseTransmit;
import go.smart.woaiwhz.smartgo.BuildConfig;

/**
 * Created by huazhou.whz on 2016/8/10.
 */
public abstract class ActivityTransmit<M extends ActivityTransmit> extends BaseTransmit<M> {
    protected static final int INIT_REQUEST_CODE = -1;

    protected int mRequestCode = INIT_REQUEST_CODE;
    protected ActivityOptionsCompat mOption;

    public ActivityTransmit(@NonNull Activity from) {
        super(from);
    }

    public void go(){
        prepare2Go();

        if(isAvailable()) {
            ActivityCompat.startActivityForResult(mFrom,mIntent,mRequestCode,mOption.toBundle());
        }else if(BuildConfig.DEBUG){
            Log.e(TAG, "have no resolved activity");
        }
    }

    private boolean isAvailable(){
        final PackageManager manager = mFrom.getPackageManager();

        return mIntent.resolveActivity(manager) != null;
    }

    protected abstract void prepare2Go();

    public M andRequestCode(int requestCode){
        mRequestCode = requestCode;

        return me;
    }

    public M withFlag(int flag){
        getIntent().addFlags(flag);

        return me;
    }

    public M animate(@AnimRes int enterResId, @AnimRes int exitResId){
        if(isOptionNull()){
            mOption = ActivityOptionsCompat.makeCustomAnimation(mFrom,enterResId,exitResId);
        }

        return me;
    }

    public M animate(@NonNull View source, int startX, int startY,
                     int startWidth, int startHeight){
        if(isOptionNull()){
            mOption = ActivityOptionsCompat.makeScaleUpAnimation(source, startX, startY,
                    startWidth, startHeight);
        }

        return me;
    }

    public M animate(@NonNull View sharedElement,@NonNull String sharedElementName){
        if(isOptionNull()){
            mOption = ActivityOptionsCompat.makeSceneTransitionAnimation(mFrom,sharedElement,sharedElementName);
        }

        return me;
    }

    public M animate(@NonNull Pair<View, String>... sharedElements){
        if(isOptionNull()){
            mOption = ActivityOptionsCompat.makeSceneTransitionAnimation(mFrom,sharedElements);
        }

        return me;
    }

    public M animate(@NonNull View source, Bitmap thumbnail, int startX, int startY){
        if(isOptionNull()){
            mOption = ActivityOptionsCompat.makeThumbnailScaleUpAnimation(source, thumbnail, startX, startY);
        }

        return me;
    }

    // TODO: 2016/8/11
    @SuppressWarnings("unchecked")
    public SharedAnimatorBuilder<M> shareElements(){
        return new SharedAnimatorBuilder(this);
    }

    public static final class SharedAnimatorBuilder<M extends ActivityTransmit>{
        private final M external;
        private final List<Pair> mCollection;

        public SharedAnimatorBuilder(M external){
            this.external = external;

            mCollection = new ArrayList<>();
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
                return fine();
            }

            final View decor = external.mFrom.getWindow().getDecorView();

            if (decor == null){
                return fine();
            }

            if(includeStatusBar) {
                final View statusBar = decor.findViewById(android.R.id.statusBarBackground);
                addToCollection(statusBar);
            }

            final View navBar = decor.findViewById(android.R.id.navigationBarBackground);
            addToCollection(navBar);

            return fine();
        }

        private void addToCollection(View view){
            if(view == null){
                return;
            }

            like(view);
        }

        @SuppressWarnings("unchecked")
        public M fine(){
            if(!mCollection.isEmpty()) {
                Pair[] pairs = new Pair[mCollection.size()];
                mCollection.toArray(pairs);
                external.animate(pairs);
            }

            return external;
        }
    }

    private boolean isOptionNull(){
        return mOption == null;
    }
}
