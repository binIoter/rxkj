package com.rxkj.libcore.base;

import android.support.annotation.StringRes;
import com.trello.rxlifecycle2.LifecycleTransformer;

public interface BaseView<T> {

  void setPresenter(T presenter);

  void showToast(String message);

  void showToast(@StringRes int resId);

  <T> LifecycleTransformer<T> getDestroyEvent();
}
