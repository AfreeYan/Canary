package com.afree.ctracker.collector;

import android.content.Context;

import com.afree.ctracker.file.CrashNativeStorage;
import com.afree.ctracker.model.ReportModel;

/**
 * @author afree on 8/9/16.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
  private final Thread.UncaughtExceptionHandler defaultExceptionHandler;
  private final Context mContext;
  private CrashNativeStorage mStorage;

  public CrashHandler(Context context,CrashNativeStorage storage) {
    mContext = context;
    mStorage = storage;
    defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(this);
  }

  @Override
  public void uncaughtException(Thread thread, Throwable ex) {
    try {
      handleException(thread, ex);
    } finally {
      defaultHandleException(thread, ex);
    }
  }

  private void defaultHandleException(Thread thread, Throwable ex) {
    if (defaultExceptionHandler != null) {
      defaultExceptionHandler.uncaughtException(thread, ex);
    }
  }

  private void handleException(Thread thread, Throwable ex) {
    ReportModel model = buildReportModel(thread, ex);

    saveDumpToNative(model);
  }

  private ReportModel buildReportModel(Thread thread, Throwable ex) {
    ReportModel.Builder builder = new ReportModel.Builder();
    ReportModel m = builder.setThrowable(ex)
            .setCrashTime(System.currentTimeMillis())
            .build(mContext);
    return m;
  }


  private void saveDumpToNative(ReportModel model) {
    mStorage.store(model);
  }


}
