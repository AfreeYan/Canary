package com.afree.utils;

import android.content.Context;

/**
 * @author afree8909@gmail.com on 6/29/16.
 */
public class GlobalConfig {
  private static Context sContext;

  public static Context getContext() {
    return sContext;
  }

  public static void setContext(Context context) {
    sContext = context;
  }
}
