package com.afree.canary.base.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

/**
 * A BaseRecyclerDataAdapter only describes a list data operation
 * 
 * @author afree8909@gmail.com on 6/23/16.
 */
public abstract class BaseRecyclerDataAdapter<T, VH extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<VH> {
  private List<T> mData;

  public int getCount() {
    return mData == null ? 0 : mData.size();
  }

  public void setData(List<T> list) {
    mData = list;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return getCount();
  }

  /**
   * @return UnmodifiableList avoiding to modify
   */
  public List<T> getData() {
    return mData == null ? null : Collections.unmodifiableList(mData);
  }

  public T getItem(int position) {
    if (mData == null || getCount() <= position) {
      return null;
    }
    return mData.get(position);
  }

  public void add(T item) {
    if (item == null) {
      return;
    }

    checkData();
    mData.add(item);
    notifyItemInserted(mData.size() - 1);
  }

  public void addAll(List<T> list) {
    if (list == null || list.isEmpty()) {
      return;
    }
    checkData();

    int preCount = getCount();
    mData.addAll(list);
    notifyItemRangeInserted(preCount, list.size());
  }

  public void insert(T model, int position) {
    if (model == null) {
      return;
    }
    checkData();
    if (position < 0 || position > getCount()) {
      return;
    }
    mData.add(position, model);
    notifyItemInserted(position);
  }

  public void updateItem(int position) {
    checkData();
    if (position < 0 || position > getCount()) {
      return;
    }
    notifyItemChanged(position);
  }

  public void updateItem(T item) {
    checkData();
    int position = mData.indexOf(item);
    updateItem(position);
  }

  public void clear() {
    checkData();
    mData.clear();
    notifyDataSetChanged();
  }

  public void removeItem(int position) {
    checkData();
    if (position < 0 || position > getCount()) {
      return;
    }
    mData.remove(position);
    notifyItemRemoved(position);
  }

  public void removeItem(T item) {
    checkData();
    int i = mData.indexOf(item);
    removeItem(i);
  }


  private void checkData() {
    if (mData == null) {
      throw new RuntimeException("data is null ,which should be initial first");
    }
  }

}
