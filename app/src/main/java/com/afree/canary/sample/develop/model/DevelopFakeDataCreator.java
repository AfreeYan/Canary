package com.afree.canary.sample.develop.model;

import com.afree.canary.sample.main.model.CommonModel;
import com.afree.canary.sample.develop.fragment.RecyclerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author afree8909@gmail.com on 6/29/16.
 */
public class DevelopFakeDataCreator {

  private static final String[] NAMES = {
      "RecyclerView"
  };

  private static final String[] FRAGMENT_NAMES = {
      RecyclerFragment.class.getName()
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
