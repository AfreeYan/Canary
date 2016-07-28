package com.afree.canary.sample.develop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afree.canary.R;
import com.afree.canary.base.BaseFragment;
import com.afree.canary.sample.develop.adapter.RecyclerListAdapter;
import com.afree.canary.sample.develop.model.RecyclerFakeDataCreator;
import com.afree.canary.sample.main.model.CommonModel;
import com.afree.canary.widget.load.RecyclerLoadScrollListener;

import java.util.ArrayList;

/**
 * @author afree8909@gmail.com on 7/26/16.
 */
public class RecyclerListLoadFragment extends BaseFragment {
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

    final RecyclerListAdapter adapter = new RecyclerListAdapter();

    adapter.setData(makeFakeData(0));

    recyclerView.setAdapter(adapter);

    recyclerView.addOnScrollListener(new RecyclerLoadScrollListener() {
      @Override
      public void onLoadMore() {
        ArrayList<CommonModel> list = makeFakeData(adapter.getCount());
        adapter.addAll(list);
      }
    });

    LinearLayoutManager manager = new LinearLayoutManager(recyclerView.getContext());
    recyclerView.setLayoutManager(manager);


  }

  private ArrayList<CommonModel> makeFakeData(int start) {
    ArrayList<CommonModel> list = new ArrayList<>();
    for (int i = start; i < start+ 10; i++) {
      CommonModel m = new CommonModel("item " + i, "");
      list.add(m);
    }
    return list;
  }

}
