package com.rxkj.poetry.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import com.ofo.route.OfoRouter;
import com.ofo.route.OnRouteCallback;
import com.ofo.route.PageContainer;
import com.rxkj.libcore.AppRouterTable;
import com.rxkj.libcore.base.BaseActivity;
import com.rxkj.poetry.R;
import com.rxkj.poetry.contract.EntryContract;
import com.rxkj.poetry.presenter.EntryPresenter;

public class EntryActivity extends BaseActivity implements EntryContract.View {
  private EntryContract.Presenter mPresenter;
  private ImageView mIvAd;
  private TextView mTvJump;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_entry);
    initView();
    setPresenter(new EntryPresenter(this));
    mPresenter.start();
  }

  private void initView() {
    mIvAd = findViewById(R.id.iv_ad);
    mTvJump = findViewById(R.id.tv_jump);
  }

  @Override public void setPresenter(EntryContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override public void showAdImage() {
    mIvAd.setImageResource(R.drawable.ic_launcher_background);
  }

  @Override public void updateCountDown(int count) {
    mTvJump.setText(String.valueOf(count));
  }

  @Override public void jumpToHome() {
    OfoRouter.getInstance()
        .build(AppRouterTable.KEY_MAIN_ACTIVITY)
        .navigation(this, new OnRouteCallback() {
          @Override public void onFound(PageContainer pageContainer) {

          }

          @Override public void onLost(PageContainer pageContainer) {

          }

          @Override public void onArrival(PageContainer pageContainer) {
            finish();
          }

          @Override public void onInterrupt(PageContainer pageContainer) {

          }
        });
  }
}
