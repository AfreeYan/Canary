package com.afree.canary.sample.design.model;

import java.util.List;

/**
 * @author afree8909@gmail.com on 7/14/16.
 */
public class CityModel {

  public List<City> data;

  public class City {

    private int cityId;
    private String cityName;
    private String cityPinYin;

    public int getCityId() {
      return cityId;
    }

    public String getCityName() {
      return cityName;
    }

    public String getCityPinYin() {
      return cityPinYin;
    }
  }

}
