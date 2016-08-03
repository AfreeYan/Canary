package com.afree.canary.sample.develop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afree.canary.R;
import com.afree.canary.utils.ViewUtils;


/**
 * 顶头view-字母头view
 * @author afree8909@gmail.com on 8/2/16.
 */
public class LetterStickyHeaderView extends LinearLayout {
  private TextView mHeaderText;

  public LetterStickyHeaderView(Context context) {
    super(context);
  }

  public LetterStickyHeaderView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public static LetterStickyHeaderView newInstance(ViewGroup parent) {
    return (LetterStickyHeaderView) ViewUtils.newInstance(parent,
        R.layout.common_letter_stick_header);
  }

  public static LetterStickyHeaderView newInstance(Context context) {
    return (LetterStickyHeaderView) ViewUtils.newInstance(context,
        R.layout.common_letter_stick_header);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    initView();
  }

  private void initView() {
    mHeaderText = (TextView) findViewById(R.id.tv_letter_stick_header);
  }

  public TextView getHeaderText() {
    return mHeaderText;
  }

}
