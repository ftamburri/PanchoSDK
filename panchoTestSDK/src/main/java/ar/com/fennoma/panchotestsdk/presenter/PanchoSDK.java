package ar.com.fennoma.panchotestsdk.presenter;

import android.content.Context;
import android.content.Intent;

import ar.com.fennoma.panchotestsdk.activities.FirstActivity;

public class PanchoSDK {

    private static PanchoSDK instance;

    private Context context;
    private IPanchoSDKListener listener;
    private Integer backgroundColor;

    public interface IPanchoSDKListener {
        void onFinishedSuccessfully();
        void onCancel();
    }

    public static PanchoSDK getInstance() {
        if(instance == null) {
            instance = new PanchoSDK();
        }
        return instance;
    }

    public PanchoSDK setBackgroundColor(int color) {
        backgroundColor = color;
        return this;
    }

    public PanchoSDK setListener(IPanchoSDKListener listener) {
        this.listener = listener;
        return this;
    }

    public PanchoSDK setContext(Context context) {
        this.context = context;
        return this;
    }

    public Integer getBackgroundColor() {
        return backgroundColor;
    }

    public void start() {
        Intent intent = new Intent(context, FirstActivity.class);
        context.startActivity(intent);
    }

    public void onCancel() {
        if(listener != null) {
            listener.onCancel();
        }
    }

    public void onSuccess() {
        if(listener != null) {
            listener.onFinishedSuccessfully();
        }
    }
}
