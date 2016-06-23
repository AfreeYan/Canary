package com.afree.canary.base;

import android.view.View;

/**
 * controller of base class for business logic
 * target object limited to view now
 * 
 * @author afree8909@gmail.com on 6/23/16.
 */
public abstract class BaseController<V extends View, T> {
  /**
   * The controller's tag
   */
  private Object mTag;

  public Object getTag() {
    return mTag;
  }

  /**
   * Set the tag associated with this controller
   * 
   * @param tag an Object to tag the controller with
   * @see #getTag()
   */
  public void setTag(Object tag) {
    this.mTag = tag;
  }

  public abstract void bind(V view, T t);

  public void unbind() {}
}
