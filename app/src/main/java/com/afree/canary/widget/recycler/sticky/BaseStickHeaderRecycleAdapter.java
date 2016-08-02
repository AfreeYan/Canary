package com.afree.canary.widget.recycler.sticky;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.afree.canary.base.BaseController;
import com.afree.canary.base.adapter.BaseRecyclerAdapter;


/**
 * @author afree8909@gmail.com on 7/31/16.
 */
public abstract class BaseStickHeaderRecycleAdapter<H, T>
    extends BaseRecyclerAdapter<T> implements
    StickyRecyclerHeadersAdapter<BaseStickHeaderRecycleAdapter.BaseHeaderViewHolder> {

  protected SparseArray<H> mHeaderData;

  public void setHeaderData(SparseArray<H> data) {
    this.mHeaderData = data;
    notifyDataSetChanged();
  }

  public SparseArray<H> getHeaderData() {
    return mHeaderData;
  }

  public H getHeaderItem(int headerId) {
    if (mHeaderData == null) {
      return null;
    }
    return mHeaderData.get(headerId);
  }

  public int getHeaderCount() {
    return mHeaderData == null ? 0 : mHeaderData.size();
  }

  public void clearHeader() {
    if (mHeaderData != null) {
      mHeaderData.clear();
    }
    notifyDataSetChanged();
  }

  @Override
  public BaseHeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
    BaseController headerController;
    View headerView;
    headerController = newHeaderController();
    headerView = newHeaderView(parent);
    return new BaseHeaderViewHolder(headerView, headerController);
  }

  @Override
  public void onBindHeaderViewHolder(BaseHeaderViewHolder holder, int position) {
    doBindHeader(holder.mHeaderController, holder.itemView,
        getHeaderItem((int) getHeaderId(position)));
  }


  public final static class BaseHeaderViewHolder extends RecyclerView.ViewHolder {
    public final BaseController mHeaderController;

    public BaseHeaderViewHolder(View itemView, BaseController controller) {
      super(itemView);
      this.mHeaderController = controller;
    }
  }

  protected void doBindHeader(BaseController controller, View baseView, H baseModel) {
    controller.bind(baseView, baseModel);
  }

  protected abstract View newHeaderView(ViewGroup parent);

  protected abstract BaseController newHeaderController();



}
