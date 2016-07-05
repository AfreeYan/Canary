package com.afree.canary.sample.design.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.afree.canary.sample.design.model.AnimatorModel;
import com.afree.canary.widget.pager.PagerSlidingTabStrip;
import com.afree.utils.GlobalConfig;

/**
 * @author afree8909@gmail.com on 7/5/16.
 */
public class AnimatorAdapter extends FragmentPagerAdapter
    implements
      PagerSlidingTabStrip.IconTabProvider {
  private SparseArray<AnimatorModel> mSparseArray;

  public AnimatorAdapter(FragmentManager fm, SparseArray<AnimatorModel> sparseArray) {
    super(fm);
    mSparseArray = sparseArray;
  }

  @Override
  public Fragment getItem(int position) {
    AnimatorModel model = mSparseArray.get(position);
    return Fragment.instantiate(GlobalConfig.getContext(), model.getFragmentName());
  }

  @Override
  public int getCount() {
    return mSparseArray.size();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return mSparseArray.get(position).getName();
  }

  @Override
  public View getTab(int position) {
    TextView tv = new TextView(GlobalConfig.getContext());
    tv.setText(mSparseArray.get(position).getName());
    tv.setGravity(Gravity.CENTER);
    return tv;
  }
}
