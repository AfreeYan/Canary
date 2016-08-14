package com.afree.ctracker.collector;

import android.content.Context;
import android.os.Debug;
import android.os.Environment;
import android.os.StatFs;

import com.afree.ctracker.model.AppModel;
import com.afree.ctracker.model.RamModel;
import com.afree.ctracker.model.RomModel;
import com.afree.ctracker.model.SystemModel;
import com.afree.ctracker.utils.AppInfoUtils;
import com.afree.ctracker.utils.NetworkUtils;
import com.afree.ctracker.utils.SystemInfoUtils;

import java.io.File;

/**
 * common data collector
 * {@link com.afree.ctracker.model.AppModel}
 * {@link com.afree.ctracker.model.RamModel}
 * {@link com.afree.ctracker.model.SystemModel}
 * {@link com.afree.ctracker.model.RomModel}
 *
 * @author afree on 8/11/16.
 */
public class CommonDataCollector {

  public static AppModel getAppModel(Context context) {

    AppModel appModel = new AppModel();

    appModel.setVersionCode(AppInfoUtils.getVersionCode(context));
    appModel.setVersionName(AppInfoUtils.getVersionName(context));
    appModel.setNetworkType(NetworkUtils.getNetworkTypeName(context));
    appModel.setChannel(AppInfoUtils.getMetaChannel(context));

    return appModel;
  }

  public static SystemModel getSystemModel(Context context) {
    SystemModel m = new SystemModel();
    m.setVersionSDK(SystemInfoUtils.getSdkVersion());
    m.setRelease(SystemInfoUtils.getSdkReleaseVersion());
    m.setManufacture(SystemInfoUtils.getManufacturer());
    m.setDevice(SystemInfoUtils.getDevice());
    m.setHardware(SystemInfoUtils.getHardWare());
    m.setBrand(SystemInfoUtils.getBrand());
    m.setCpuABI(SystemInfoUtils.getCpuABI());
    m.setCpuABI2(SystemInfoUtils.getCpuABI2());
    m.setLanguage(SystemInfoUtils.getLanguage());
    m.setScreenWidthHeight(SystemInfoUtils.getScreenWH(context));
    m.setImei(SystemInfoUtils.getImei(context));
    return m;
  }

  public static RomModel getRomModel() {
    RomModel m = new RomModel();

    File path = Environment.getDataDirectory();
    StatFs sf = new StatFs(path.getPath());
    long availableBlocks = 0;
    long blockCount = 0;
    long size = 0;
    if (android.os.Build.VERSION.SDK_INT >= 18) {
      availableBlocks = sf.getAvailableBlocksLong();
      blockCount = sf.getBlockCountLong();
      size = sf.getBlockSizeLong();
    } else {
      availableBlocks = sf.getAvailableBlocks();
      blockCount = sf.getBlockCount();
      size = sf.getBlockSize();
    }
    long totalSizeInKB = blockCount * (size >> 10);
    long availableSizeInKB = availableBlocks * (size >> 10);

    m.setPrivateMem(Long.toString(totalSizeInKB));
    m.setAvailPrivateMem(Long.toString(availableSizeInKB));
    String state = Environment.getExternalStorageState();
    if (Environment.MEDIA_MOUNTED.equals(state)) {
      path = Environment.getExternalStorageDirectory();
      sf = new StatFs(path.getPath());

      if (android.os.Build.VERSION.SDK_INT >= 18) {
        availableBlocks = sf.getAvailableBlocksLong();
        blockCount = sf.getBlockCountLong();
        size = sf.getBlockSizeLong();
      } else {
        availableBlocks = sf.getAvailableBlocks();
        blockCount = sf.getBlockCount();
        size = sf.getBlockSize();
      }
      totalSizeInKB = blockCount * (size >> 10);
      availableSizeInKB = availableBlocks * (size >> 10);
      m.setExternalMem(Long.toString(totalSizeInKB));
      m.setAvailExternalMem(Long.toString(availableSizeInKB));
    }

    return m;
  }


  public static RamModel getRamModel() {
    RamModel ramInfo = new RamModel();

    android.os.Debug.MemoryInfo memoryInfo = new android.os.Debug.MemoryInfo();
    Debug.getMemoryInfo(memoryInfo);

    ramInfo.setDalvikPss(Integer.toString(memoryInfo.dalvikPss));
    ramInfo.setNativePss(Integer.toString(memoryInfo.nativePss));
    ramInfo.setOtherPss(Integer.toString(memoryInfo.otherPss));
    ramInfo.setDalvikPrivateDirty(Integer.toString(memoryInfo.dalvikPrivateDirty));
    ramInfo.setNativePrivateDirty(Integer.toString(memoryInfo.nativePrivateDirty));
    ramInfo.setOtherPrivateDirty(Integer.toString(memoryInfo.otherPrivateDirty));
    ramInfo.setDalvikSharedDirty(Integer.toString(memoryInfo.dalvikSharedDirty));
    ramInfo.setNativeSharedDirty(Integer.toString(memoryInfo.nativeSharedDirty));
    ramInfo.setOtherSharedDirty(Integer.toString(memoryInfo.otherSharedDirty));

    return ramInfo;
  }
}
