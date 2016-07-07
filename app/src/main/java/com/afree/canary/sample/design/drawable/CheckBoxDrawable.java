package com.afree.canary.sample.design.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import com.afree.canary.R;
import com.afree.canary.utils.ViewUtils;
import com.afree.utils.DimensionPixelUtils;
import com.afree.utils.GlobalConfig;

/**
 * @author afree8909@gmail.com on 7/5/16.
 */
public class CheckBoxDrawable extends Drawable {

  private Paint mPaint;
  private boolean mChecked = false;
  private ColorStateList mStrokeColorList;
  private int mCurrentColor;
  private int mTickColor = GlobalConfig.getContext().getResources().getColor(R.color.blue_600);
  private int mAnimDuration = 400;
  private int mWidth = (int) DimensionPixelUtils.dip2px(GlobalConfig.getContext(), 32);
  private int mHeight = (int) DimensionPixelUtils.dip2px(GlobalConfig.getContext(), 32);
  private int mCornerRadius = (int) DimensionPixelUtils.dip2px(GlobalConfig.getContext(), 32);
  private int mStrokeSize = (int) DimensionPixelUtils.dip2px(GlobalConfig.getContext(), 2);
  private int mBoxSize;
  private RectF mBoxRect;
  private Path mTickPath;

  private float mTickPathProgress = -1f;
  private static final float[] TICK_DATA = new float[] {0f, 0.473f, 0.367f, 0.839f, 1f, 0.207f};


  private void initInternal() {
    mPaint = new Paint();
    mPaint.setAntiAlias(true);

    mBoxRect = new RectF();
    mTickPath = new Path();
  }

  public CheckBoxDrawable() {
    initInternal();
  }

  @Override
  public void draw(Canvas canvas) {
    if (mChecked) {
      drawChecked(canvas);
    } else {
      drawUnChecked(canvas);
    }
  }

  private void drawChecked(Canvas canvas) {
    float size = mBoxSize - mStrokeSize * 2;
    float x = mBoxRect.left + mStrokeSize;
    float y = mBoxRect.top + mStrokeSize;

    mPaint.setColor(mCurrentColor);
    mPaint.setStrokeWidth(mStrokeSize);
    mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    canvas.drawRoundRect(mBoxRect, mCornerRadius, mCornerRadius, mPaint);

    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeJoin(Paint.Join.MITER);
    mPaint.setStrokeCap(Paint.Cap.BUTT);
    mPaint.setColor(mTickColor);

    canvas.drawPath(getTickPath(mTickPath, x, y, size, 1f, true), mPaint);
  }

  private void drawUnChecked(Canvas canvas) {
    mPaint.setColor(mCurrentColor);
    mPaint.setStrokeWidth(mStrokeSize);
    mPaint.setStyle(Paint.Style.STROKE);

    canvas.drawRoundRect(mBoxRect, mCornerRadius, mCornerRadius, mPaint);
  }

  @Override
  protected boolean onStateChange(int[] state) {
    boolean checked = ViewUtils.isContainState(state, android.R.attr.state_checked);
    int color = mStrokeColorList.getColorForState(state, mCurrentColor);
    boolean needRedraw = false;

    if (mChecked != checked) {
      mChecked = checked;
      needRedraw = true;


    }

    if (mCurrentColor != color) {
      mCurrentColor = color;
      needRedraw = true;
    }

    return needRedraw;
  }

  @Override
  protected void onBoundsChange(Rect bounds) {
    mBoxRect.set(bounds.exactCenterX() - mBoxSize / 2, bounds.exactCenterY() - mBoxSize / 2,
        bounds.exactCenterX() + mBoxSize / 2, bounds.exactCenterY() + mBoxSize / 2);
  }

  @Override
  public void setAlpha(int alpha) {
    mPaint.setAlpha(alpha);
  }

  @Override
  public void setColorFilter(ColorFilter colorFilter) {
    mPaint.setColorFilter(colorFilter);
  }

  @Override
  public int getOpacity() {
    return 0;
  }

  private Path getTickPath(Path path, float x, float y, float size, float progress, boolean in) {
    if (mTickPathProgress == progress) {
      return path;
    }
    mTickPathProgress = progress;

    float x1 = x + size * TICK_DATA[0];
    float y1 = y + size * TICK_DATA[1];
    float x2 = x + size * TICK_DATA[2];
    float y2 = y + size * TICK_DATA[3];
    float x3 = x + size * TICK_DATA[4];
    float y3 = y + size * TICK_DATA[5];

    float d1 = (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    float d2 = (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    float midProgress = d1 / (d1 + d2);

    path.reset();
    if (in) {
      path.moveTo(x1, y1);

      if (progress < midProgress) {
        progress = progress / midProgress;
        path.lineTo(x1 * (1 - progress) + x2 * progress, y1 * (1 - progress) + y2 * progress);
      }
      else {
        progress = (progress - midProgress) / (1f - midProgress);
        path.lineTo(x2, y2);
        path.lineTo(x2 * (1 - progress) + x3 * progress, y2 * (1 - progress) + y3 * progress);
      }
    } else {
      path.moveTo(x3, y3);

      if (progress < midProgress) {
        progress = progress / midProgress;
        path.lineTo(x2, y2);
        path.lineTo(x1 * (1 - progress) + x2 * progress, y1 * (1 - progress) + y2 * progress);
      }
      else {
        progress = (progress - midProgress) / (1f - midProgress);
        path.lineTo(x2 * (1 - progress) + x3 * progress, y2 * (1 - progress) + y3 * progress);
      }
    }

    return path;

  }
}
