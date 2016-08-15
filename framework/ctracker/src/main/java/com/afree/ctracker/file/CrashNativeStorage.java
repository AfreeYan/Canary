package com.afree.ctracker.file;

import android.content.Context;
import android.support.annotation.NonNull;

import com.afree.ctracker.model.ReportModel;
import com.afree.ctracker.utils.IOUtils;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author afree on 8/15/16.
 */
public class CrashNativeStorage {
  private static final String NAME_STORAGE = "CrashTracker";
  private static final String CRASH_FILE_EXTENSION = ".txt";
  private Context mContext;

  public CrashNativeStorage(Context context) {
    mContext = context;
  }

  @NonNull
  public File getStorageFolder() {
    return mContext.getDir(NAME_STORAGE, Context.MODE_PRIVATE);
  }

  public File createStorageFile(String fileName) {
    return new File(getStorageFolder(), fileName);
  }

  public void store(ReportModel model) {

    String str = convertDataToString(model);
    String fileName = String.valueOf(model.getCrashTime()).concat(CRASH_FILE_EXTENSION);
    File file = createStorageFile(fileName);
    FileWriter fw = null;
    try {
      fw = new FileWriter(file);
      fw.write(str);
    } catch (Exception e) {
      throw new RuntimeException("exception occur on storing crash dump");
    } finally {
      IOUtils.safeClose(fw);
    }
  }

  private String convertDataToString(ReportModel model) {
    Gson gson = new Gson();
    return gson.toJson(model);
  }
}
