package com.afree.ctracker;

import android.content.Context;

import com.afree.ctracker.collector.CrashHandler;

/**
 * @author afree on 8/7/16.
 */

public class CrashTracker {

  public static void start(Context context) {
    CrashHandler crashHandler = new CrashHandler(context);
  }
}
