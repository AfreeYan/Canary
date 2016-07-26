package com.afree.canary.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.afree.canary.R;

/**
 * @author afree on 5/31/16.
 */
public class BaseActivity extends AppCompatActivity {

  /**
   * child class need to override this if fragment container id was changed
   * 
   * @return fragment container view id, only one root container for a activity
   */
  protected int getViewConatainerId() {
    return R.id.fl_base_container;
  }

  /**
   * replace fragment with no add to back stack
   */
  public void replace(Fragment fragment) {
    replace(fragment, false);
  }

  public void replace(Fragment fragment, boolean addToBackStack) {
    if (fragment == null) {
      return;
    }
    replace(getViewConatainerId(), fragment, addToBackStack);
  }

  public void replace(int containerViewId, Fragment fragment, boolean addToBackStack) {
    if (fragment == null) {
      return;
    }

    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.replace(containerViewId, fragment, fragment.getClass().getName());

    if (addToBackStack) {
      ft.addToBackStack(fragment.getClass().getName());
    }
    ft.commit();
  }
}
