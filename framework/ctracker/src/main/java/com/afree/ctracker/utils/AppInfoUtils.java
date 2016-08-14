package com.afree.ctracker.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

/**
 * @author afree on 8/13/16.
 */
public class AppInfoUtils {
  private static final String META_CHANNEL = "CHANNEL";

  public static String getVersionName(Context context) {
    String version = "1.0.0";
    PackageInfo packageInfo = getPackageInfo(context);
    if (packageInfo != null) {
      version = packageInfo.versionName;
    }
    return version;
  }

  public static int getVersionCode(Context context) {
    int version = 1000;
    PackageInfo packageInfo = getPackageInfo(context);
    if (packageInfo != null) {
      version = packageInfo.versionCode;
    }
    return version;
  }

  public static String getPackageName(Context context) {
    String packageName = "";
    PackageInfo packageInfo = getPackageInfo(context);
    if (packageInfo != null) {
      packageName = packageInfo.packageName;
    }
    return packageName;
  }

  public static PackageInfo getPackageInfo(Context context) {
    try {
      PackageManager pm = context.getPackageManager();
      return pm.getPackageInfo(context.getPackageName(), 0);
    } catch (PackageManager.NameNotFoundException e) {
      return null;
    }
  }

  public static String getMetaChannel(Context context) {
    String channel = "";
    try {
      Bundle data = context.getPackageManager().getApplicationInfo(
              context.getPackageName(), PackageManager.GET_META_DATA).metaData;
      if (data != null) {
        channel = data.getString(META_CHANNEL);
      }
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return channel;
  }

}
