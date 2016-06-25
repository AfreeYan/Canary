package com.afree.canary.sample.design;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;

import com.afree.canary.R;
import com.afree.canary.base.BaseActivity;
import com.afree.canary.sample.design.fragment.CoordinatorLayoutFragment;

/**
 * @author afree8909@gmail.com on 6/23/16.
 */
public class DesignActivity extends BaseActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.frame_activity);


    Fragment f = Fragment.instantiate(this, CoordinatorLayoutFragment.class.getName());
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.fl_base_container, f).commit();
  }

  public static void launch(Context context) {
    Intent intent = new Intent(context, DesignActivity.class);
    if (!(context instanceof Activity)) {
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
    context.startActivity(intent);
  }

}
