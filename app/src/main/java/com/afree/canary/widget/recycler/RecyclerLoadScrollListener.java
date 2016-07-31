package com.afree.canary.widget.recycler;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

/**
 * parse recyclerView or AbsListView scroll event to load more event
 * 
 * @author afree8909@gmail.com on 7/28/16.
 */
public abstract class RecyclerLoadScrollListener extends RecyclerView.OnScrollListener {
  /**
   * whether to parse scroll event
   */
  private boolean mEnabled = true ;
  private boolean mIsLoaded = true ;

  private int mLastVisibleItemPosition;

  /**
   * {@link #onLoadMore()} will invoked if the value >(childCount - mLastVisibleItemPosition)
   */
  private int mPreLoadValue = 2;

  @Override
  public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);

    Log.d("test", "y=" + dy);
    if (dy > 0) {
      mEnabled = true;
      mLastVisibleItemPosition = calculateLastVisibleItemPosition(recyclerView.getLayoutManager());
    } else {
      mEnabled = false;
    }
  }

  @Override
  public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
    super.onScrollStateChanged(recyclerView, newState);

    Log.d("test", "onScrollStateChanged");

    if (!mEnabled) {
      return;
    }

    if (!mIsLoaded) {
      return;
    }
    if (newState != RecyclerView.SCROLL_STATE_IDLE) {
      return;
    }
    RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();
    int childCount = lm.getItemCount();
    if ((mPreLoadValue >= (childCount - mLastVisibleItemPosition))) {
      mIsLoaded = false;
      onLoadMore();
    }
  }

  protected int calculateLastVisibleItemPosition(RecyclerView.LayoutManager lm) {
    int pos = 0;
    if (lm instanceof LinearLayoutManager) {
      pos = ((LinearLayoutManager) lm).findLastVisibleItemPosition();
    } else if (lm instanceof StaggeredGridLayoutManager) {
      StaggeredGridLayoutManager s = (StaggeredGridLayoutManager) lm;
      int[] indexes = s.findLastVisibleItemPositions(new int[s.getSpanCount()]);
      pos = getMax(indexes);
    }

    return pos;
  }

  private int getMax(int[] array) {
    int max = 0;
    if (array == null || array.length == 0) {
      return max;
    }
    for (int i : array) {
      if (i > max) {
        max = i;
      }
    }
    return max;
  }

  /**
   * callback for load more
   */
  public abstract void onLoadMore();

  public void setLoaded(boolean loaded) {
    mIsLoaded = loaded;
  }

  public void setPreLoadValue(int preLoadValue) {
    mPreLoadValue = preLoadValue;
  }
}
