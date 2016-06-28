package com.afree.canary.widget.bar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afree.canary.R;

/**
 * @author afree8909@gmail.com on 6/27/16.
 */
public class CommonBottomTab extends LinearLayout {
  private ImageView mIcon;
  private TextView mText;
  private int mUnselectedColor = Color.DKGRAY;
  private int mSelectedColor = Color.DKGRAY;


  public CommonBottomTab(Context context) {
    this(context, null);
  }

  public CommonBottomTab(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CommonBottomTab(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initView(context, attrs, defStyleAttr);
  }

  private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
    inflate(getContext(), R.layout.default_tab_contianer, this);
    setOrientation(VERTICAL);
    mIcon = (ImageView) findViewById(R.id.iv_default_tab);
    mText = (TextView) findViewById(R.id.tv_default_tab);

    // parse custom attrs
    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CommonBottomTab);
    mSelectedColor = a.getResourceId(R.styleable.CommonBottomTab_ivSelectedColor, Color.DKGRAY);
    mUnselectedColor = a.getResourceId(R.styleable.CommonBottomTab_ivUnSelectedColor, Color.DKGRAY);


    Drawable icon = a.getDrawable(R.styleable.CommonBottomTab_ivSrc);
    String text = a.getString(R.styleable.CommonBottomTab_tvText);
    int tvSelectorRes = a.getResourceId(R.styleable.CommonBottomTab_tvSelector, 0);
    mIcon.setImageDrawable(icon);
    mText.setText(text);

    if (tvSelectorRes != 0) {
      setTextSelector(tvSelectorRes);
    }

    a.recycle();

  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    invalidateIconColor();
  }

  @Override
  public void setSelected(boolean selected) {
    super.setSelected(selected);
    invalidateIconColor();
  }

  /**
   * @param selectorRes need color drawable
   */
  public void setTextSelector(int selectorRes) {
    mText.setTextColor(getResources().getColorStateList(R.color.main_bottom_tab_selector));
  }

  /**
   * fresh imageView's color
   */
  private void invalidateIconColor() {
    int color = isSelected() ? mSelectedColor : mUnselectedColor;
    mIcon.setColorFilter(ContextCompat.getColor(getContext(), color));
  }


  public TextView getText() {
    return mText;
  }

  public ImageView getIcon() {
    return mIcon;
  }

  public void setUnselectedColor(int unselectedColor) {
    mUnselectedColor = unselectedColor;
  }

  public void setSelectedColor(int selectedColor) {
    mSelectedColor = selectedColor;
  }
}
