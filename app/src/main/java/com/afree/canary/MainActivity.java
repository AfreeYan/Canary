package com.afree.canary;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.afree.canary.base.BaseToolbarActivity;

/**
 * @author afree on 5/30/16.
 */
public class MainActivity extends BaseToolbarActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main_activity);

  }
}
