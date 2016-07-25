package com.afree.canary.sample.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afree.canary.R;
import com.afree.canary.base.BaseFragment;
import com.afree.canary.sample.main.adapter.HeaderAndFooterTestAdapter;
import com.afree.canary.sample.main.view.DesignItemContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author afree8909@gmail.com on 6/20/16.
 */
public class OthersFragment extends BaseFragment {

  private String[] items = {
      "TencentOpen/GT", "blockcanary", "drawable-optimizer", "hotfix",
      "recyclerView", "dialog", "search", "plugin", "Android-QuickSideBar"
  };

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.main_others_fragment, container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    View container = getView();
    RecyclerView recyclerView = null;
    if (container != null) {
      recyclerView = (RecyclerView) container.findViewById(R.id.rv_others);
    }

    final HeaderAndFooterTestAdapter adapter = new HeaderAndFooterTestAdapter();

    final DesignItemContainer header = DesignItemContainer.newInstance((ViewGroup) container);
    final DesignItemContainer footer = DesignItemContainer.newInstance((ViewGroup) container);


    List<String> strings = Arrays.asList(items);
    adapter.setData(strings);

    LinearLayoutManager lm = new LinearLayoutManager(getContext());
    lm.setOrientation(LinearLayoutManager.VERTICAL);

    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(lm);


    assert container != null;
    final SwipeRefreshLayout swipeRefreshLayout =
        (SwipeRefreshLayout) container.findViewById(R.id.swipeLayout);
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {

        adapter.setHeader(header);
        adapter.setFooter(footer);

        swipeRefreshLayout.setRefreshing(false);
      }
    });


  }
}
