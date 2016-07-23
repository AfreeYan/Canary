package com.afree.canary.utils;

import android.os.Handler;
import android.os.Looper;


/**
 * @author afree8909@gmail.com on 7/23/16.
 */
public class MainThreadUtils {

  private static Handler sHandler;

  public static Handler getHandler() {
    synchronized (MainThreadUtils.class) {
      if (sHandler == null) {
        sHandler = new Handler(Looper.getMainLooper());
      }
      return sHandler;
    }
  }

  public static void post(Runnable run) {
    getHandler().post(run);
  }

  public static void postDelayed(Runnable run, long delayMillis) {
    getHandler().postDelayed(run, delayMillis);
  }

}
