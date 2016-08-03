package com.afree.canary.sample.develop.controller;

import com.afree.canary.base.BaseController;
import com.afree.canary.sample.develop.view.LetterStickyHeaderView;

/**
 * @author afree8909@gmail.com on 8/2/16.
 */
public class LetterStickyController extends BaseController<LetterStickyHeaderView,String> {
  @Override
  public void bind(LetterStickyHeaderView view, String s) {
    view.getHeaderText().setText(s);
  }
}
