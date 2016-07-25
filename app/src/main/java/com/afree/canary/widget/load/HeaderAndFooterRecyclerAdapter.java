package com.afree.canary.widget.load;

import android.view.View;

import com.afree.canary.base.BaseController;
import com.afree.canary.base.adapter.BaseRecyclerAdapter;

/**
 * recyclerView adapter which can support add header or footer view,
 * note,it can only support one view for each
 * 
 * @author afree8909@gmail.com on 7/23/16.
 */
public abstract class HeaderAndFooterRecyclerAdapter<T> extends BaseRecyclerAdapter<T> {

  protected static final int ITEM_VIEW_TYPE_HEADER = 100;
  protected static final int ITEM_VIEW_TYPE_FOOTER = 101;

  protected View mHeader;
  protected View mFooter;
  private Object mHeaderData;
  private Object mFooterData;

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    int headerCount = getHeaderCount();
    int contentCount = getCount();

    if (position < headerCount) {
      bindHeaderOrFooter(holder.mBaseController, holder.itemView, mHeaderData);
    } else if (position >= headerCount + contentCount) {
      bindHeaderOrFooter(holder.mBaseController, holder.itemView, mFooterData);
    } else {
      super.onBindViewHolder(holder, position - headerCount);
    }
  }

  private void bindHeaderOrFooter(BaseController controller, View itemView, Object data) {
    if (controller == null) {
      return;
    }
    controller.bind(itemView, data);
  }

  @Override
  public int getItemViewType(int position) {
    int headerCount = getHeaderCount();
    int contentCount = getCount();
    if (position < headerCount) {
      return ITEM_VIEW_TYPE_HEADER;
    } else if (position >= headerCount + contentCount) {
      return ITEM_VIEW_TYPE_FOOTER;
    }
    return super.getItemViewType(position);
  }

  public void setHeader(View header) {
    mHeader = header;
    notifyDataSetChanged();
  }

  public void setFooter(View footer) {
    mFooter = footer;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return getHeaderCount() + getCount() + getFooterCount();
  }

  protected int getHeaderCount() {
    return mHeader == null ? 0 : 1;
  }

  protected int getFooterCount() {
    return mFooter == null ? 0 : 1;
  }

  public void setHeaderData(Object headerData) {
    mHeaderData = headerData;
    notifyDataSetChanged();
  }

  public void setFooterData(Object footerData) {
    mFooterData = footerData;
    notifyDataSetChanged();
  }
}
