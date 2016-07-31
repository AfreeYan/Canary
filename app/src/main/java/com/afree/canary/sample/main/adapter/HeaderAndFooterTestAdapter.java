package com.afree.canary.sample.main.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.afree.canary.base.BaseController;
import com.afree.canary.sample.main.controller.HeaderAndFooterTestController;
import com.afree.canary.sample.main.controller.StringListController;
import com.afree.canary.sample.main.view.DesignItemContainer;
import com.afree.canary.widget.recycler.HeaderAndFooterRecyclerAdapter;

/**
 * @author afree8909@gmail.com on 7/25/16.
 */
public class HeaderAndFooterTestAdapter extends HeaderAndFooterRecyclerAdapter<String> {
  @Override
  protected View newView(ViewGroup parent, int type) {
    return DesignItemContainer.newInstance(parent);
  }

  @Override
  protected BaseController newController(int type) {
    if (type == ITEM_VIEW_TYPE_HEADER || type == ITEM_VIEW_TYPE_FOOTER) {
      return new HeaderAndFooterTestController();
    }
    return new StringListController();
  }

}
