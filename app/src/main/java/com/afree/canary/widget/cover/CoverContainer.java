package com.afree.canary.widget.cover;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.afree.canary.R;
import com.afree.canary.utils.ViewUtils;

import java.util.Stack;

/**
 * @author afree on 4/17/16.
 */
public class CoverContainer extends RelativeLayout {
  private Stack<Cover> mCovers = new Stack<>();
  private ViewGroup.LayoutParams mTargetParams;
  private ViewPager.OnPageChangeListener mPageChangeListener;

  public CoverContainer(Context context) {
    super(context);
  }

  public CoverContainer(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    interceptorKeyEvent();
  }


  /**
   * make this receive key event
   */
  private void interceptorKeyEvent() {
    setFocusable(true);
    setFocusableInTouchMode(true);
    setOnKeyListener(new OnKeyListener() {
      @Override
      public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
          if (mCovers.size() > 0) {
            Cover cover = mCovers.pop();
            if (cover == null || cover.getTargetView() == null) {
              return false;
            }
            if (cover.isCanCancel()) {
              hideCovers(cover.getTargetView(), cover.getType());
              if (cover.getDismissListener() != null) {
                cover.getDismissListener().onDismiss();
              }
            }
            return true;
          }
        }
        return false;
      }
    });
  }

  /**
   * @param target target view to cover
   * @return cover container applied on target
   */
  public static CoverContainer build(View target) {
    ViewGroup parent = (ViewGroup) target.getParent();
    if (parent instanceof CoverContainer) {
      return (CoverContainer) parent;
    }

    return new CoverContainer(target.getContext());
  }

  public Cover showCover(final View target, CoverType type) {

    beforeShow(target, type);

    View coverView = ViewUtils.findChildViewById(this, type.ordinal());
    if (coverView != null) {
      coverView.bringToFront();
      Cover cover = (Cover) coverView.getTag(R.id.tag_key_cover);
      mCovers.remove(cover);
      mCovers.push(cover);
      return cover;
    }

    addTarget(target, type);
    Cover cover = createCover(target, type);
    mCovers.push(cover);

    requestFocus();
    return cover;
  }

  private void addTarget(View target, CoverType type) {
    LayoutParams targetParams = new LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    if (indexOfChild(target) == -1) {
      addView(target, targetParams);
    } else {
      target.setLayoutParams(targetParams);
    }

    if (type.mHideTarget) {
      target.setVisibility(View.INVISIBLE);
    }
  }

  private Cover createCover(View target, CoverType type) {
    View coverView = LayoutInflater.from(getContext()).inflate(type.mLayoutResId, this, false);
    coverView.setId(type.ordinal());
    ViewGroup.LayoutParams p = coverView.getLayoutParams();
    LayoutParams params;
    if (p instanceof LayoutParams) {
      params = (LayoutParams) p;
    } else {
      params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }
    addView(coverView, params);
    Cover cover = new Cover(coverView, target, type);
    coverView.setTag(R.id.tag_key_cover, cover);
    return cover;
  }

  public void hideCovers(View target, CoverType... types) {
    if (target == null || types.length == 0) {
      return;
    }

    for (CoverType t : types) {
      if (t == null) {
        continue;
      }
      removeCover(t);
    }

    if (getChildCount() == 1) {
      removeCoverContainer(target);
    }
  }

  /**
   * do something at here , before cover show
   */
  private void beforeShow(View target, CoverType type) {
    initTargetParams(target);
    replaceTarget(target);
  }

  private void initTargetParams(View target) {
    if (mTargetParams == null) {
      mTargetParams = target.getLayoutParams();
    }
  }

  private void replaceTarget(View target) {
    ViewGroup parent = (ViewGroup) target.getParent();
    if (parent instanceof CoverContainer) {
      return;
    }

    int index = parent.indexOfChild(target);
    ViewGroup.LayoutParams params;
    if (parent instanceof ViewPager) {
      params = compatViewPager((ViewPager) parent);
    } else {
      params = target.getLayoutParams();
    }

    parent.removeViewAt(index);
    parent.addView(this, index, params);
  }

  private ViewGroup.LayoutParams compatViewPager(final ViewPager parent) {
    ViewGroup.LayoutParams params;
    params = new ViewPager.LayoutParams();
    ((ViewPager.LayoutParams) params).isDecor = true;
    ((ViewPager.LayoutParams) params).gravity = Gravity.CENTER;
    mPageChangeListener = new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        if (mCovers.size() > 0) {
          for (Cover t : mCovers) {
            if (t != null) {
              hideCovers(t.getTargetView(), t.getType());
            }
          }
        }
        parent.removeOnPageChangeListener(mPageChangeListener);
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    };
    parent.addOnPageChangeListener(mPageChangeListener);

    return params;
  }

  private void removeCoverContainer(View target) {
    ViewGroup parent = (ViewGroup) getParent();
    int index = parent.indexOfChild(this);
    parent.removeViewAt(index);
    if (target.getParent() != null) {
      ((ViewGroup) target.getParent()).removeView(target);
    }

    parent.addView(target, index, mTargetParams);
    target.setVisibility(View.VISIBLE);
  }

  private void removeCover(CoverType t) {
    View coverView = findViewById(t.ordinal());
    if (coverView != null) {
      Cover cover = (Cover) coverView.getTag(R.id.tag_key_cover);
      mCovers.remove(cover);
      removeView(coverView);
    }
  }

}
