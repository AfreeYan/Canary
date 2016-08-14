package com.afree.canary.sample.develop.controller;

import android.view.View;
import android.widget.TextView;

import com.afree.canary.R;
import com.afree.canary.base.BaseController;
import com.afree.canary.sample.main.model.CommonModel;

/**
 * @author afree8909@gmail.com on 7/28/16.
 */
public class RecyclerItemController extends BaseController<View, CommonModel> {
  @Override
  public void bind(View view, CommonModel model) {
    if (model == null) {
      return;
    }
    TextView tv = (TextView) view.findViewById(R.id.tv_common_card);
    if (tv != null) {
      tv = null;
      tv.setText(model.getName());
    }
  }
}
