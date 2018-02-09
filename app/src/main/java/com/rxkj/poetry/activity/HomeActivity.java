package com.rxkj.poetry.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.rxkj.libcore.AppRouterTable;
import com.rxkj.libcore.base.BaseActivity;
import com.rxkj.poetry.R;
import com.rxkj.poetry.fragment.FindFragment;
import com.rxkj.poetry.fragment.HomeFragment;
import com.rxkj.poetry.fragment.MyFragment;

@Route(path = AppRouterTable.PATH_MAIN_ACTIVITY) public class HomeActivity extends BaseActivity {
  private BottomBar mBottomBar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    mBottomBar = findViewById(R.id.bottomBar);
    mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
      @Override public void onTabSelected(@IdRes int tabId) {
        switch (tabId) {
          case R.id.tab_favorites:
            replaceFragment(R.id.contentContainer, HomeFragment.newInstance(),
                HomeFragment.class.getSimpleName());
            break;
          case R.id.tab_friends:
            replaceFragment(R.id.contentContainer, FindFragment.newInstance(),
                FindFragment.class.getSimpleName());
            break;
          case R.id.tab_nearby:
            replaceFragment(R.id.contentContainer, MyFragment.newInstance(),
                MyFragment.class.getSimpleName());
            break;
          default:
            replaceFragment(R.id.contentContainer, HomeFragment.newInstance(),
                HomeFragment.class.getSimpleName());
            break;
        }
      }
    });
  }


}
