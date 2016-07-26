package com.afree.canary.sample.develop.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.afree.canary.R;
import com.afree.canary.base.BaseController;
import com.afree.canary.base.adapter.BaseRecyclerAdapter;
import com.afree.canary.sample.develop.controller.RecyclerController;
import com.afree.canary.sample.main.model.CommonModel;
import com.afree.canary.utils.ViewUtils;
import com.afree.utils.DimensionPixelUtils;

/**
 * @author afree8909@gmail.com on 7/26/16.
 */
public class RecyclerAdapter extends BaseRecyclerAdapter<CommonModel> {
  @Override
  protected View newView(ViewGroup parent, int type) {
    View view = ViewUtils.newInstance(parent, R.layout.common_card_tv);
    LinearLayout.LayoutParams params =
        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            (int) DimensionPixelUtils.dip2px(parent.getContext(), 80));
    view.setLayoutParams(params);
    return view;
  }

  @Override
  protected BaseController newController(int type) {
    return new RecyclerController();
  }
}
