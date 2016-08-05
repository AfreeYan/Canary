package com.afree.canary.widget.bar;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.afree.canary.R;

/**
 * @author afree8909@gmail.com on 7/8/16.
 */
public class SideBar extends View {

  private static final float TEXT_SIZE_SCALE = 0.7f;
  private static final int INVALID_VALUE = -1;
  private static final int ALPHA_MAX = 255;
  private static final int ALPHA_MIN = 50;
  private final int mScaledTouchSlop;
  private final float mDensity;
  private Paint mPaint;
  private int mTextColor = Color.GRAY;
  private int mTextSize;

  private CharSequence[] mEntries; // data
  private int mActivityPointerId = INVALID_VALUE;

  private RectF mValidRect = new RectF();
  private int mTransitionItemCount = 4;
  private int mCurrentIndex;
  private float mActionDownY;
  private float mCurrentY;
  private float mWidth;
  private float mItemHeight;
  private boolean mIsMoving;
  private boolean mIsEventOver;

  private OnItemSelectedListener mListener;


  public SideBar(Context context) {
    this(context, null);
  }

  public SideBar(Context context, AttributeSet attrs) {
    super(context, attrs);

    mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    mDensity = getContext().getResources().getDisplayMetrics().density;

    mPaint = new Paint();
    mPaint.setAntiAlias(true);
    mPaint.setTextAlign(Paint.Align.CENTER);
    mPaint.setColor(mTextColor);

    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SideBar);
    mEntries = a.getTextArray(R.styleable.SideBar_entries);
    a.recycle();
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mWidth = w - getPaddingLeft() - getPaddingRight();
    float height = h - getPaddingTop() - getPaddingBottom();

    int entriesLength = getEntriesLength();
    if (entriesLength == 0) {
      return;
    }
    mItemHeight = height / entriesLength;
    mTextSize = (int) (mItemHeight * TEXT_SIZE_SCALE);
    mPaint.setTextSize(mTextSize);

    mValidRect.set(w - (2 * mTextSize), 0, w + mTextSize, h);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    int action = MotionEventCompat.getActionMasked(event);
    switch (action) {
      case MotionEvent.ACTION_DOWN:
        mActivityPointerId = MotionEventCompat.getPointerId(event, 0);
        mIsMoving = false;
        mActionDownY = getMotionEventY(event, mActivityPointerId);
        if (mActionDownY == INVALID_VALUE) {
          return false;
        }
        if (!mValidRect.contains(event.getX(), event.getY())) {
          return false;
        }
        break;
      case MotionEvent.ACTION_MOVE:
        if (mActivityPointerId == INVALID_VALUE) {
          return false;
        }
        float y = getMotionEventY(event, mActivityPointerId);
        if (y == INVALID_VALUE) {
          return false;
        }
        float dY = Math.abs(y - mActionDownY);
        if (dY > mScaledTouchSlop && !mIsMoving) {
          mIsMoving = true;
        }
        if (mIsMoving) {
          mCurrentY = y;

          int index = (int) ((y - getPaddingTop()) / mItemHeight);
          if (mCurrentIndex != index) {
            if (index >= 0 && index < getEntriesLength()) {
              mCurrentIndex = index;
              notifySeletesChanged();
            }
          }
          invalidate();
        }
        break;
      case MotionEventCompat.ACTION_POINTER_UP:
        onPointerChanged(event);
        break;
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_CANCEL:
        notifySeletesChanged();

        mIsEventOver = mIsMoving;
        mIsMoving = false;

        mActivityPointerId = INVALID_VALUE;
        mCurrentIndex = INVALID_VALUE;
        invalidate();
        return false;
    }

    return true;
  }

  private void notifySeletesChanged() {
    Log.d("test","index="+mCurrentIndex+";char="+mEntries[mCurrentIndex]);
    if (mListener != null) {
      if (mCurrentIndex >= 0 && mCurrentIndex < getEntriesLength()) {
        mListener.onItemSelected(mCurrentIndex, String.valueOf(mEntries[mCurrentIndex]));
      }
    }
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    float itemY;
    float dY;
    float dYFactor; // distance from current Y position to item position
    float dX; // transition distance of x for each item
    int alpha;


    for (int i = 0; i < getEntriesLength(); i++) {
      itemY = mItemHeight * i + getPaddingTop();
      dY = Math.abs(mCurrentY - itemY);
      dYFactor = dY / mItemHeight;


      if (mIsEventOver || !mIsMoving) { // set to initial status
        alpha = ALPHA_MAX;
        dX = 0;
        mPaint.setTextSize((float) (mTextSize));
        mPaint.setTypeface(Typeface.DEFAULT);
      } else {
        if (dYFactor <= mTransitionItemCount) {
          alpha = Math.max(ALPHA_MIN, (int) (ALPHA_MAX - dY));
          dX = mTransitionItemCount * mItemHeight - dY;


          mPaint.setTextSize((float) (1.5 * mTextSize));
          mPaint.setTypeface(Typeface.DEFAULT_BOLD);

        } else {// set to initial status
          alpha = ALPHA_MAX;
          dX = 0;
          mPaint.setTextSize((float) (mTextSize));
          mPaint.setTypeface(Typeface.DEFAULT);
        }
      }

      canvas.save();
      canvas.translate(-dX, 0);
      mPaint.setAlpha(alpha);

      CharSequence c = mEntries[i];
      canvas.drawText(String.valueOf(c), mWidth - mTextSize, itemY + mItemHeight, mPaint);
      canvas.restore();

    }
    if (mCurrentIndex == INVALID_VALUE && mIsEventOver) {
      mIsEventOver = false;
      postInvalidateDelayed(100);
    }
  }

  public CharSequence[] getEntries() {
    return mEntries;
  }

  public void setEntries(CharSequence[] entries) {
    mEntries = entries;
    invalidate();
  }

  public void setTextColor(int textColor) {
    mTextColor = textColor;
    invalidate();
  }

  public void setTransitionItemCount(int transitionItemCount) {
    if (transitionItemCount < 1 || transitionItemCount > 7) {
      return;
    }
    mTransitionItemCount = transitionItemCount;
  }

  private int getEntriesLength() {
    return mEntries == null ? 0 : mEntries.length;
  }

  private int dip2px(int dipValue) {
    return (int) (dipValue * mDensity + 0.5f);
  }

  private float getMotionEventY(MotionEvent ev, int activePointerId) {
    final int index = MotionEventCompat.findPointerIndex(ev, activePointerId);
    if (index < 0) {
      return INVALID_VALUE;
    }
    return MotionEventCompat.getY(ev, index);
  }

  private void onPointerChanged(MotionEvent ev) {
    final int pointerIndex = MotionEventCompat.getActionIndex(ev);
    final int pointerId = MotionEventCompat.getPointerId(ev, pointerIndex);
    if (pointerId == mActivityPointerId) {
      int newPointerIndex = pointerIndex == 0 ? 1 : 0;
      mActivityPointerId = MotionEventCompat.getPointerId(ev, newPointerIndex);
    }
  }

  public interface OnItemSelectedListener {
    void onItemSelected(int position, String data);
  }

  public void setListener(OnItemSelectedListener listener) {
    mListener = listener;
  }
}
