package com.afree.canary.widget.recycler;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author afree8909@gmail.com on 7/31/16.
 */
public class ItemDividerDecoration extends RecyclerView.ItemDecoration {
  private boolean mIsHorizontal = true;
  private Drawable mDrawable;
  private Paint mPaint;

  public ItemDividerDecoration() {
    mPaint = new Paint();
    mPaint.setStrokeWidth(5);
    mPaint.setColor(Color.BLUE);
    mPaint.setAntiAlias(true);
    mPaint.setPathEffect(new DashPathEffect(new float[] {25.0f, 25.0f}, 0));
  }

  @Override
  public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    super.onDraw(c, parent, state);

    int childCount = parent.getChildCount();
    int lastChildPosition = -1;
    for (int i = 0; i < childCount; i++) {
      View child = parent.getChildAt(i);
      int childPos = parent.getChildAdapterPosition(child);
      if (childPos < lastChildPosition) {
        continue;
      }
      lastChildPosition = childPos;

      int horizontalPosition = getHorizontalPosition(childPos, parent);

      Rect bounds = getDividerBounds(childPos, horizontalPosition, parent, child);

      draw(c, bounds);
    }
  }

  private void draw(Canvas c, Rect bounds) {
    if (bounds == null || c == null) {
      return;
    }

    if (mDrawable != null) {
      mDrawable.setBounds(bounds);
      mDrawable.draw(c);
    } else {
      c.drawLine(bounds.left, bounds.top, bounds.right, bounds.bottom, mPaint);

    }
  }

  private Rect getDividerBounds(int childPos, int horizontalPosition, RecyclerView parent,
      View child) {
    Rect bounds = new Rect();
    if (mIsHorizontal) {

      int tX = (int) ViewCompat.getTranslationX(child);
      int tY = (int) ViewCompat.getTranslationY(child);

      RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

      bounds.left = parent.getPaddingLeft() + tX;
      bounds.right = parent.getWidth() - parent.getPaddingLeft() + tX;

      int dividerSize = getDividerSize(childPos, horizontalPosition, params);

      bounds.top = child.getBottom() + params.bottomMargin + tY;
      bounds.bottom = bounds.top + dividerSize;


    }

    return bounds;
  }

  private int getDividerSize(int childPos, int horizontalPosition, RecyclerView.LayoutParams params) {
    int size;
    if (mDrawable != null) {
      size = mDrawable.getIntrinsicHeight();

    } else {
      size = (int) mPaint.getStrokeWidth();
    }
    return size;
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

    int position = parent.getChildAdapterPosition(view);

    int horizontalPosition = getHorizontalPosition(position, parent);

    fillItemOffset(outRect, position, horizontalPosition, parent);
  }

  private void fillItemOffset(Rect outRect, int position, int horizontalPosition,
      RecyclerView parent) {


  }

  /**
   * horizontal position compatible with gridLayout
   */
  private int getHorizontalPosition(int position, RecyclerView parent) {
    if (parent.getLayoutManager() instanceof GridLayoutManager) {
      GridLayoutManager lm = (GridLayoutManager) parent.getLayoutManager();
      GridLayoutManager.SpanSizeLookup spanSizeLookup = lm.getSpanSizeLookup();
      if (spanSizeLookup != null) {
        return spanSizeLookup.getSpanGroupIndex(position, lm.getSpanCount());
      }
    }
    return position;
  }
}
