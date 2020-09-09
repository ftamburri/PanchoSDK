package ar.com.fennoma.panchotestsdk.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import ar.com.fennoma.panchotestsdk.R;
import ar.com.fennoma.panchotestsdk.presenter.PanchoSDK;
import ar.com.fennoma.panchotestsdk.utils.DialogUtils;

public class FirstActivity extends PanchoBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        setFullTitleWithBack(getString(R.string.activity_first_title));
        setViews();
    }

    private void setViews() {
        if(PanchoSDK.getInstance().getBackgroundColor() != null) {
            View background = findViewById(R.id.background);
            background.setBackgroundColor(PanchoSDK.getInstance().getBackgroundColor());
        }
        if(PanchoSDK.getInstance().getTextColor() != null) {
            TextView text = findViewById(R.id.text);
            text.setTextColor(PanchoSDK.getInstance().getTextColor());
        }
        if(PanchoSDK.getInstance().getButtonBackgroundColor() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                replaceRippleBackgroundColor();
            }
        }
        setButtons();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void replaceRippleBackgroundColor() {
        TextView next = findViewById(R.id.next);
        RippleDrawable background = (RippleDrawable) next.getBackground();
        Drawable drawable = background.getDrawable(0);
        if(drawable != null) {
            ColorStateList myColorStateList = new ColorStateList(
                    new int[][]{
                            new int[]{},
                            new int[]{android.R.attr.state_pressed},
                    },
                    new int[] {
                            PanchoSDK.getInstance().getButtonBackgroundColor(),
                            PanchoSDK.getInstance().getButtonBackgroundColor()
                    }
            );
            drawable.setTintList(myColorStateList);
        }
    }

    private void setButtons() {
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivityForResult(intent, MAIN_FLOW_KEY);
                animActivityRightToLeft();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MAIN_FLOW_KEY && resultCode == RESULT_OK) {
            PanchoSDK.getInstance().onSuccess();
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        DialogUtils.showDialog(this, getString(R.string.first_activity_cancel_title),
                getString(R.string.first_activity_cancel_message),
                new DialogUtils.IDialogListener() {
                    @Override
                    public void onAccept() {
                        PanchoSDK.getInstance().onCancel();
                        FirstActivity.super.onBackPressed();
                    }

                    @Override
                    public void onCancel() {

                    }
                });
    }
}
