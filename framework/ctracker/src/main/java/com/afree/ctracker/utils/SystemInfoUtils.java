package com.afree.ctracker.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.File;
import java.util.Locale;

/**
 * @author afree on 8/14/16.
 */
public class SystemInfoUtils {


  public static String getLanguage() {
    return Locale.getDefault().getLanguage();
  }

  public static int getScreenWidth(Context context) {
    DisplayMetrics displayMetrics = getDisplayMetrics(context);
    return displayMetrics.widthPixels;
  }

  public static int getScreenHeight(Context context) {
    DisplayMetrics displayMetrics = getDisplayMetrics(context);
    return displayMetrics.heightPixels;
  }

  public static String getScreenWH(Context context) {
    int w = getScreenWidth(context);
    int h = getScreenHeight(context);

    return new StringBuilder().append(w).append("_").append(h).toString();
  }


  private static DisplayMetrics getDisplayMetrics(Context context) {
    return context.getResources().getDisplayMetrics();
  }

  public static boolean isSDCardMounted() {
    return Environment.getExternalStorageState().equals(
            Environment.MEDIA_MOUNTED);
  }

  public static boolean isExternalSDCardMounted() {
    if (Build.VERSION.SDK_INT < 11) {
      return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    } else {
      return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
              && !Environment.isExternalStorageEmulated();
    }
  }

