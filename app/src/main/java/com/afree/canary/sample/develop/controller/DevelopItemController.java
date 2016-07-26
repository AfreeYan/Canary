package com.afree.canary.sample.develop.controller;

import android.view.View;

import com.afree.canary.base.BaseController;
import com.afree.canary.sample.develop.DevelopActivity;
import com.afree.canary.sample.main.model.CommonModel;
import com.afree.canary.sample.main.view.DesignItemContainer;

/**
 * @author afree8909@gmail.com on 6/23/16.
 */
public class DevelopItemController extends BaseController<DesignItemContainer, CommonModel> {
  @Override
  public void bind(DesignItemContainer view, final CommonModel model) {
    if (model == null) {
      return;
    }
    view.getTvName().setText(model.getName());

    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        DevelopActivity.launch(v.getContext(), model.getFragmentName());
      }
    });
  }
}
