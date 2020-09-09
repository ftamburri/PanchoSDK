package ar.com.fennoma.sdktester;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ar.com.fennoma.panchotestsdk.presenter.PanchoSDK;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PanchoSDK.getInstance()
                        .setBackgroundColor(getResources().getColor(R.color.sdk_background))
                        .setButtonBackgroundColor(getResources().getColor(android.R.color.holo_green_dark))
                        .setTextColor(getResources().getColor(android.R.color.holo_red_dark))
                        .setContext(MainActivity.this)
                        .setListener(new PanchoSDK.IPanchoSDKListener() {
                            @Override
                            public void onFinishedSuccessfully() {
                                Toast.makeText(MainActivity.this, "El flujo terminó bien", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onCancel() {
                                Toast.makeText(MainActivity.this, "Cancelado", Toast.LENGTH_LONG).show();
                            }
                        })
                        .start();
            }
        });
    }
}

