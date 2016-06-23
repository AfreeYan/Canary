package com.afree.canary.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.afree.canary.base.BaseController;

/**
 * @author afree8909@gmail.com on 6/23/16.
 */
public abstract class BaseRecyclerAdapter<T>
    extends BaseRecyclerDataAdapter<T, BaseRecyclerAdapter.ViewHolder> {

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    BaseController controller;
    View view;
    controller = newController(viewType);
    view = newView(parent, viewType);
    return new ViewHolder(view, controller);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    doBind(holder.mBaseController, holder.itemView, getItem(position));
  }

  private void doBind(BaseController controller, View view, T item) {
    controller.bind(view, item);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    private final BaseController mBaseController;

    public ViewHolder(View itemView, BaseController controller) {
      super(itemView);
      mBaseController = controller;
    }

  }

  protected abstract View newView(ViewGroup parent, int type);

  protected abstract BaseController newController(int type);

}
