package com.afree.canary.sample.main.controller;

import com.afree.canary.base.BaseController;
import com.afree.canary.sample.main.view.DesignItemContainer;

/**
 * @author afree8909@gmail.com on 6/23/16.
 */
public class DesignItemController extends BaseController<DesignItemContainer, String> {
  @Override
  public void bind(DesignItemContainer view, String s) {
    view.getTvName().setText(s);
  }
}
