package com.afree.canary.sample.develop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afree.canary.R;
import com.afree.canary.base.BaseFragment;
import com.afree.canary.sample.develop.adapter.RecyclerAdapter;
import com.afree.canary.sample.develop.model.RecyclerFakeDataCreator;

/**
 * @author afree8909@gmail.com on 7/26/16.
 */
public class RecyclerFragment extends BaseFragment {
  private View mContainer;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mContainer = inflater.inflate(R.layout.develop_recycler_fragment, container, false);
    return mContainer;
  }


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    RecyclerView recyclerView = (RecyclerView) mContainer.findViewById(R.id.rv_recycler_develop);

    RecyclerAdapter adapter = new RecyclerAdapter();
    adapter.setData(RecyclerFakeDataCreator.create());

    recyclerView.setAdapter(adapter);

    GridLayoutManager manager = new GridLayoutManager(recyclerView.getContext(), 2);
    recyclerView.setLayoutManager(manager);

  }
}
