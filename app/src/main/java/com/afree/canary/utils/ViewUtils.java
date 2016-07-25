package com.afree.canary.utils;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author afree8909@gmail.com on 7/7/16.
 */
public class ViewUtils {

  /**
   * @param states states array
   * @param state target state
   * @return true for states contain target state
   */
  public static boolean isContainState(int[] states, int state) {
    if (states == null || states.length == 0) {
      return false;
    }

    for (int s : states) {
      if (s == state) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return child view
   */
  public static View findChildViewById(ViewGroup parent, int id) {
    if (parent == null) {
      return null;
    }
    final int count = parent.getChildCount();
    for (int i = 0; i < count; ++i) {
      View child = parent.getChildAt(i);
      if (child.getId() == id) {
        return child;
      }
    }
    return null;
  }


}
