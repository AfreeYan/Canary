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
public class RadioBar extends LinearLayout {
  private View.OnClickListener mChildOnClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      performChildClick(v);
    }
  };
  private OnSelectedChangeListener mOnSelectedChangeListener;

  private int mSelectedPosition = -1;

  public RadioBar(Context context) {
    super(context);
  }

  public RadioBar(Context context, AttributeSet attrs) {
    super(context, attrs);

  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    setChecked(0);
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
    View oldSelected = getChildAt(mSelectedPosition);
    if (oldSelected == v) {
      return;
    }
    if (oldSelected != null) {
      oldSelected.setSelected(false);
    }
    int childCount = getChildCount();
    for (int i = 0; i < childCount; i++) {
      if (v == getChildAt(i)) {
        v.setSelected(true);
        mSelectedPosition = i;
        if (mOnSelectedChangeListener != null) {
          mOnSelectedChangeListener.onSelectedChanged(v, i);
        }
        break;
      }
    }
  }

  public interface OnSelectedChangeListener {
    void onSelectedChanged(View child, int position);

  }

  public void setOnSelectedListener(OnSelectedChangeListener listener) {
    mOnSelectedChangeListener = listener;
  }

}
