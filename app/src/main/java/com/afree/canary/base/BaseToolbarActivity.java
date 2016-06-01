package com.afree.canary.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.afree.canary.R;

/**
 * base activity that contain toolbar
 * child activity content view would be added to fixed LinearLayout container, so {setContentView}
 * should be called careful
 * 
 * @author afree on 5/31/16.
 */
public class BaseToolbarActivity extends BaseActivity {
  protected Toolbar mToolbar;
  private LinearLayout mContainer;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initView();
  }

  private void initView() {
    getDelegate().setContentView(R.layout.base_toolbar_activity);
    mToolbar = (Toolbar) findViewById(R.id.toolbar_default);
    mContainer = (LinearLayout) findViewById(R.id.base_toolbar_activity_container);
    mToolbar.setTitleTextColor(getResources().getColor(R.color.grey_1000));
    setSupportActionBar(mToolbar);
  }

  @Override
  public void setContentView(@LayoutRes int layoutResID) {
    View contentView = LayoutInflater.from(this).inflate(layoutResID, null);
    mContainer.addView(contentView);
  }

  @Override
  public void setContentView(View view) {
    mContainer.addView(view);
  }

  @Override
  public void setContentView(View view, ViewGroup.LayoutParams params) {
    mContainer.addView(view, params);
  }
}
