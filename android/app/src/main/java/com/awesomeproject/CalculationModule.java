package com.awesomeproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mylibrary.Calculation;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class CalculationModule extends ReactContextBaseJavaModule implements LifecycleEventListener {

    private ReactApplicationContext mContext;

    private final BroadcastReceiver mHeadsetPlugReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                boolean plugged = (intent.getIntExtra("state", 0) == 1);
                String message = plugged ? "Headset plugged in" : "Headset plugged out";
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            }
        }
    };

    public CalculationModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
        registerBroadcastReceiver();
    }

    private void registerBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_HEADSET_PLUG);
        mContext.registerReceiver(mHeadsetPlugReceiver, filter);
    }


    @NonNull
    @Override
    public String getName() {
        return "CalculationModule";
    }

    @ReactMethod
    public void addNumber(int a, int b, Promise promise){
        promise.resolve(new Calculation().addNumber(a,b));
    }

    @ReactMethod
    public void startTrackingAudioJackPlug() {
        registerBroadcastReceiver();
    }

    @Override
    public void onHostResume() {

    }

    @Override
    public void onHostPause() {

    }

    @Override
    public void onHostDestroy() {
        mContext.unregisterReceiver(mHeadsetPlugReceiver);
    }
}
