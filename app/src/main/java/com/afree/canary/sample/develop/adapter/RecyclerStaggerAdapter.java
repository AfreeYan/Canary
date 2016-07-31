package com.afree.canary.sample.develop.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.afree.canary.R;
import com.afree.canary.base.BaseController;
import com.afree.canary.sample.develop.controller.RecyclerItemController;
import com.afree.canary.utils.ViewUtils;
import com.afree.canary.widget.recycler.HeaderAndFooterRecyclerAdapter;
import com.afree.utils.DimensionPixelUtils;
import com.afree.utils.GlobalConfig;

/**
 * @author afree8909@gmail.com on 7/31/16.
 */
public class RecyclerStaggerAdapter extends HeaderAndFooterRecyclerAdapter {

  private int mSmall = DimensionPixelUtils.dip2px(GlobalConfig.getContext(), 180);
  private int mLarge = DimensionPixelUtils.dip2px(GlobalConfig.getContext(), 300);

  @Override
  protected View newView(ViewGroup parent, int type) {
    View view = ViewUtils.newInstance(parent, R.layout.common_card_tv);
    LinearLayout.LayoutParams params =
        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            DimensionPixelUtils.dip2px(parent.getContext(), 80));
    view.setLayoutParams(params);
    return view;
  }

  @Override
  protected BaseController newController(int type) {
    return new RecyclerItemController();
  }

  @Override
  protected void doBind(BaseController controller, View view, Object item, int position) {
    if (view != null && view.getLayoutParams() != null) {
      view.getLayoutParams().height = position % 2 != 0 ? mSmall : mLarge;
    }
    super.doBind(controller, view, item, position);
  }
}
