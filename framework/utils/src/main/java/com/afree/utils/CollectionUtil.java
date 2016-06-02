package com.afree.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Convenience class for Collection Operation
 * 
 * @author afree on 6/1/16.
 */
public class CollectionUtil {

  public static <T> boolean isEmpty(Collection<T> collection) {
    return collection == null || collection.isEmpty();
  }

  public static <K, V> boolean isEmpty(Map<K, V> map) {
    return map == null || isEmpty(map.keySet());
  }

  /**
   * replace all elements from start with elements in source.
   * Some adapter use this method to append target to originData from position.
   */
  public static <T> List<T> replaceFromPosition(List<T> origin, List<T> target, int pos) {
    if (isEmpty(origin)) {
      return target;
    }
    if (isEmpty(target)) {
      return origin;
    }
    if (pos > origin.size()) {// append to end of list
      pos = origin.size();
    }
    List<T> result = new ArrayList<>(origin);// avoid operation about origin list
    int newSize = pos + target.size();
    result.addAll(pos, target);
    while (result.size() > newSize) {
      result.remove(result.size() - 1); // remove last until size is right.
    }
    return result;
  }

  /**
   * append the target to the origin from pos.
   */
  public static <T> List<T> appendFromPosition(List<T> origin, List<T> target, int pos) {
    if (isEmpty(origin)) {
      List<T> result = new ArrayList<T>();
      if (target != null) {
        result.addAll(target); // clone
      }
      return result;
    }
    if (isEmpty(target)) {
      return origin;
    }
    if (pos > origin.size()) {// append to end of list
      pos = origin.size();
    }
    List<T> result = new ArrayList<>(origin);// avoid operation about origin list
    result.addAll(pos, target);
    return result;
  }
}
