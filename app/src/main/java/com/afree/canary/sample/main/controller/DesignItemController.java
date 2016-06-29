package com.afree.canary.sample.main.controller;

import android.view.View;

import com.afree.canary.base.BaseController;
import com.afree.canary.sample.design.DesignActivity;
import com.afree.canary.sample.design.model.DesignModel;
import com.afree.canary.sample.main.view.DesignItemContainer;

/**
 * @author afree8909@gmail.com on 6/23/16.
 */
public class DesignItemController extends BaseController<DesignItemContainer, DesignModel> {
  @Override
  public void bind(DesignItemContainer view, final DesignModel model) {
    if (model == null) {
      return;
    }
    view.getTvName().setText(model.getName());

    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        DesignActivity.launch(v.getContext(),model.getFragmentName());
      }
    });
  }
}
