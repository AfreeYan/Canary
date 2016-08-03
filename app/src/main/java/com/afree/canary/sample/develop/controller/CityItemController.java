package com.afree.canary.sample.develop.controller;

import com.afree.canary.base.BaseController;
import com.afree.canary.sample.develop.fragment.CityItemModel;
import com.afree.canary.sample.develop.view.CityItemView;

/**
 * @author afree8909@gmail.com on 8/2/16.
 */
public class CityItemController extends BaseController<CityItemView, CityItemModel> {
  @Override
  public void bind(CityItemView view, CityItemModel m) {
    if (m != null) {
      view.setText(m.getCityName());
    }
  }
}
