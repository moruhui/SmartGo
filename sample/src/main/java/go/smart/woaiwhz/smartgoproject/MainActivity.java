package go.smart.woaiwhz.smartgoproject;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import go.smart.woaiwhz.smartgo.SmartGo;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1 << 10;
    public static final String REQUEST_STRING = "resolve";
    private ServiceConnection mServiceConntect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View root = getWindow().getDecorView();
        Inject j = new Inject(root);
        Toolbar toolbar = j.$(R.id.tool_bar);
        setSupportActionBar(toolbar);
    }

    /**
     * 显式调用
     */
    public void launchExplicitActivity(View v) throws Exception{
        SmartGo.from(this)
                .to(ExplicitActivity.class)
//                .to((Class<? extends Activity>) Class.forName("go.smart.woaiwhz.smartgoproject.ExplicitActivity"))
                .shareElements()
                .like(v)
                .withSystemUI()
                .go();
    }

    /**
     * 隐式调用
     */
    public void launchImplicitActivity(View v){
        SmartGo.from(this)
                .to("go.smart.woaiwhz.smartgoproject.implicit")
                .category("go.smart.woaiwhz.smartgoproject.have.fun")
                .requestCode(REQUEST_CODE)
                .animate(android.R.anim.fade_in, android.R.anim.fade_out)
                .go();
    }

    public void launchService(View v){
        if(mServiceConntect == null){
            mServiceConntect = new MyServiceConnect();
        }

        SmartGo.from(this)
                .run(BackgroundService.class)
                .bind(mServiceConntect, Service.BIND_AUTO_CREATE)
                .go();
    }

    private class MyServiceConnect implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivity.this,"connected",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivity.this,"disconnected",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbindService(mServiceConntect);
    }

    public void launchBroadcast(View v){
        registerReceiver();

        SmartGo.from(this)
                .send("go.smart.woaiwhz.smartgoproject.broadcast")
                .extras()
                .with(REQUEST_STRING,"I'm a broadcast!")
                .then()
                .go();
    }

    private void registerReceiver(){
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(getApplicationContext(),intent.getStringExtra(REQUEST_STRING),Toast.LENGTH_SHORT).show();
            }
        },new IntentFilter("go.smart.woaiwhz.smartgoproject.broadcast"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE && data != null){
            final String result = data.getStringExtra(REQUEST_STRING);

            Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        }
    }
}
