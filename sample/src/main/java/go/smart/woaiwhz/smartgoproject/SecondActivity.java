package go.smart.woaiwhz.smartgoproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Inject j = new Inject(findViewById(android.R.id.content));
        Toolbar toolbar = j.$(R.id.tool_bar);
        setSupportActionBar(toolbar);
    }
}
