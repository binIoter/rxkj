package com.rxkj.poetry.presenter;

import android.os.CountDownTimer;
import com.rxkj.poetry.contract.EntryContract;

/**
 * Created by zhangbin on 2018/2/8.
 */

public class EntryPresenter implements EntryContract.Presenter {
  private EntryContract.View mView;
  private CountDownTimer mSplashTimer;
  private int AD_SHOW_TIME = 3000;

  public EntryPresenter(EntryContract.View view) {
    mView = view;
  }

  @Override public void start() {
    mView.showAdImage();
    cancelTimer();
    mSplashTimer = new CountDownTimer(AD_SHOW_TIME, 1000) {
      @Override public void onTick(final long millisUntilFinished) {
        mView.updateCountDown((int) (millisUntilFinished / 1000));
      }

      @Override public void onFinish() {
        mView.updateCountDown(0);
        mView.jumpToHome();
      }
    }.start();
  }

  private void cancelTimer() {
    if (mSplashTimer != null) {
      mSplashTimer.cancel();
      mSplashTimer = null;
    }
  }
}
