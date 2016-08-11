package go.smart.woaiwhz.smartgo;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

/**
 * Created by huazhou.whz on 2016/8/10.
 */
public abstract class ActivityTransmit<M extends ActivityTransmit> extends BaseTransmit<M> {
    protected static final int INIT_REQUEST_CODE = -1;

    protected int mRequestCode = INIT_REQUEST_CODE;
    protected ActivityOptionsCompat mOption;

    ActivityTransmit(@NonNull Activity from) {
        super(from);
    }

    public void go(){
        prepare2Go();

        if(isAvailable()) {
            ActivityCompat.startActivityForResult(mFrom,mIntent,mRequestCode,mOption.toBundle());
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

    private boolean isOptionNull(){
        return mOption == null;
    }
}