  public static String getMacAddress(Context context) {
    WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    if (wifi == null) {
      return null;
    }
    WifiInfo info = null;
    try {
      // here maybe throw exception in android framework
      info = wifi.getConnectionInfo();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (info != null) {
      return info.getMacAddress();
    } else {
      return null;
    }
  }

  public static String getWifiIPAddress(Context context) {
    try {
      WifiManager mgr = (WifiManager) context
              .getSystemService(Context.WIFI_SERVICE);
      if (mgr == null) {
        return null;
      }

      WifiInfo info = mgr.getConnectionInfo();
      if (info == null) {
        return null;
      }
      // if (info.getSSID() == null) return null;

      int ipAddress = info.getIpAddress();
      if (ipAddress == 0) {
        return null;
      }

      String ip = String.format(Locale.US, "%d.%d.%d.%d", (ipAddress & 0xff),
              (ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff),
              (ipAddress >> 24 & 0xff));

      return ip;
    } catch (Exception e) {
      return null;
    }
  }

  public static String getSdkVersion() {
    try {
      return Build.VERSION.SDK;
    } catch (Exception e) {
      e.printStackTrace();
      return String.valueOf(getSdkVersionInt());
    }
  }

  public static int getSdkVersionInt() {
    try {
      return Build.VERSION.SDK_INT;
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }


  public static String getSdkReleaseVersion() {
    try {
      return Build.VERSION.RELEASE;
    } catch (Exception e) {
      e.printStackTrace();
      return getSdkVersion();
    }
  }

  public static String getManufacturer() {
    return getSystemProperties("ro.product.manufacturer");
  }

  public static String getDevice() {
    return getSystemProperties("ro.product.device");
  }

  public static String getHardWare() {
    return getSystemProperties("ro.hardware");
  }

  public static String getBrand() {
    return getSystemProperties("ro.product.brand");
  }

  public static String getCpuABI() {
    return getSystemProperties("ro.product.cpu.abi");
  }

  public static String getCpuABI2() {
    return getSystemProperties("ro.product.cpu.abi2");
  }

  private static String getSystemProperties(String key) {
    return SystemProperties.get(key, "unknown");
  }


  /**
   * if the external storage device which is emulated, that mean the devices
   * does not have real external storage ,result includes that devices.
   *
   * @return
   */
  public static long getAvailableExternalStorage() {
    try {
      File file = Environment.getExternalStorageDirectory();
      if (file != null && file.exists()) {
        StatFs sdFs = new StatFs(file.getPath());
        if (sdFs != null) {
          long sdBlockSize = sdFs.getBlockSize();
          long sdAvailCount = sdFs.getAvailableBlocks();
          return sdAvailCount * sdBlockSize;
        }
      }
      return 0;
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  public static long getTotalExternalMemorySize() {
    try {
      File file = Environment.getExternalStorageDirectory();
      if (file != null && file.exists()) {
        StatFs sdFs = new StatFs(file.getPath());
        if (sdFs != null) {
          long sdBlockSize = sdFs.getBlockSize();
          long sdTotalCount = sdFs.getBlockCount();
          return sdTotalCount * sdBlockSize;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  public static long getAvailableInternalStorage() {
    File file = Environment.getDataDirectory();
    if (file != null && file.exists()) {
      StatFs sdFs = new StatFs(file.getPath());
      if (sdFs != null) {
        long sdBlockSize = sdFs.getBlockSize();
        long sdAvailCount = sdFs.getAvailableBlocks();
        return sdAvailCount * sdBlockSize;
      }
    }
    return 0;
  }

  public static long getTotalInternalMemorySize() {
    File path = Environment.getDataDirectory();
    if (path != null && path.exists()) {
      StatFs stat = new StatFs(path.getPath());
      long blockSize = stat.getBlockSize();
      long totalBlocks = stat.getBlockCount();
      return totalBlocks * blockSize;
    }
    return 0;
  }

  public static boolean checkAvailableInternalStorage(long size) {
    long availabelStorage = getAvailableInternalStorage();
    // if apkSize is -1 , do not check
    if (size < 0) {
      return true;
    }
    if (availabelStorage <= 0) {
      return false;
    }
    return availabelStorage >= size;
  }

  public static boolean checkAvailableExternalStorage(long size) {
    long availabelStorage = getAvailableExternalStorage();
    // if apkSize is -1 , do not check
    if (size < 0) {
      return true;
    }
    if (availabelStorage <= 0) {
      return false;
    }
    return availabelStorage >= size;
  }


  public static boolean checkAvailableStorage(long size) {
    long availabelStorage = getAvailableExternalStorage();
    // if apkSize is -1 , do not check
    if (size < 0) {
      return true;
    }
    if (availabelStorage <= 0) {
      return false;
    }
    return availabelStorage >= size;
  }

  /**
   * check if the mobile has been rooted
   *
   * @return the mobile has been rooted
   * @throws java.io.IOException
   * @author TQS
   */
  public static boolean isRooted() {
    boolean rooted = false;
    boolean hasSuFile = false;
    String command = "ls -l /%s/su";
    File su = new File("/system/bin/su");
    if (su.exists()) {
      hasSuFile = true;
      command = String.format(command, "system/bin");
    } else {
      su = new File("/system/xbin/su");
      if (su.exists()) {
        hasSuFile = true;
        command = String.format(command, "system/xbin");
      } else {
        su = new File("/data/bin/su");
        if (su.exists()) {
          hasSuFile = true;
          command = String.format(command, "data/bin");
        }
      }
    }

    if (hasSuFile == true) {
      rooted = true;
    }

    return rooted;
  }


  @TargetApi(Build.VERSION_CODES.DONUT)
  public static String getDpi(WindowManager windowManager) {
    if (windowManager == null) {
      return "";
    }
    int densityDpi = getMetricsSize(windowManager);
    switch (densityDpi) {
      case DisplayMetrics.DENSITY_LOW:
        return "ldpi";
      case DisplayMetrics.DENSITY_MEDIUM:
        return "mdpi";
      case DisplayMetrics.DENSITY_HIGH:
        return "hdpi";
      case DisplayMetrics.DENSITY_XHIGH:
        return "xhpdi";
      case DisplayMetrics.DENSITY_XXHIGH:
      default:
        return "xxhdpi";
    }
  }


  @TargetApi(Build.VERSION_CODES.DONUT)
  public static int getMetricsSize(WindowManager windowManager) {
    if (windowManager == null) {
      return 0;
    }
    DisplayMetrics metrics = new DisplayMetrics();
    windowManager.getDefaultDisplay().getMetrics(metrics);
    return metrics.densityDpi;
  }

  public static String getImei(Context context) {
    try {
      TelephonyManager telephonyManager = (TelephonyManager) context
              .getSystemService(Context.TELEPHONY_SERVICE);
      return telephonyManager.getDeviceId();
    } catch (Exception e) {
      // In some devices, we are not able to get device id, and may cause some exception,
      // so catch it.
      return "";
    }
  }

}
