package com.afree.canary.sample.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.afree.canary.R;
import com.afree.canary.base.BaseToolbarActivity;
import com.afree.canary.sample.main.fragment.DesignFragment;
import com.afree.canary.sample.main.fragment.DevelopFragment;
import com.afree.canary.sample.main.fragment.OthersFragment;
import com.afree.canary.widget.bar.RadioBarLayout;

/**
 * @author afree on 5/30/16.
 */
public class MainActivity extends BaseToolbarActivity {

  private String[] mFragmentName =
  {DesignFragment.class.getName(), DevelopFragment.class.getName(), OthersFragment.class.getName()};
  private Fragment mLastFragment;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    initView(savedInstanceState);
  }

  private void initView(Bundle savedInstanceState) {
    setContentView(R.layout.main_activity);
    RadioBarLayout radioBar = (RadioBarLayout) findViewById(R.id.rb_main_bottom_bar);
    assert radioBar != null;

    radioBar.setOnSelectedListener(new RadioBarLayout.OnSelectedChangeListener() {
      @Override
      public void onSelectedChanged(View child, int curPos) {
        switchFragment(curPos);
      }
    });

    radioBar.setChecked(0);
  }

  private void switchFragment(int curPos) {
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();

    String curName = mFragmentName[curPos];

    Fragment curFragment = fm.findFragmentByTag(curName);
    if (curFragment != null) {
      ft.show(curFragment);
    } else {
      curFragment = Fragment.instantiate(this, curName);
      ft.add(R.id.fl_base_container, curFragment, curName);
      ft.show(curFragment);
    }
    if (mLastFragment != null) {
      ft.hide(mLastFragment);
    }
    mLastFragment = curFragment;

    ft.commit();
  }
}
