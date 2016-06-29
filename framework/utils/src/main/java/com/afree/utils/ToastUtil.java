package com.afree.utils;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/**
 * @author afree8909@gmail.com on 6/29/16.
 */
public class ToastUtil {

  public static void toast(String message) {
    if (!TextUtils.isEmpty(message)) {
      Toast.makeText(GlobalConfig.getContext(), message, Toast.LENGTH_SHORT).show();
    }
  }

  public static void toastLong(String message) {
    if (!TextUtils.isEmpty(message)) {
      Toast.makeText(GlobalConfig.getContext(), message, Toast.LENGTH_SHORT).show();
    }
  }

  public static void toast(int res) {
    String msg = GlobalConfig.getContext().getString(res);
    toast(msg);
  }

  public static void toastLong(int res) {
    String msg = GlobalConfig.getContext().getString(res);
    toastLong(msg);
  }


  public static void toastByCustomView(View v) {
    if (v == null) {
      return;
    }
    final Toast toast = new Toast(GlobalConfig.getContext());
    toast.setGravity(Gravity.CENTER, 0, 0);
    toast.setView(v);
    toast.setDuration(Toast.LENGTH_SHORT);
    toast.show();
  }

}
