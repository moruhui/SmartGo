package go.smart.woaiwhz.smartgo.builder;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

import go.smart.woaiwhz.smartgo.base.Box;

/**
 * Created by huazhou.whz on 2016/8/22.
 */
public class BundleBuilder<M> {
    private final Box<Bundle> mBox;
    private final Bundle mBundle;
    private final M mOriginal;

    public BundleBuilder(@NonNull Box<Bundle> box, M original) {
        mBox = box;
        mBundle = new Bundle();
        mOriginal = original;
    }
    
    public BundleBuilder<M> with(@NonNull String name, boolean value){
        mBundle.putBoolean(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, byte value){
        mBundle.putByte(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, char value){
        mBundle.putChar(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, short value){
        mBundle.putShort(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, int value){
        mBundle.putInt(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, long value){
        mBundle.putLong(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, float value){
        mBundle.putFloat(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, double value){
        mBundle.putDouble(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, @NonNull String value){
        mBundle.putString(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, @NonNull CharSequence value){
        mBundle.putCharSequence(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, @NonNull Parcelable value){
        mBundle.putParcelable(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, @NonNull Parcelable[] value){
        mBundle.putParcelableArray(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, @NonNull Serializable value){
        mBundle.putSerializable(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, @NonNull boolean[] value){
        mBundle.putBooleanArray(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, @NonNull byte[] value){
        mBundle.putByteArray(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, @NonNull short[] value){
        mBundle.putShortArray(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, @NonNull char[] value){
        mBundle.putCharArray(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, @NonNull int[] value){
        mBundle.putIntArray(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, @NonNull long[] value){
        mBundle.putLongArray(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, @NonNull float[] value){
        mBundle.putFloatArray(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, @NonNull double[] value){
        mBundle.putDoubleArray(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, @NonNull String[] value){
        mBundle.putStringArray(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, @NonNull CharSequence[] value){
        mBundle.putCharSequenceArray(name,value);

        return this;
    }

    public BundleBuilder<M> with(@NonNull String name, @NonNull Bundle value){
        mBundle.putBundle(name,value);

        return this;
    }

    public BundleBuilder<M> integerArrayList(@NonNull String name, @NonNull ArrayList<Integer> value){
        mBundle.putIntegerArrayList(name,value);

        return this;
    }

    public BundleBuilder<M> stringArrayList(@NonNull String name, @NonNull ArrayList<String> value){
        mBundle.putStringArrayList(name,value);

        return this;
    }

    public BundleBuilder<M> parcelableArrayList(@NonNull String name, @NonNull ArrayList<? extends Parcelable> value){
        mBundle.putParcelableArrayList(name,value);

        return this;
    }


    public M then(){
        mBox.set(mBundle);

        return mOriginal;
    }
}
