package go.smart.woaiwhz.smartgoproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import go.smart.woaiwhz.smartgo.SmartGo;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1 << 10;
    public static final String REQUEST_STRING = "resolve";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Inject j = new Inject(findViewById(android.R.id.content));
        Toolbar toolbar = j.$(R.id.tool_bar);
        setSupportActionBar(toolbar);
    }

    /**
     * 显式调用
     */
    public void launchExplicitActivity(View v){
        SmartGo.from(this)
                .to(ExplicitActivity.class)
                .shareElements()
                .like(findViewById(R.id.launch_explicit))
                .withSystemUI()
                .go();
    }

    /**
     * 隐式调用
     */
    public void launchImplicitActivity(View v){
        SmartGo.from(this)
                .to("go.smart.woaiwhz.smartgoproject.implicit")
                .withCategory("go.smart.woaiwhz.smartgoproject.have.fun")
                .andRequestCode(REQUEST_CODE)
                .animate(android.R.anim.fade_in, android.R.anim.fade_out)
                .go();
    }

    public void launchService(View v){
        SmartGo.from(this)
                .run(BackgroundService.class)
//                .bind()
//                .whenConnected(new ServiceTransmit.ConnectedService() {
//                    @Override
//                    public void onServiceConnected(ComponentName name, IBinder service) {
//
//                    }
//                })
//                .whenDisconnected(new ServiceTransmit.DisconnectedService() {
//                    @Override
//                    public void onServiceDisconnected(ComponentName name) {
//
//                    }
//                })
//                .withFlag(2)
//                .fine()
                .go();
    }

    public void launchBroadCast(View v){
        registerReceiver();

        SmartGo.from(this)
                .send("go.smart.woaiwhz.smartgoproject.broadcast")
                .withExtra(REQUEST_STRING,"I'm a broadcast!")
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
