package com.afree.canary.sample.develop.fragment;

import android.text.TextUtils;

/**
 * @author afree8909@gmail.com on 8/2/16.
 */
public class CityItemModel {
  private static final char UNEXPECT_CHAR = '*';

  private String cityId;
  private String cityName;
  private String cityPinYin;

  public static CityItemModel getFakeCity(String cityName) {
    CityItemModel city = new CityItemModel();
    city.setCityName(cityName);
    return city;
  }

  public String getCityId() {
    return cityId;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getCityPinYin() {
    return cityPinYin;
  }

  public char getFirstLetter() {
    if (TextUtils.isEmpty(cityPinYin)) {
      return UNEXPECT_CHAR;
    }
    return cityPinYin.toUpperCase().charAt(0);
  }
}
