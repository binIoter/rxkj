package com.rxkj.libcore.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
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
    Window window = getWindow();
    if (StatusBarUtil.changeStatusBarLightMode(this)) {
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.setStatusBarColor(Color.TRANSPARENT);
      }
    } else {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.default_status_bar_color));
      }
    }
    if (isFullScreenEnable()) {
      setForceFullScreen();
    }
  }

  public <T> LifecycleTransformer<T> getDestroyEvent() {
    return bindUntilEvent(ActivityEvent.DESTROY);
  }

  protected boolean isFullScreenEnable() {
    return false;
  }

  private void setForceFullScreen() {
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower
      // api
      View v = this.getWindow().getDecorView();
      v.setSystemUiVisibility(View.GONE);
    } else if (Build.VERSION.SDK_INT >= 19) {
      View decorView = getWindow().getDecorView();
      int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
      decorView.setSystemUiVisibility(uiOptions);
    }
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
