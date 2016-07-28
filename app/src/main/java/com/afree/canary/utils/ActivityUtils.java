package com.afree.canary.utils;

import android.app.Activity;
import android.view.View;

/**
 * @author afree8909@gmail.com on 7/28/16.
 */
public class ActivityUtils {


  public static Activity findActivity(View view) {
    if (view == null) {
      return null;
    }
    View targetView = view;
    while (true) {
      if (targetView.getContext() instanceof Activity) {
        return (Activity) targetView.getContext();
      }
      if (!(targetView.getParent() instanceof View)) {
        return null;
      }
      targetView = (View) targetView.getParent();
    }
  }
}
