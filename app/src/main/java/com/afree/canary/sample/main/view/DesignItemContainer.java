package com.afree.canary.sample.main.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afree.canary.R;

/**
 * @author afree8909@gmail.com on 6/23/16.
 */
public class DesignItemContainer extends LinearLayout {
  private TextView mTvName;
  public DesignItemContainer(Context context) {
    super(context);
  }

  public DesignItemContainer(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public static DesignItemContainer newInstance(ViewGroup parent) {
    return (DesignItemContainer) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.main_design_item, parent, false);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    mTvName = (TextView) findViewById(R.id.tv_name_item_design);
  }

  public TextView getTvName() {
    return mTvName;
  }
}
