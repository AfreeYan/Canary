package com.afree.canary.sample.design.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.afree.canary.R;
import com.afree.canary.base.BaseFragment;
import com.afree.canary.sample.design.drawable.CheckBoxDrawable;

/**
 * @author afree8909@gmail.com on 7/5/16.
 */
public class AnimatorCbFragment extends BaseFragment {

  private ViewGroup mContainer;

  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mContainer =
        (ViewGroup) inflater.inflate(R.layout.animator_checkbox_fragment, container, false);
    return mContainer;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    CheckBox btn = (CheckBox) mContainer.findViewById(R.id.btn_animator_checkbox);

    int color = getActivity().getResources().getColor(R.color.red_400);
    CheckBoxDrawable drawable = new CheckBoxDrawable();
    if (Build.VERSION.SDK_INT > 16) {
      btn.setBackground(drawable);
    }
  }
}
