package ar.com.fennoma.panchotestsdk.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import ar.com.fennoma.panchotestsdk.R;
import ar.com.fennoma.panchotestsdk.presenter.PanchoSDK;

public class FirstActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        setViews();
    }

    private void setViews() {
        View background = findViewById(R.id.background);
        if(PanchoSDK.getInstance().getBackgroundColor() != null) {
            background.setBackgroundColor(PanchoSDK.getInstance().getBackgroundColor());
        }
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PanchoSDK.getInstance().onSuccess();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        PanchoSDK.getInstance().onCancel();
        super.onBackPressed();
    }
}
