package com.afree.canary.sample.develop.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.afree.canary.base.BaseController;
import com.afree.canary.sample.develop.controller.CityItemController;
import com.afree.canary.sample.develop.controller.LetterStickyController;
import com.afree.canary.sample.develop.fragment.CityItemModel;
import com.afree.canary.sample.develop.view.CityItemView;
import com.afree.canary.sample.develop.view.LetterStickyHeaderView;
import com.afree.canary.widget.recycler.sticky.BaseStickHeaderRecycleAdapter;

/**
 * @author afree8909@gmail.com on 8/2/16.
 */
public class RecyclerStickyAdapter extends BaseStickHeaderRecycleAdapter<String, CityItemModel> {
  private static final char HEADDER_CHARACTOR_LENGTH = 1;
  private static final char OFFSET = 'A';

  @Override
  protected View newHeaderView(ViewGroup parent) {
    return LetterStickyHeaderView.newInstance(parent);
  }

  @Override
  protected BaseController newHeaderController() {
    return new LetterStickyController();
  }

  @Override
  protected View newView(ViewGroup parent, int type) {
    return CityItemView.newInstance(parent);
  }

  @Override
  protected BaseController newController(int type) {
    return new CityItemController();
  }

  @Override
  public long getHeaderId(int position) {
    if (position < HEADDER_CHARACTOR_LENGTH) {
      return position;
    }
    CityItemModel city = getItem(position);
    char letter = city.getFirstLetter();
    return letter - OFFSET + HEADDER_CHARACTOR_LENGTH;
  }
}
