package go.smart.woaiwhz.smartgo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import go.smart.woaiwhz.smartgo.builder.BundleBuilder;
import go.smart.woaiwhz.smartgo.builder.SharedAnimatorBuilder;

/**
 * Created by huazhou.whz on 2016/8/10.
 */
public class Implicit extends BaseActivityLauncher {
    private Uri mData;
    private String mType;
    private String mAction;
    private List<String> mCategories;

    public Implicit(@NonNull Context from, @NonNull String action) {
        super(from);

        mAction = action;
    }

    public Implicit data(@NonNull Uri data){
        mData = data;

        return this;
    }

    public Implicit type(@NonNull String type){
        mType = type;

        return this;
    }

    public Implicit category(@NonNull String category){
        if(mCategories == null){
            mCategories = new ArrayList<>();
        }
        mCategories.add(category);

        return this;
    }

    public Implicit requestCode(int requestCode) {
        return requestCode(this,requestCode);
    }

    public Implicit flag(final int flag){
        return flag(this,flag);
    }

    public Implicit animate(@AnimRes int enterResId, @AnimRes int exitResId){
        return animate(this,enterResId,exitResId);
    }

    public Implicit animate(@NonNull View source, int startX, int startY,
                            int startWidth, int startHeight){
        return animate(this,source,startX,startY,startWidth,startHeight);
    }

    public Implicit animate(@NonNull View sharedElement, @NonNull String sharedElementName){
        return animate(this,sharedElement,sharedElementName);
    }

    public Implicit animate(@NonNull View source,
                            @NonNull Bitmap thumbnail, int startX, int startY){
        return animate(this,source,thumbnail,startX,startY);
    }

    public Implicit animate(@NonNull Pair<View, String>... sharedElements){
        return animate(this,sharedElements);
    }

    public SharedAnimatorBuilder<Implicit> shareElements(){
        return shareElements(this);
    }

    public BundleBuilder<Implicit> extras(){
        return extras(this);
    }

    @Override
    public void preGo(@NonNull final Intent intent) {
        intent.setAction(mAction);
        prepareCategories(intent);
        appendDataAndType(intent);

        super.preGo(intent);
    }

    private void prepareCategories(final Intent intent){
        if(mCategories != null){
            for (final String category : mCategories){
                intent.addCategory(category);
            }
        }
    }

    private void appendDataAndType(final Intent intent){
        if(mData == null || mType == null){
            if(mType != null){
                intent.setType(mType);
            }else if(mData != null){
                intent.setData(mData);
            }
        }else {
            intent.setDataAndType(mData,mType);
        }
    }
}
