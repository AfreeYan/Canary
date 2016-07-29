package com.afree.canary.widget.decoration;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afree.canary.R;
import com.afree.utils.DimensionPixelUtils;

/**
 * common load more footer view
 * 
 * @author afree8909@gmail.com on 7/28/16.
 */
public class LoadFooterView extends RelativeLayout {
  private ProgressBar mProgressBar;
  private TextView mTvDes;

  public LoadFooterView(Context context) {
    super(context);
    init(context);
  }

  public LoadFooterView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  private void init(Context context) {
    inflate(context, R.layout.loading_view_default, this);
    setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        DimensionPixelUtils.dip2px(getContext(), 48)));
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    mProgressBar = (ProgressBar) findViewById(R.id.pb_loading_default);
    mTvDes = (TextView) findViewById(R.id.tv_loading_default);
  }

}
