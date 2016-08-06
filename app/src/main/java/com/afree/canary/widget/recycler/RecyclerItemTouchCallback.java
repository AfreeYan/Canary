package com.afree.canary.widget.recycler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.afree.canary.R;
import com.afree.canary.base.adapter.BaseRecyclerDataAdapter;
import com.afree.utils.DimensionPixelUtils;

/**
 * @author afree8909@gmail.com on 8/5/16.
 */
public class RecyclerItemTouchCallback extends ItemTouchHelper.Callback {
  private Paint mPaint = new Paint();
  private BaseRecyclerDataAdapter mDataAdapter;

  public RecyclerItemTouchCallback(BaseRecyclerDataAdapter adapter) {
    mDataAdapter = adapter;
  }

  @Override
  public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    final int dragFlags =
        ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
    final int swipeFlags = ItemTouchHelper.START;
    return makeMovementFlags(dragFlags, swipeFlags);

  }

  @Override
  public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
      RecyclerView.ViewHolder target) {

    if (viewHolder.getItemViewType() != target.getItemViewType() || mDataAdapter == null) {
      return false;
    }

    mDataAdapter.swapItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
    return true;
  }

  @Override
  public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    int pos = viewHolder.getAdapterPosition();
    mDataAdapter.removeItem(pos);
  }

  @Override
  public void onChildDraw(final Canvas c, RecyclerView recyclerView,
      RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState,
      boolean isCurrentlyActive) {
    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
      View itemView = viewHolder.itemView;

      Bitmap icon;
      if (dX < 0) {
        icon = BitmapFactory.decodeResource(recyclerView.getResources(), R.mipmap.ic_delete_common);
        mPaint.setColor(ContextCompat.getColor(recyclerView.getContext(), R.color.red_500));

        c.drawRect(itemView.getRight() + dX,
            itemView.getTop() + itemView.getPaddingTop(),
            itemView.getRight() - itemView.getPaddingRight()
            , itemView.getBottom() - itemView.getPaddingBottom(), mPaint);

        c.drawBitmap(
            icon,
            itemView.getRight() - itemView.getPaddingRight()
                - DimensionPixelUtils.dip2px(recyclerView.getContext(), 36),
            itemView.getTop() + (itemView.getBottom() - itemView.getTop() - icon.getHeight()) / 2,
            mPaint);

        icon.recycle();

      }
    }
  }
}
