package com.afree.canary.sample.design;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.afree.canary.R;
import com.afree.canary.base.BaseActivity;

/**
 * @author afree8909@gmail.com on 6/23/16.
 */
public class DesignActivity extends BaseActivity {

  private final static String KEY_FRAGMENT_NAME = "fragment_name";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.frame_activity);

    String fragmentName = getIntent().getStringExtra(KEY_FRAGMENT_NAME);

    Fragment f = Fragment.instantiate(this, fragmentName);
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.fl_base_container, f).commit();
  }

  public static void launch(Context context, String fragmentName) {
    if (TextUtils.isEmpty(fragmentName)) {
      return;
    }
    Intent intent = new Intent(context, DesignActivity.class);
    if (!(context instanceof Activity)) {
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
    intent.putExtra(KEY_FRAGMENT_NAME, fragmentName);
    context.startActivity(intent);
  }

}
