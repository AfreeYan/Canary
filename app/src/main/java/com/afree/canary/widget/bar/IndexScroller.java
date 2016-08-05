package com.afree.canary.widget.bar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.afree.canary.R;


public class IndexScroller extends View {
  private final int CELL_MAX_HEIGHT;
  private Paint mPaint = new Paint();
  private RectF mRect = new RectF();
  private float mDensity;
  private boolean mShowBg = false;

  private int mCurrentSection = -1;
  private int mIndexBarFontNormalColor;
  private int mIndexBarFontHoverColor;
  private float mIndexBarFontSize;

  private int mIndexbarBgColor;

  private String[] mSections;

  private OnIndexScrollerTouchChangeListenner mListenner;

  public interface OnIndexScrollerTouchChangeListenner {
    public void OnIndexScrollerTouchChanged(boolean isTouched, String letter);
  }

  public void setOnIndexScrollerTouchChangeListenner(
      OnIndexScrollerTouchChangeListenner listenner) {
    this.mListenner = listenner;
  }

  public String[] getSection() {
    return mSections;
  }

  public void setSections(String[] mSections) {
    this.mSections = mSections;
  }

  public IndexScroller(Context context) {
    this(context, null);
  }

  public IndexScroller(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public IndexScroller(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    mDensity = context.getResources().getDisplayMetrics().density;
    float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
    TypedArray a = context.obtainStyledAttributes(attrs,
        R.styleable.IndexScroller, defStyle, 0);
    mIndexBarFontNormalColor = a.getColor(
        R.styleable.IndexScroller_barTextColor, Color.BLACK);
    mIndexBarFontHoverColor = a.getColor(
        R.styleable.IndexScroller_barTextColorHover, Color.GREEN);
    mIndexbarBgColor = a.getColor(
        R.styleable.IndexScroller_barBackgroundColor, Color.GRAY);
    mIndexBarFontSize = a.getDimension(
        R.styleable.IndexScroller_barTextSize, 15 * scaledDensity);
    CharSequence[] entries = a
        .getTextArray(R.styleable.IndexScroller_entries);
    if (entries != null) {
      int length = entries.length;
      mSections = new String[length];
      for (int i = 0; i < length; i++) {
        mSections[i] = entries[i].toString();
      }
    } else {
      mSections = new String[] {"#", "A", "B", "C", "D", "E", "F", "G",
          "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
          "T", "U", "V", "W", "X", "Y", "Z"};
    }
    mPaint.setColor(mIndexBarFontNormalColor);
    mPaint.setTypeface(Typeface.DEFAULT_BOLD);
    mPaint.setAntiAlias(true);
    mPaint.setTextSize(mIndexBarFontSize);
    CELL_MAX_HEIGHT = (int) ((mPaint.descent() - mPaint.ascent()) * 3);
    a.recycle();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    int width = getWidth();
    int height = getHeight();
    int singleHeight = height / mSections.length;
    singleHeight = singleHeight > CELL_MAX_HEIGHT ? CELL_MAX_HEIGHT
        : singleHeight;
    if (mShowBg) {
      mRect.set(0, 0, width, height);
      mPaint.setAntiAlias(true);
      mPaint.setColor(mIndexbarBgColor);
      mPaint.setAlpha(128);
      canvas.drawRoundRect(mRect, 14 * mDensity, 14 * mDensity, mPaint);
    }
    for (int i = 0; i < mSections.length; i++) {
      mPaint.setColor(mIndexBarFontNormalColor);
      mPaint.setTypeface(Typeface.DEFAULT_BOLD);
      mPaint.setAntiAlias(true);
      mPaint.setTextSize(mIndexBarFontSize);
      if (i == mCurrentSection) {
        mPaint.setColor(mIndexBarFontHoverColor);
        mPaint.setFakeBoldText(true);
      }
      float paddingTop = (singleHeight - (mPaint.descent() - mPaint
          .ascent())) / 2;
      float posX = width / 2 - mPaint.measureText(mSections[i]) / 2;
      float posY = i * singleHeight + paddingTop - mPaint.ascent();
      canvas.drawText(mSections[i], posX, posY, mPaint);
      mPaint.reset();
    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    super.onTouchEvent(event);
    final float y = event.getY();
    int singleHeight = getHeight() / mSections.length;
    singleHeight = singleHeight > CELL_MAX_HEIGHT ? CELL_MAX_HEIGHT
        : singleHeight;
    int contentHeight = singleHeight * mSections.length;
    final int index = (int) (y / contentHeight * mSections.length);
    final int oldSection = mCurrentSection;
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        mShowBg = true;
        if (oldSection != index && mListenner != null && index >= 0
            && index < mSections.length) {
          mCurrentSection = index;
          mListenner.OnIndexScrollerTouchChanged(mShowBg,
              mSections[index]);
          invalidate();
        }
        break;

      case MotionEvent.ACTION_MOVE:
        if (oldSection != index && mListenner != null && index >= 0
            && index < mSections.length) {
          mCurrentSection = index;
          mListenner.OnIndexScrollerTouchChanged(mShowBg,
              mSections[index]);
          invalidate();
        }
        break;
      case MotionEvent.ACTION_UP:
        mShowBg = false;
        mCurrentSection = -1;
        if (mListenner != null) {
          if (index <= 0) {
            mListenner.OnIndexScrollerTouchChanged(mShowBg,
                mSections[0]);
          } else if (index > 0 && index < mSections.length) {
            mListenner.OnIndexScrollerTouchChanged(mShowBg,
                mSections[index]);
          } else if (index >= mSections.length) {
            mListenner.OnIndexScrollerTouchChanged(mShowBg,
                mSections[mSections.length - 1]);
          }
        }
        invalidate();
        break;
    }
    return true;
  }

}
