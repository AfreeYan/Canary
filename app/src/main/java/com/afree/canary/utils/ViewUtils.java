package com.afree.canary.utils;

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


}
