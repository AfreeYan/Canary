package com.afree.ctracker.model;


/**
 * info about app
 *
 * @author afree on 8/11/16.
 */
public class AppModel {

  private String appId;       // 应用名称应用名称
  private String versionName;   // 版本名称
  private int versionCode;   // 版本号
  private String networkType;   // 网络类型 mobile/WIFI
  private String channel;       // 渠道名称

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public String getNetworkType() {
    return networkType;
  }

  public void setNetworkType(String networkType) {
    this.networkType = networkType;
  }

  public int getVersionCode() {
    return versionCode;
  }

  public void setVersionCode(int versionCode) {
    this.versionCode = versionCode;
  }

  public String getVersionName() {
    return versionName;
  }

  public void setVersionName(String versionName) {
    this.versionName = versionName;
  }
}
