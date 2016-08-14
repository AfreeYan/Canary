package com.afree.ctracker.model;

/**
 * info about cellphone ram
 *
 * @author afree on 8/11/16.
 */
public class RamModel {

  private String dalvikPss;           // Dalvik物理内存
  private String nativePss;           // Native物理内存
  private String otherPss;            // 除dalvik和native外的物理内存
  private String dalvikPrivateDirty;  // Dalvik进程独享物理内存
  private String nativePrivateDirty;  // Native独享物理内存
  private String otherPrivateDirty;   // 除dalvik和native外的独享物理内存
  private String dalvikSharedDirty;   // Dalvik进程共享物理内存
  private String nativeSharedDirty;   // Native进程共享物理内存
  private String otherSharedDirty;    // 除dalvik和native外的共享物理内存

  public String getDalvikPrivateDirty() {
    return dalvikPrivateDirty;
  }

  public void setDalvikPrivateDirty(String dalvikPrivateDirty) {
    this.dalvikPrivateDirty = dalvikPrivateDirty;
  }

  public String getDalvikPss() {
    return dalvikPss;
  }

  public void setDalvikPss(String dalvikPss) {
    this.dalvikPss = dalvikPss;
  }

  public String getDalvikSharedDirty() {
    return dalvikSharedDirty;
  }

  public void setDalvikSharedDirty(String dalvikSharedDirty) {
    this.dalvikSharedDirty = dalvikSharedDirty;
  }

  public String getNativePrivateDirty() {
    return nativePrivateDirty;
  }

  public void setNativePrivateDirty(String nativePrivateDirty) {
    this.nativePrivateDirty = nativePrivateDirty;
  }

  public String getNativePss() {
    return nativePss;
  }

  public void setNativePss(String nativePss) {
    this.nativePss = nativePss;
  }

  public String getNativeSharedDirty() {
    return nativeSharedDirty;
  }

  public void setNativeSharedDirty(String nativeSharedDirty) {
    this.nativeSharedDirty = nativeSharedDirty;
  }

  public String getOtherPrivateDirty() {
    return otherPrivateDirty;
  }

  public void setOtherPrivateDirty(String otherPrivateDirty) {
    this.otherPrivateDirty = otherPrivateDirty;
  }

  public String getOtherPss() {
    return otherPss;
  }

  public void setOtherPss(String otherPss) {
    this.otherPss = otherPss;
  }

  public String getOtherSharedDirty() {
    return otherSharedDirty;
  }

  public void setOtherSharedDirty(String otherSharedDirty) {
    this.otherSharedDirty = otherSharedDirty;
  }
}
