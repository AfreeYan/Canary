package com.afree.ctracker.model;

/**
 * info about cellphone rom
 *
 * @author afree on 8/11/16.
 */

public class RomModel {
  private String privateMem;       // 系统存储空间大小(KB)
  private String availPrivateMem;  // 可用的系统存储空间大小(KB)
  private String externalMem;      // 外部存储大小(KB)
  private String availExternalMem; // 可用的外部存储大小(KB)

  public String getAvailExternalMem() {
    return availExternalMem;
  }

  public void setAvailExternalMem(String availExternalMem) {
    this.availExternalMem = availExternalMem;
  }

  public String getAvailPrivateMem() {
    return availPrivateMem;
  }

  public void setAvailPrivateMem(String availPrivateMem) {
    this.availPrivateMem = availPrivateMem;
  }

  public String getExternalMem() {
    return externalMem;
  }

  public void setExternalMem(String externalMem) {
    this.externalMem = externalMem;
  }

  public String getPrivateMem() {
    return privateMem;
  }

  public void setPrivateMem(String privateMem) {
    this.privateMem = privateMem;
  }
}
