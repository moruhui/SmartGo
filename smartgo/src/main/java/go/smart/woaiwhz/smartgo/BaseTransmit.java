package go.smart.woaiwhz.smartgo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by huazhou.whz on 2016/8/10.
 */
public abstract class BaseTransmit<M extends BaseTransmit> {
    protected final Activity mFrom;
    protected Intent mIntent;
    protected M me;

    @SuppressWarnings("unchecked")
    BaseTransmit(@NonNull Activity from){
        mFrom = from;
        me = (M) this;
    }

    public abstract void go();

    public M withExtra(@NonNull String name, boolean value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, byte value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, char value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, short value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, int value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, long value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, float value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, double value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, @NonNull String value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, @NonNull CharSequence value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, @NonNull Parcelable value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, @NonNull Parcelable[] value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, @NonNull Serializable value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, @NonNull boolean[] value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, @NonNull byte[] value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, @NonNull short[] value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, @NonNull char[] value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, @NonNull int[] value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, @NonNull long[] value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, @NonNull float[] value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, @NonNull double[] value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, @NonNull String[] value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, @NonNull CharSequence[] value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withExtra(@NonNull String name, @NonNull Bundle value){
        getIntent().putExtra(name,value);

        return me;
    }

    public M withIntegerArrayListExtra(@NonNull String name, @NonNull ArrayList<Integer> value){
        getIntent().putIntegerArrayListExtra(name,value);

        return me;
    }

    public M withStringArrayListExtra(@NonNull String name, @NonNull ArrayList<String> value){
        getIntent().putStringArrayListExtra(name,value);

        return me;
    }

    public M withParcelableArrayListExtra(@NonNull String name, @NonNull ArrayList<? extends Parcelable> value){
        getIntent().putParcelableArrayListExtra(name,value);

        return me;
    }

    protected Intent getIntent(){
        if(mIntent == null){
            mIntent = new Intent();
        }

        return mIntent;
    }
}
