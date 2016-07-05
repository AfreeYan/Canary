package com.afree.canary.sample.design.model;

import android.util.SparseArray;

import com.afree.canary.sample.design.fragment.AnimatorBtnFragment;
import com.afree.canary.sample.design.fragment.AnimatorCbFragment;

/**
 * @author afree8909@gmail.com on 7/5/16.
 */
public class AnimatorFakeDataCreator {
  private static final String[] NAMES = {
      "CheckBox", "Button"
  };

  private static final String[] FRAGMENT_NAMES = {
      AnimatorCbFragment.class.getName(),
      AnimatorBtnFragment.class.getName()
  };

  public static SparseArray<AnimatorModel> create() {
    SparseArray<AnimatorModel> sparseArray = new SparseArray<>();

    for (int i = 0; i < NAMES.length; i++) {

      AnimatorModel m = new AnimatorModel();
      m.setName(NAMES[i]);
      m.setFragmentName(FRAGMENT_NAMES[i]);
      sparseArray.put(i, m);
    }

    return sparseArray;
  }
}
