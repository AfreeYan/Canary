package com.afree.canary.sample.design.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afree.canary.R;
import com.afree.canary.base.BaseFragment;
import com.afree.canary.sample.main.adapter.DesignListAdapter;
import com.afree.canary.sample.main.adapter.StringListAdapter;

import java.util.Arrays;

/**
 * @author afree8909@gmail.com on 6/23/16.
 */
public class CoordinatorLayoutFragment extends BaseFragment {
  protected Toolbar mToolbar;
  private String[] items = {
      "CoordinatorLayout", "NavigationView", "AppBarLayout", "CollapsingToolbarLayout",
      "CollapsingToolbarLayout", "Snackbar", "FloatingActionButton", "TextInputLayout"
  };
  private ViewGroup mContainer;


  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mContainer = (ViewGroup) inflater.inflate(R.layout.coordinator_fragment, container, false);
    return mContainer;
  }


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mToolbar = (Toolbar) mContainer.findViewById(R.id.tb_coordinator);
    mToolbar.setTitleTextColor(getResources().getColor(R.color.grey_1000));
    ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

    TabLayout tableLayout = (TabLayout) mContainer.findViewById(R.id.tl_coordinator);

    RecyclerView recyclerView = (RecyclerView) mContainer.findViewById(R.id.rv_coordinator);


    StringListAdapter adapter = new StringListAdapter();
    adapter.setData(Arrays.asList(items));

    LinearLayoutManager lm = new LinearLayoutManager(getContext());
    lm.setOrientation(LinearLayoutManager.VERTICAL);

    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(lm);
  }
}
