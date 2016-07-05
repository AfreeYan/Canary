package com.afree.canary.sample.design.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afree.canary.R;
import com.afree.canary.base.BaseFragment;
import com.afree.canary.sample.design.adapter.AnimatorAdapter;
import com.afree.canary.sample.design.model.AnimatorFakeDataCreator;
import com.afree.canary.widget.pager.PagerSlidingTabStrip;

/**
 * @author afree8909@gmail.com on 6/29/16.
 */
public class AnimatorFragment extends BaseFragment {
  private ViewGroup mContainer;

  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mContainer = (ViewGroup) inflater.inflate(R.layout.design_animator_fragment, container, false);
    return mContainer;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    initView();
  }

  private void initView() {
    PagerSlidingTabStrip tab =
        (PagerSlidingTabStrip) mContainer.findViewById(R.id.tab_animator_design_fragment);
    ViewPager pager = (ViewPager) mContainer.findViewById(R.id.pager_animator_design_fragment);

    AnimatorAdapter adapter =
        new AnimatorAdapter(getFragmentManager(), AnimatorFakeDataCreator.create());
    pager.setAdapter(adapter);
    tab.setViewPager(pager);
  }
}
