package com.afree.canary.sample.develop.model;

import com.afree.canary.sample.develop.fragment.RecyclerGridLoadFragment;
import com.afree.canary.sample.develop.fragment.RecyclerHFFragment;
import com.afree.canary.sample.develop.fragment.RecyclerListLoadFragment;
import com.afree.canary.sample.develop.fragment.RecyclerStaggeredLoadFragment;
import com.afree.canary.sample.main.model.CommonModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author afree8909@gmail.com on 7/26/16.
 */
public class RecyclerFakeDataCreator {

  private static final String[] NAMES = {
      "Header and Footer", "List with refresh and load more",
      "Grid with refresh and load more", "Staggered with refresh and load more"
  };

  private static final String[] FRAGMENT_NAMES = {
      RecyclerHFFragment.class.getName(),
      RecyclerListLoadFragment.class.getName(),
      RecyclerGridLoadFragment.class.getName(),
      RecyclerStaggeredLoadFragment.class.getName()
  };

  public static List<CommonModel> create() {
    List<CommonModel> list = new ArrayList<>();
    for (int i = 0; i < FRAGMENT_NAMES.length; i++) {
      CommonModel m = new CommonModel(NAMES[i], FRAGMENT_NAMES[i]);
      list.add(m);
    }
    return list;
  }

}
