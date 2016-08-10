package go.smart.woaiwhz.smartgoproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import go.smart.woaiwhz.smartgo.SmartGo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Inject j = new Inject(findViewById(android.R.id.content));
        Toolbar toolbar = j.$(R.id.tool_bar);
        setSupportActionBar(toolbar);
    }

    @SuppressWarnings("unused")
    public void launchActivity(View v){
        SmartGo.from(this)
                .to(SecondActivity.class)
                .animate(android.R.anim.fade_in, android.R.anim.fade_out)
                .go();
    }
}
