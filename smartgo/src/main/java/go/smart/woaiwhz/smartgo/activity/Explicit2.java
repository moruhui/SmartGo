package go.smart.woaiwhz.smartgo.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;

import go.smart.woaiwhz.smartgo.Box;
import go.smart.woaiwhz.smartgo.Extras;
import go.smart.woaiwhz.smartgo.IntentBuilder;
import go.smart.woaiwhz.smartgo.SharedAnimatorBuilder;

/**
 * Created by huazhou.whz on 2016/8/23.
 */
public class Explicit2{
    private final Intent mIntent;
    private final Context mFrom;
    private Box<ActivityOptionsCompat> mActivityOptionsBox;

    public Explicit2(Context from, Class<?> to){
        mFrom = from;
        mIntent = IntentBuilder.build(from,to);
    }

    public SharedAnimatorBuilder<Explicit2> sharedAnimate(){
        mActivityOptionsBox = new Box<>();

        return new SharedAnimatorBuilder<>(mActivityOptionsBox,this,mFrom);
    }

    public Extras<Explicit2> extras(){
        return new Extras<>(mIntent,this);
    }

    public void test(){

    }
}
