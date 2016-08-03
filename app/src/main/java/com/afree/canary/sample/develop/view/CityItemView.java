package com.afree.canary.sample.develop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afree.canary.R;
import com.afree.canary.utils.ViewUtils;


/**
 * 顶头view-城市选择项view
 * 
 * @author afree8909@gmail.com on 8/2/16.
 */
public class CityItemView extends TextView {

  public CityItemView(Context context) {
    super(context);
  }

  public CityItemView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public static CityItemView newInstance(ViewGroup parent) {
    return (CityItemView) ViewUtils.newInstance(parent,
        R.layout.common_item_city);
  }

  public static CityItemView newInstance(Context context) {
    return (CityItemView) ViewUtils
        .newInstance(context, R.layout.common_item_city);
  }

}
