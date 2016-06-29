package com.afree.canary.sample.design.model;

/**
 * @author afree8909@gmail.com on 6/29/16.
 */
public class DesignModel {

  private String mName;

  private String mFragmentName;

  public DesignModel(String name, String fragmentName) {
    mName = name;
    mFragmentName = fragmentName;
  }

  public String getFragmentName() {
    return mFragmentName;
  }

  public void setFragmentName(String fragmentName) {
    mFragmentName = fragmentName;
  }

  public String getName() {
    return mName;
  }

  public void setName(String name) {
    mName = name;
  }
}
