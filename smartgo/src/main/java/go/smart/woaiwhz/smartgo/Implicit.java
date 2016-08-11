package go.smart.woaiwhz.smartgo;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Created by huazhou.whz on 2016/8/10.
 */
public final class Implicit extends ActivityTransmit<Implicit> {
    private Uri mData;
    private String mType;
    private String mAction;

    Implicit(@NonNull Activity from,@NonNull String action) {
        super(from);

        mAction = action;
    }

    public Implicit withData(@NonNull Uri data){
        if(mData == null){
            mData = data;
        }

        return me;
    }

    public Implicit withType(@NonNull String type){
        if(mType == null){
            mType = type;
        }

        return me;
    }

    public Implicit withCategory(@NonNull String category){
        getIntent().addCategory(category);

        return me;
    }

    @Override
    public void prepare2Go() {
        getIntent().setAction(mAction);
        checkDataAndType();
    }

    private void checkDataAndType(){
        if(mData == null || mType == null){
            if(mType != null){
                getIntent().setType(mType);
            }else if(mData != null){
                getIntent().setData(mData);
            }
        }else {
            getIntent().setDataAndType(mData,mType);
        }
    }
}
