package com.afree.canary.base;

import android.app.Application;
import android.util.Log;

import com.afree.ctracker.CrashTracker;
import com.afree.ctracker.collector.CrashHandler;
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

    CrashTracker.start(this);

  }

  private void initFramework() {
    GlobalConfig.setContext(this);
  }
}
