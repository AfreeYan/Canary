package com.afree.ctracker;

import android.content.Context;
import android.content.Intent;

import com.afree.ctracker.collector.CrashHandler;
import com.afree.ctracker.collector.CrashSenderService;
import com.afree.ctracker.config.IConfiguration;
import com.afree.ctracker.file.CrashNativeStorage;
import com.afree.ctracker.network.DefaultCrashSender;
import com.afree.ctracker.network.ICrashSender;

import java.io.File;

/**
 * @author afree on 8/7/16.
 */

public class CrashTracker {
  private CrashHandler mCrashHandler;
  private IConfiguration mConfiguration;
  private CrashNativeStorage mStorage;

  private static class InstanceHolder {
    private static CrashTracker sInstance = new CrashTracker();
  }

  public static CrashTracker getInstance() {
    return InstanceHolder.sInstance;
  }

  private CrashTracker() {
  }

  public static void start(Context context, IConfiguration configuration) {
    if (context == null || configuration == null) {
      return;
    }
    getInstance().setStorage(new CrashNativeStorage(context));
    getInstance().setCrashHandler(new CrashHandler(context, getInstance().mStorage));
    getInstance().setConfiguration(configuration);

    getInstance().report(context);
  }


  private void setConfiguration(IConfiguration configuration) {
    mConfiguration = configuration;
  }

  private void setCrashHandler(CrashHandler crashHandler) {
    mCrashHandler = crashHandler;
  }

  private void setStorage(CrashNativeStorage storage) {
    mStorage = storage;
  }

  public ICrashSender getCrashSender() {
    if (mConfiguration.getCrashSender() == null) {
      return new DefaultCrashSender(mConfiguration);
    }
    return mConfiguration.getCrashSender();
  }

  public File getStorageFolder() {
    return mStorage == null ? null : mStorage.getStorageFolder();
  }

  private void report(Context context) {
    Intent intent = new Intent(context, CrashSenderService.class);
    context.startService(intent);
  }

}
