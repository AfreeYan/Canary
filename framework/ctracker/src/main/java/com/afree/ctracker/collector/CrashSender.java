package com.afree.ctracker.collector;

import android.app.IntentService;
import android.content.Intent;

/**
 * @author afree on 8/9/16.
 */

public class CrashSender extends IntentService {
  public CrashSender() {
    super(CrashSender.class.getName());
  }


  @Override
  protected void onHandleIntent(Intent intent) {
  }

}
