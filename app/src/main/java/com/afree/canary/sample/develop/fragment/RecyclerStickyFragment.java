package com.afree.canary.sample.develop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afree.canary.R;
import com.afree.canary.base.BaseFragment;
import com.afree.canary.sample.develop.adapter.RecyclerStickyAdapter;
import com.afree.canary.sample.develop.model.RecyclerFakeDataCreator;
import com.afree.canary.widget.recycler.sticky.StickyRecyclerHeadersDecoration;
import com.afree.utils.GlobalConfig;

/**
 * @author afree8909@gmail.com on 8/2/16.
 */
public class RecyclerStickyFragment extends BaseFragment {

  private View mContainer;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContainer = inflater.inflate(R.layout.develop_recycler_sticky_fragment, container, false);
    return mContainer;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    RecyclerView recyclerView = (RecyclerView) mContainer.findViewById(R.id.rv_recycler_sticky_develop);

    RecyclerStickyAdapter adapter = new RecyclerStickyAdapter();
    adapter.setHeaderData(getHeaderData());
    adapter.setData(RecyclerFakeDataCreator.getCities());

    recyclerView.setAdapter(adapter);

    LinearLayoutManager manager = new LinearLayoutManager(recyclerView.getContext());
    recyclerView.setLayoutManager(manager);

    final StickyRecyclerHeadersDecoration headersDecor =
            new StickyRecyclerHeadersDecoration(adapter);
    recyclerView.addItemDecoration(headersDecor);

    adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
      @Override
      public void onChanged() {
        headersDecor.invalidateHeaders();
      }
    });

  }

  public SparseArray<String> getHeaderData() {
    String[] array = GlobalConfig.getContext().getResources().getStringArray(R.array.letter_list);
    SparseArray<String> sparseArray = new SparseArray<String>();
    for (int i = 0; i < array.length; i++) {
      sparseArray.put(i, array[i]);
    }
    return sparseArray;
  }


}
