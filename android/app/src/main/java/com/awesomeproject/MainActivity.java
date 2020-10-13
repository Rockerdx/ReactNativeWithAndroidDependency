package com.awesomeproject;

import com.example.mylibrary.Calculation;
import com.facebook.react.ReactActivity;

public class MainActivity extends ReactActivity {

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {

    Calculation calculation = new Calculation();
    calculation.addNumber(4 ,6);

    return "AwesomeProject";
  }
}
