package com.afree.canary.widget.cover;

import android.view.View;
import android.view.ViewGroup;

/**
 * util class for client to show,hide,and remove covers
 *
 * @author afree on 4/17/16.
 */
public class CoverUtils {

  /**
   * show covers view which above the target view
   * a target, only one covers for each type could be added,and which has some limit.
   *
   * @return covers , which you can bind business
   */
  public static Cover showCovers(View target, CoverType type) {
    if (target == null || type == null || !(target.getParent() instanceof ViewGroup)) {
      return null;
    }

    CoverContainer coverContainer = CoverContainer.build(target);
    return coverContainer.showCover(target, type);
  }


  /**
   * hide and recycle covers
   *
   * @param types covers applied to target
   */
  public static void hideCovers(View target, CoverType... types) {
    if (target == null || !(target.getParent() instanceof CoverContainer)) {
      return;
    }
    CoverContainer coverContainer = (CoverContainer) target.getParent();
    coverContainer.hideCovers(target, types);
  }


}
