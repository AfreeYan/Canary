package com.afree.canary.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.afree.utils.CollectionUtil;

import java.util.List;

/**
 * @author afree8909@gmail.com on 6/22/16.
 */
public class FragmentUtils {

  /**
   * return last fragment of the container by fragmentManager
   */
  public static Fragment getLastFragment(FragmentManager fm) {
    if (fm == null) {
      return null;
    }

    List<Fragment> list = fm.getFragments();

    if (CollectionUtil.isEmpty(list)) {
      return null;
    }

    return list.get(list.size() - 1);
  }

  /**
   * return last fragment of the container by fragmentManager
   */
  public static Fragment getTopFragment(FragmentManager fm) {
    if (fm == null) {
      return null;
    }

    List<Fragment> list = fm.getFragments();

    if (CollectionUtil.isEmpty(list)) {
      return null;
    }

    return list.get(0);
  }

}
