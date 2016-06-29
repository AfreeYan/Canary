package com.afree.canary.sample.main.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.afree.canary.base.BaseController;
import com.afree.canary.base.adapter.BaseRecyclerAdapter;
import com.afree.canary.sample.main.controller.StringListController;
import com.afree.canary.sample.main.view.DesignItemContainer;

/**
 * @author afree8909@gmail.com on 6/29/16.
 */
public class StringListAdapter extends BaseRecyclerAdapter<String> {
  @Override
  protected View newView(ViewGroup parent, int type) {
    return DesignItemContainer.newInstance(parent);
  }

  @Override
  protected BaseController newController(int type) {
    return new StringListController();
  }
}
