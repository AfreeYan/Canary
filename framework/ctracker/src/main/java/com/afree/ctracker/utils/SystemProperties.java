package com.afree.ctracker.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author afree on 8/14/16.
 */
public class SystemProperties {

  /**
   * Get the value for the given key, returned as a boolean.
   * Values 'n', 'no', '0', 'false' or 'off' are considered false.
   * Values 'y', 'yes', '1', 'true' or 'on' are considered true.
   * (case sensitive).
   * If the key does not exist, or has any other value, then the default
   * result is returned.
   *
   * @param key the key to lookup
   * @param def a default value to return
   * @return the key parsed as a boolean, or def if the key isn't found or is
   * not able to be parsed as a boolean.
   * @throws IllegalArgumentException if the key exceeds 32 characters
   */
  private static Method sGetWithDefaultMethod;

  static {
    try {
      Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
      initGetWithDefaultMethod(systemPropertiesClass);
    } catch (ClassNotFoundException e) {
    } catch (NoSuchMethodException e) {
    } catch (IllegalArgumentException e) {
    } catch (SecurityException e) {
    }
  }


  private static void initGetWithDefaultMethod(Class sytemPropertiesClass) throws SecurityException, NoSuchMethodException {
    Method method = sytemPropertiesClass.getDeclaredMethod("get", String.class, String.class);
    method.setAccessible(true);
    sGetWithDefaultMethod = method;
  }

  /**
   * Get the value for the given key.
   *
   * @return if the key isn't found, return def if it isn't null, or an empty string otherwise
   * @throws IllegalArgumentException if the key exceeds 32 characters
   */
  public static String get(String key, String def) {
    try {
      return (String) sGetWithDefaultMethod.invoke(null, key, def);
    } catch (IllegalArgumentException e) {
    } catch (IllegalAccessException e) {
    } catch (InvocationTargetException e) {
    }
    if (def == null) {
      def = "";
    }
    return def;
  }

}
