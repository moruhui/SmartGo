package go.smart.woaiwhz.smartgoproject;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import go.smart.woaiwhz.smartgo.SmartGo;
import go.smart.woaiwhz.smartgo.builder.ServiceBindingBuilder;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1 << 10;
    public static final String REQUEST_STRING = "resolve";

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
    public void launchExplicitActivity(View v){
        SmartGo.from(this)
                .to(ExplicitActivity.class)
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
        SmartGo.from(this)
                .run(BackgroundService.class)
                .bind()
                .connected(new ServiceBindingBuilder.ConnectedService() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Toast.makeText(MainActivity.this,"service connected",Toast.LENGTH_SHORT).show();
                    }
                })
                .disconnected(new ServiceBindingBuilder.DisconnectedService() {
                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        Toast.makeText(MainActivity.this,"service disconnected",Toast.LENGTH_SHORT).show();
                    }
                })
                .then()
                .flag(Service.BIND_AUTO_CREATE)
                .go();
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
