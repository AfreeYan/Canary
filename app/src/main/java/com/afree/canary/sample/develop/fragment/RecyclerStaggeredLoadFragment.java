package com.afree.canary.sample.develop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afree.canary.R;
import com.afree.canary.base.BaseFragment;
import com.afree.canary.sample.develop.adapter.RecyclerStaggerAdapter;
import com.afree.canary.sample.main.model.CommonModel;
import com.afree.canary.widget.decoration.LoadFooterView;
import com.afree.canary.widget.load.RecyclerLoadScrollListener;

import java.util.ArrayList;

/**
 * @author afree8909@gmail.com on 7/26/16.
 */
public class RecyclerStaggeredLoadFragment extends BaseFragment {

  private View mContainer;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContainer = inflater.inflate(R.layout.develop_recycler_fragment, container, false);
    return mContainer;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    RecyclerView recyclerView = (RecyclerView) mContainer.findViewById(R.id.rv_recycler_develop);

    final RecyclerStaggerAdapter adapter = new RecyclerStaggerAdapter();

    adapter.setData(makeFakeData(0));
    final LoadFooterView footerView = new LoadFooterView(getContext());
    adapter.setFooter(footerView);
    footerView.setVisibility(View.INVISIBLE);


    recyclerView.setAdapter(adapter);

    recyclerView.addOnScrollListener(new RecyclerLoadScrollListener() {
      @Override
      public void onLoadMore() {
        footerView.setVisibility(View.VISIBLE);
        ArrayList<CommonModel> list = makeFakeData(adapter.getCount());
        adapter.addAll(list);
        setLoaded(true);
      }
    });

    StaggeredGridLayoutManager manager =
        new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(manager);

    final SwipeRefreshLayout swipeRefreshLayout =
        (SwipeRefreshLayout) mContainer.findViewById(R.id.swipe_recycler_develop);
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        adapter.setData(makeFakeData(0));
        swipeRefreshLayout.setRefreshing(false);
        footerView.setVisibility(View.INVISIBLE);

      }
    });

  }

  private ArrayList<CommonModel> makeFakeData(int start) {
    ArrayList<CommonModel> list = new ArrayList<>();
    for (int i = start; i < start + 20; i++) {
      CommonModel m = new CommonModel("item " + i, "");
      list.add(m);
    }
    return list;
  }

}
