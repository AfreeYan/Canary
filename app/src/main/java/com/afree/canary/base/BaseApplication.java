package com.afree.canary.base;

import android.app.Application;

import com.afree.utils.GlobalConfig;

/**
 * @author afree8909@gmail.com on 6/29/16.
 */
public class BaseApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    init();
  }

  private void init() {
    initFramework();
  }

  private void initFramework() {
    GlobalConfig.setContext(this);
  }
}
