package go.smart.woaiwhz.smartgoproject;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by huazhou.whz on 2016/8/4.
 */
public class Inject {
    private final View mRoot;

    public Inject(@NonNull View root){
        mRoot = root;
    }

    @SuppressWarnings("unchecked")
    public <T> T $(@IdRes int id){
        return (T) mRoot.findViewById(id);
    }
}
