package com.afree.ctracker.model;

import android.content.Context;

import com.afree.ctracker.collector.CommonDataCollector;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * all data for reporting
 *
 * @author afree on 8/11/16.
 */
public class ReportModel {
  private String stackTrace;
  private long crashTime;
  private String customData;

  private AppModel appModel;
  private SystemModel systemModel;
  private RomModel romModel;
  private RamModel ramModel;

  public long getCrashTime() {
    return crashTime;
  }

  public static class Builder {

    private String mStackTrace;
    private long mCrashTime;
    private String mCustomData;

    public Builder setCrashTime(long crashTime) {
      mCrashTime = crashTime;
      return this;
    }

    public Builder setCustomData(String customData) {
      mCustomData = customData;
      return this;
    }

    public Builder setThrowable(Throwable t) {
      final Writer result = new StringWriter();
      final PrintWriter printWriter = new PrintWriter(result);
      // If the exception was thrown in a background thread inside
      // AsyncTask, then the actual exception can be found with getCause
      Throwable cause = t;
      while (cause != null) {
        cause.printStackTrace(printWriter);
        cause = cause.getCause();
      }
      mStackTrace = result.toString();
      printWriter.close();

      return this;
    }

    public ReportModel build(Context context) {
      ReportModel m = new ReportModel();
      m.stackTrace = mStackTrace;
      m.crashTime = mCrashTime;
      m.customData = mCustomData;

      m.appModel = CommonDataCollector.getAppModel(context);
      m.systemModel = CommonDataCollector.getSystemModel(context);
      m.romModel = CommonDataCollector.getRomModel();
      m.ramModel = CommonDataCollector.getRamModel();
      return m;
    }
  }

}
