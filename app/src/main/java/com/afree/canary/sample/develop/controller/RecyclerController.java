package com.afree.canary.sample.develop.controller;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.afree.canary.R;
import com.afree.canary.base.BaseActivity;
import com.afree.canary.base.BaseController;
import com.afree.canary.sample.main.model.CommonModel;

/**
 * @author afree8909@gmail.com on 7/26/16.
 */
public class RecyclerController extends BaseController<View, CommonModel> {
  @Override
  public void bind(View view, final CommonModel commonModel) {
    if (view == null || commonModel == null) {
      return;
    }

    TextView tv = (TextView) view.findViewById(R.id.tv_common_card);
    if (tv != null) {
      tv.setText(commonModel.getName());
      tv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Context context = v.getContext();
          if (context instanceof BaseActivity) {
            Fragment f = Fragment.instantiate(context, commonModel.getFragmentName());
            ((BaseActivity) context).replace(f);
          }
        }
      });
    }
  }
}
