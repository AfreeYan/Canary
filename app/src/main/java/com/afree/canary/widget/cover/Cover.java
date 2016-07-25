package com.afree.canary.widget.cover;

import android.view.View;

/**
 * @author afree on 4/17/16.
 */
public class Cover {
  private OnDismissListener mDismissListener;
  private final View mCoverView;
  private final CoverType mType;
  private final View mTargetView;

  public Cover(View coverView, View targetView, CoverType type) {
    mCoverView = coverView;
    mType = type;
    mTargetView = targetView;
  }

  public interface OnDismissListener {
    void onDismiss();
  }

  public void setDismissListener(OnDismissListener dismissListener) {
    mDismissListener = dismissListener;
  }

  public View getCoverView() {
    return mCoverView;
  }

  public boolean isCanCancel() {
    return mType.mCanCancel;
  }

  public View getTargetView() {
    return mTargetView;
  }

  OnDismissListener getDismissListener() {
    return mDismissListener;
  }

  public CoverType getType() {
    return mType;
  }
}
