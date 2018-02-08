package com.rxkj.libcore.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;
import com.rxkj.libcore.LibCoreModule;

public class ToastUtils {
  private static ToastUtils sInstance;
  private Toast mToast;
  private Context mContext;

  private ToastUtils() {
    mContext = LibCoreModule.getAppContext();
  }

  public static ToastUtils getInstance() {
    if (sInstance == null) {
      synchronized (ToastUtils.class) {
        if (sInstance == null) {
          sInstance = new ToastUtils();
        }
      }
    }

    return sInstance;
  }

  private Toast createToast(String text, int duration) {
    cancelLastToast();
    text = getTextToShow(text);
    duration = getCorrectDuration(duration);
    mToast = Toast.makeText(mContext, text, duration);

    return mToast;
  }

  private int getCorrectDuration(int duration) {
    if (duration < 0) {
      duration = Toast.LENGTH_SHORT;
    }
    return duration;
  }

  private String getTextToShow(String text) {
    if (text == null) {
      text = "";
    }
    return text;
  }

  private Toast createToast(int resId, int duration) {
    cancelLastToast();
    String text = getTextToShow(mContext, resId);
    duration = getCorrectDuration(duration);
    mToast = Toast.makeText(mContext, text, duration);
    return mToast;
  }

  public static void showToast(String text) {
    showToast(text, Toast.LENGTH_SHORT);
  }

  public static void showToast(String text, int duration) {
    if (Looper.getMainLooper() != Looper.myLooper()) {
      return;
    }
    try {
      getInstance().createToast(text, duration).show();
    } catch (Exception e) {

    }
  }

  public static void showToast(int resId) {
    showToast(resId, Toast.LENGTH_SHORT);
  }

  public static void showToast(int resId, int duration) {
    if (Looper.getMainLooper() != Looper.myLooper()) {
      return;
    }
    try {
      getInstance().createToast(resId, duration).show();
    } catch (Exception e) {

    }
  }

  private String getTextToShow(Context context, int resId) {
    String text;
    if (resId < 0) {
      text = "";
    } else {
      text = context.getResources().getString(resId);
    }
    return text;
  }

  private void cancelLastToast() {
    if (mToast != null) {
      mToast.cancel();
      mToast = null;
    }
  }
}
