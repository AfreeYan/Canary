package com.afree.canary.sample.develop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afree.canary.R;
import com.afree.canary.base.BaseFragment;
import com.afree.canary.sample.develop.adapter.RecyclerStickyAdapter;
import com.afree.canary.sample.develop.model.RecyclerFakeDataCreator;
import com.afree.canary.widget.bar.IndexScroller;
import com.afree.canary.widget.recycler.sticky.StickyRecyclerHeadersDecoration;
import com.afree.utils.GlobalConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author afree8909@gmail.com on 8/2/16.
 */
public class RecyclerStickyFragment extends BaseFragment {
  private static final String[] HEADER_CHARACTOR = new String[] {"#"};
  private View mContainer;
  private int[] mPositions = new int[27];
  private static final char OFFSET = 'A';
  private Map mCharMap;
  private String[] mSections;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mContainer = inflater.inflate(R.layout.develop_recycler_sticky_fragment, container, false);
    return mContainer;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    final RecyclerView recyclerView =
        (RecyclerView) mContainer.findViewById(R.id.rv_recycler_sticky_develop);

    final RecyclerStickyAdapter adapter = new RecyclerStickyAdapter();
    List<CityItemModel> cities = RecyclerFakeDataCreator.getCities();
    if (cities != null) {
      cities.add(0, CityItemModel.getFakeCity("城市"));
    }

    fillPosition(cities);
    adapter.setData(cities);
    SparseArray<String> headerData = getHeaderData();
    createScrollerIndex();
    adapter.setHeaderData(headerData);

    recyclerView.setAdapter(adapter);

    final LinearLayoutManager manager = new LinearLayoutManager(recyclerView.getContext());
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


    IndexScroller indexScroller =
        (IndexScroller) mContainer.findViewById(R.id.index_scroller_city_list);
    final TextView floatLetter =
        (TextView) mContainer.findViewById(R.id.tv_float_letter_sticky_recycler);
    indexScroller
        .setOnIndexScrollerTouchChangeListenner(new IndexScroller.OnIndexScrollerTouchChangeListenner() {
          @Override
          public void OnIndexScrollerTouchChanged(boolean isTouched, String letter) {

            if (isTouched) {
              int position;
              if (TextUtils.equals(HEADER_CHARACTOR[0], letter)) {
                position = 0;
              } else {
                position = mPositions[letter.toUpperCase().charAt(0) - OFFSET];
              }
              manager.scrollToPositionWithOffset(position, 0);
              floatLetter.setText(letter);
              floatLetter.setVisibility(View.VISIBLE);
            } else {
              floatLetter.postDelayed(new Runnable() {
                @Override
                public void run() {
                  floatLetter.setVisibility(View.GONE);
                }
              }, 100);
            }
          }
        });
    indexScroller.setSections(mSections);
  }

  private void createScrollerIndex() {
    List<String> list = new ArrayList<>();
    list.add(HEADER_CHARACTOR[0]);
    for (int i = 0; i < 26; ++i) {
      char ch = (char) (OFFSET + i);
      int count = getChar2Selection(ch, mCharMap);
      if (count > 0) {
        list.add(String.valueOf(ch));
      }
    }

    mSections = new String[list.size()];
    list.toArray(mSections);

  }

  public SparseArray<String> getHeaderData() {
    String[] array = GlobalConfig.getContext().getResources().getStringArray(R.array.letter_list);

    SparseArray<String> sparseArray = new SparseArray<>();
    sparseArray.put(0, HEADER_CHARACTOR[0]);
    for (int i = 0; i < array.length; i++) {
      sparseArray.put(i + 1, array[i]);
    }
    return sparseArray;
  }

  private void fillPosition(List<CityItemModel> cities) {
    mCharMap = new HashMap<>();
    for (CityItemModel city : cities) {
      char ch = city.getFirstLetter();
      int count = getChar2Selection(ch, mCharMap);
      mCharMap.put(ch, ++count);
    }

    mPositions[0] = 1;
    for (int i = 1; i < mPositions.length; ++i) {
      mPositions[i] = mPositions[i - 1] + getChar2Selection((char) (OFFSET + i - 1), mCharMap);
    }
  }

  private int getChar2Selection(char ch, Map<Character, Integer> map) {
    Integer count = map.get(ch);
    return count != null ? count : 0;
  }


}
