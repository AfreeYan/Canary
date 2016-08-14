package com.afree.ctracker.model;

/**
 * info about cellphone system
 *
 * @author afree on 8/11/16.
 */

public class SystemModel {
  private String versionSDK;   // sdk版本  如：16
  private String release;      // 安卓系统版本   如：4.1.1
  private String manufacture;  // 生产商    如：Xiaomi
  private String device;       // 设备信息 如：aries
  private String hardware;     // 硬件信息(CPU) 如：qcom
  private String brand;        // 手机品牌  如：Xiaomi
  private String cpuABI;       // cpu版本  如：armeabi-v7a
  private String cpuABI2;      // cpu品牌  如：armeabi
  private String language;     // 手机语言 如：zh_CN
  private String screenWidthHeight;  // 屏幕宽高 如：768_1024
  private String imei;          // IMEI号

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getCpuABI2() {
    return cpuABI2;
  }

  public void setCpuABI2(String cpuABI2) {
    this.cpuABI2 = cpuABI2;
  }

  public String getCpuABI() {
    return cpuABI;
  }

  public void setCpuABI(String cpuABI) {
    this.cpuABI = cpuABI;
  }

  public String getDevice() {
    return device;
  }

  public void setDevice(String device) {
    this.device = device;
  }

  public String getHardware() {
    return hardware;
  }

  public void setHardware(String hardware) {
    this.hardware = hardware;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getManufacture() {
    return manufacture;
  }

  public void setManufacture(String manufacture) {
    this.manufacture = manufacture;
  }

  public String getRelease() {
    return release;
  }

  public void setRelease(String release) {
    this.release = release;
  }

  public String getScreenWidthHeight() {
    return screenWidthHeight;
  }

  public void setScreenWidthHeight(String screenWidthHeight) {
    this.screenWidthHeight = screenWidthHeight;
  }

  public String getVersionSDK() {
    return versionSDK;
  }

  public void setVersionSDK(String versionSDK) {
    this.versionSDK = versionSDK;
  }

  public String getImei() {
    return imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }
}

