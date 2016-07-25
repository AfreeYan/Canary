package com.afree.canary.sample.main.controller;

import com.afree.canary.base.BaseController;
import com.afree.canary.sample.main.view.DesignItemContainer;

/**
 * @author afree8909@gmail.com on 7/25/16.
 */
public class HeaderAndFooterTestController extends BaseController<DesignItemContainer, String> {
  @Override
  public void bind(DesignItemContainer view, String s) {
    view.getTvName().setText("this is header or footer view");
  }
}
