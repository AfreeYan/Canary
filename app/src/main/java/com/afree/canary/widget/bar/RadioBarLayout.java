package com.afree.canary.widget.bar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * This class is used to create a multiple-exclusion scope for a set of radio views.
 * the weight of each child view is same .
 * 
 * @author afree8909@gmail.com on 6/14/16.
 */
public class RadioBarLayout extends LinearLayout {
  private final static int VALID_POSITION = -1;
  private View.OnClickListener mChildOnClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      performChildClick(v);
    }
  };
  private OnSelectedChangeListener mOnSelectedChangeListener;

  private int mSelectedPosition = VALID_POSITION;

  public RadioBarLayout(Context context) {
    super(context);
  }

  public RadioBarLayout(Context context, AttributeSet attrs) {
    super(context, attrs);

  }

  @Override
  public void addView(View child, int index, ViewGroup.LayoutParams params) {
    if (params instanceof LinearLayout.LayoutParams) {
      ((LayoutParams) params).weight = 1;
    }
    if (child != null) {
      child.setOnClickListener(mChildOnClickListener);
    }
    super.addView(child, index, params);
  }

  public void setChecked(int position) {
    if (getChildCount() > position) {
      performChildClick(getChildAt(position));
    }
  }

  private void performChildClick(View v) {
    if (v == null) {
      return;
    }
    int childCount = getChildCount();
    View child;
    for (int i = 0; i < childCount; i++) {
      child = getChildAt(i);
      if (v == child) {
        if (i == mSelectedPosition) {
          break;
        }
        v.setSelected(true);
        if (mOnSelectedChangeListener != null) {
          mOnSelectedChangeListener.onSelectedChanged(v, i);
        }
        mSelectedPosition = i;
      } else {
        if (child.isSelected()) {
          child.setSelected(false);
        }
      }
    }
  }

  public interface OnSelectedChangeListener {
    void onSelectedChanged(View child, int curPos);

  }

  public void setOnSelectedListener(OnSelectedChangeListener listener) {
    mOnSelectedChangeListener = listener;
  }

}
