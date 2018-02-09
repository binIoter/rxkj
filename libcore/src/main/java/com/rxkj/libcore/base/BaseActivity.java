package com.rxkj.libcore.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.githang.statusbar.StatusBarCompat;
import com.rxkj.libcore.R;
import com.rxkj.libcore.utils.StatusBarUtil;
import com.rxkj.libcore.utils.ToastUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public class BaseActivity extends RxAppCompatActivity
    implements ActivatableComponentImpl.ActivatableComponent {
  private ViewGroup mContainerView;
  private ActivatableComponentImpl mImpl = new ActivatableComponentImpl(this);

  @Override public void setContentView(final int resId) {
    if (mContainerView == null) {
      super.setContentView(resId);
    } else {
      mContainerView.removeAllViews();
      final LayoutInflater inflater =
          (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      inflater.inflate(resId, mContainerView, true);
    }
  }

  @Override public void setContentView(final View view) {
    if (mContainerView == null) {
      super.setContentView(view);
    } else {
      mContainerView.removeAllViews();
      mContainerView.addView(view);
    }
  }

  @Override public void setContentView(final View view, final ViewGroup.LayoutParams layoutParams) {
    if (mContainerView == null) {
      super.setContentView(view, layoutParams);
    } else {
      mContainerView.removeAllViews();
      mContainerView.addView(view, layoutParams);
    }
  }

  protected void setContainerView(final ViewGroup container) {
    mContainerView = container;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.colorApp));
  }

  public <T> LifecycleTransformer<T> getDestroyEvent() {
    return bindUntilEvent(ActivityEvent.DESTROY);
  }


  protected void replaceFragment(int containerViewId, Fragment fragment, String tag) {
    FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
    fragmentTransaction.replace(containerViewId, fragment, tag);
    fragmentTransaction.commitAllowingStateLoss();
  }

  public void showToast(String text) {
    if (TextUtils.isEmpty(text)) {
      return;
    }
    ToastUtils.showToast(text, Toast.LENGTH_SHORT);
  }

  public void showToast(int resId) {
    String str = getString(resId);
    if (TextUtils.isEmpty(str)) {
      return;
    }
    ToastUtils.showToast(str, Toast.LENGTH_SHORT);
  }

  @Override protected void onPause() {
    super.onPause();
    mImpl.onPause();
  }

  @Override protected void onResume() {
    super.onResume();
    mImpl.onResume();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
  }

  @Nullable @Override public Activity getActivity() {
    return this;
  }

  @NonNull @Override public ActivatableComponentImpl getActivitableComponentImpl() {
    return mImpl;
  }
}
