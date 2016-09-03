package go.smart.woaiwhz.smartgoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ImplicitActivity extends AppCompatActivity {
    public static final int RESULT_CODE = 1 << 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        setResultSolved();
    }

    private void setResultSolved() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.REQUEST_STRING, "You Jump,I Jump");
        setResult(RESULT_CODE, intent);
    }
}
