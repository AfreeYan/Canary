package com.afree.canary.widget.load;

import android.widget.AbsListView;

/**
 * @author afree8909@gmail.com on 7/22/16.
 */
public class SwipeLoadMore {

  private AbsListView.OnScrollListener mScrollListener = new AbsListView.OnScrollListener() {
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
        int totalItemCount) {
      scroll(firstVisibleItem, visibleItemCount, totalItemCount);
    }

  };


  private void scroll(int firstVisibleItem, int visibleItemCount, int totalItemCount) {}

}
