package com.rxkj.poetry.contract;

import com.rxkj.libcore.base.BasePresenter;
import com.rxkj.libcore.base.BaseView;

public interface EntryContract {

  interface View extends BaseView<Presenter> {
    void showAdImage();

    void updateCountDown(int count);

    void jumpToHome();
  }

  interface Presenter extends BasePresenter {

  }
}
