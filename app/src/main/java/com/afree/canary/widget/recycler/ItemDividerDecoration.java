package com.afree.canary.widget.recycler;

import android.graphics.Canvas;
import android.graphics.Color;
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
    init(null, 1, Color.GRAY);
  }

  public ItemDividerDecoration(float width, int color) {
    init(null, width, color);
  }

  public ItemDividerDecoration(Drawable drawable) {
    init(drawable, 0, 0);
  }

  private void init(Drawable drawable, float width, int color) {
    if (drawable != null) {
      mDrawable = drawable;
    } else {
      mPaint = new Paint();
      mPaint.setStrokeWidth(width);
      mPaint.setColor(color);
      mPaint.setAntiAlias(true);
    }
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

      bounds.top = child.getBottom() + params.bottomMargin + tY;
      if (mDrawable != null) {
        int dividerSize = getDividerSize(childPos, horizontalPosition, parent);
        bounds.bottom = bounds.top + dividerSize;
      } else {
        bounds.bottom = bounds.top;
      }

    } else {
      int tX = (int) ViewCompat.getTranslationX(child);
      int tY = (int) ViewCompat.getTranslationY(child);

      RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

      bounds.top = parent.getPaddingTop() + tX;
      bounds.bottom = parent.getHeight() - parent.getPaddingBottom() + tX;

      bounds.left = child.getLeft() + params.leftMargin + tY;
      if (mDrawable != null) {
        int dividerSize = getDividerSize(childPos, horizontalPosition, parent);
        bounds.right = bounds.left + dividerSize;
      } else {
        bounds.right = bounds.left;
      }

    }

    return bounds;
  }

  // fix grid case // TODO: 8/2/16  
  private int getDividerSize(int childPos, int horizontalPosition, RecyclerView parent) {
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

    outRect.set(0, 0, 0, getDividerSize(position, horizontalPosition, parent));
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

  public void setHorizontal(boolean horizontal) {
    mIsHorizontal = horizontal;
  }
}
