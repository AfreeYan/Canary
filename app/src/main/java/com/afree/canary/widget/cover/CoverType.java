package com.afree.canary.widget.cover;


import com.afree.canary.R;

/**
 * containing different covers
 *
 * @author afree on 4/6/16.
 */
public enum CoverType {

  Loading_Default(R.layout.cover_loading_default);

  /**
   * layout id
   */
  int mLayoutResId;
  /**
   * target view will be invisible when true
   */
  boolean mHideTarget;
  /**
   * no response to back key event when false
   */
  boolean mCanCancel;

  CoverType(int layoutResId) {
    this(layoutResId, true, false);
  }

  CoverType(int layoutResId, boolean hideTarget) {
    this(layoutResId, hideTarget, false);
  }

  CoverType(int layoutResId, boolean hideTarget, boolean canCancel) {
    mLayoutResId = layoutResId;
    mHideTarget = hideTarget;
    mCanCancel = canCancel;
  }

}
