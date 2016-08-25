package go.smart.woaiwhz.smartgo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by huazhou.whz on 2016/8/22.
 */
public class Extras<M> {
    private final Intent mIntent;
    private final M mOriginal;

    public Extras(Intent intent,M original) {
        mIntent = intent;
        mOriginal = original;
    }
    
    public Extras<M> with(@NonNull String name, boolean value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, byte value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, char value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, short value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, int value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, long value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, float value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, double value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, @NonNull String value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, @NonNull CharSequence value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, @NonNull Parcelable value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, @NonNull Parcelable[] value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, @NonNull Serializable value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, @NonNull boolean[] value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, @NonNull byte[] value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, @NonNull short[] value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, @NonNull char[] value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, @NonNull int[] value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, @NonNull long[] value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, @NonNull float[] value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, @NonNull double[] value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, @NonNull String[] value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, @NonNull CharSequence[] value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> with(@NonNull String name, @NonNull Bundle value){
        mIntent.putExtra(name,value);

        return this;
    }

    public Extras<M> integerArrayList(@NonNull String name, @NonNull ArrayList<Integer> value){
        mIntent.putIntegerArrayListExtra(name,value);

        return this;
    }

    public Extras<M> stringArrayList(@NonNull String name, @NonNull ArrayList<String> value){
        mIntent.putStringArrayListExtra(name,value);

        return this;
    }

    public Extras<M> parcelableArrayList(@NonNull String name, @NonNull ArrayList<? extends Parcelable> value){
        mIntent.putParcelableArrayListExtra(name,value);

        return this;
    }

    public M then(){
        return mOriginal;
    }

//    public interface ExtrasBehavior{
//
//    }
}
