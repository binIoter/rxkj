package com.rxkj.poetry.activity;

import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.rxkj.libcore.AppRouterTable;
import com.rxkj.libcore.base.BaseActivity;
import com.rxkj.poetry.R;

@Route(path = AppRouterTable.PATH_MAIN_ACTIVITY) public class HomeActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
  }
}
