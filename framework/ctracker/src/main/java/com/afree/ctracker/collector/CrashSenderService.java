package com.afree.ctracker.collector;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.afree.ctracker.CrashTracker;
import com.afree.ctracker.network.ICrashSender;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author afree on 8/9/16.
 */

public class CrashSenderService extends IntentService {

  public CrashSenderService() {
    super(CrashSenderService.class.getName());
  }


  @Override
  protected void onHandleIntent(Intent intent) {
    send();
  }

  private void send() {

    File[] targets = getStorageFiles();
    if (targets.length == 0) {
      return;
    }

    ICrashSender sender = CrashTracker.getInstance().getCrashSender();
    for (File f : targets) {
      if (f == null) {
        continue;
      }
      sender.send(this, f);

      deleteFile(f);
    }
  }

  private void deleteFile(File f) {
    boolean deleted = f.delete();
    if (!deleted) {
      Log.e(CrashSenderService.class.getName(), "cannot delete file:" + f.getName());
    }
  }

  @NonNull
  public File[] getStorageFiles() {
    File[] files = null;
    if (CrashTracker.getInstance().getStorageFolder() != null) {
      files = CrashTracker.getInstance().getStorageFolder().listFiles();
    }
    if (files == null) {
      return new File[0];
    }
    Arrays.sort(files, new Comparator<File>() {
      @Override
      public int compare(File lhs, File rhs) {
        return (int) (lhs.lastModified() - rhs.lastModified());
      }
    });
    return files;
  }

}
