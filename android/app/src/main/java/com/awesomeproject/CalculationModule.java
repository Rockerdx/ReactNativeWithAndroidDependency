package com.awesomeproject;

import androidx.annotation.NonNull;

import com.example.mylibrary.Calculation;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class CalculationModule extends ReactContextBaseJavaModule {

    @NonNull
    @Override
    public String getName() {
        return "CalculationModule";
    }

    @ReactMethod
    public int addNumber(int a, int b){
        return new Calculation().addNumber(a,b);
    }
}
