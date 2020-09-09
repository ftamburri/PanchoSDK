package ar.com.fennoma.panchotestsdk.activities;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import ar.com.fennoma.panchotestsdk.R;
import ar.com.fennoma.panchotestsdk.presenter.PanchoSDK;

public class FourthActivity extends PanchoBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        setFullTitleWithBack(getString(R.string.activity_fourth_title));
        setViews();
    }

    private void setViews() {
        View background = findViewById(R.id.background);
        if(PanchoSDK.getInstance().getBackgroundColor() != null) {
            background.setBackgroundColor(PanchoSDK.getInstance().getBackgroundColor());
        }
        setButtons();
    }

    private void setButtons() {
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        animActivityLeftToRight();
    }
}
