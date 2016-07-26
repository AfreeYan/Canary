package com.afree.canary.sample.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afree.canary.R;
import com.afree.canary.base.BaseFragment;
import com.afree.canary.sample.develop.model.DevelopFakeDataCreator;
import com.afree.canary.sample.main.adapter.DesignListAdapter;

/**
 * @author afree8909@gmail.com on 6/20/16.
 */
public class DevelopFragment extends BaseFragment {
  private View mViewContainer;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mViewContainer = inflater.inflate(R.layout.main_develop_fragment, container, false);
    return mViewContainer;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    RecyclerView recyclerView = (RecyclerView) mViewContainer.findViewById(R.id.rv_develop_main);

    DesignListAdapter adapter = new DesignListAdapter();
    adapter.setData(DevelopFakeDataCreator.create());

    LinearLayoutManager lm = new LinearLayoutManager(getContext());
    lm.setOrientation(LinearLayoutManager.VERTICAL);

    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(lm);

  }
}
