package com.rxkj.libcore.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by shenyannan on 16/11/17.
 */
public class StatusBarUtil {

  private final static String STATUS_BAR_DEF_PACKAGE = "android";
  private final static String STATUS_BAR_DEF_TYPE = "dimen";
  private final static String STATUS_BAR_NAME = "status_bar_height";
  private static final int DEFAULT_STATUS_BAR_HEIGHT = 50;

  public static boolean changeStatusBarLightMode(Activity activity) {
    return statusBarLightMode(activity) != 0;
  }

  /**
   * 设置状态栏黑色字体图标，
   * 适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
   *
   * @return 1:MIUUI 2:Flyme 3:android6.0
   */
  public static int statusBarLightMode(final Activity activity) {
    int result = 0;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      if (MIUISetStatusBarLightMode(activity.getWindow(), true)) {
        result = 1;
      } else if (FlymeSetStatusBarLightMode(activity.getWindow(), true)) {
        result = 2;
      } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        activity.getWindow()
            .getDecorView()
            .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        result = 3;
      }
    }
    return result;
  }

  /**
   * 设置状态栏图标为深色和魅族特定的文字风格
   * 可以用来判断是否为Flyme用户
   *
   * @param window 需要设置的窗口
   * @param dark 是否把状态栏字体及图标颜色设置为深色
   * @return boolean 成功执行返回true
   */
  private static boolean FlymeSetStatusBarLightMode(final Window window, final boolean dark) {
    boolean result = false;
    if (window != null) {
      try {
        final WindowManager.LayoutParams lp = window.getAttributes();
        final Field darkFlag =
            WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
        final Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
        darkFlag.setAccessible(true);
        meizuFlags.setAccessible(true);
        final int bit = darkFlag.getInt(null);
        int value = meizuFlags.getInt(lp);
        if (dark) {
          value |= bit;
        } else {
          value &= ~bit;
        }
        meizuFlags.setInt(lp, value);
        window.setAttributes(lp);
        result = true;
      } catch (final Exception e) {
        // do nothing
      }
    }
    return result;
  }

  /**
   * 设置状态栏字体图标为深色，需要MIUIV6以上
   *
   * @param window 需要设置的窗口
   * @param dark 是否把状态栏字体及图标颜色设置为深色
   * @return boolean 成功执行返回true
   */
  private static boolean MIUISetStatusBarLightMode(final Window window, final boolean dark) {
    boolean result = false;
    if (window != null) {
      final Class clazz = window.getClass();
      try {
        final Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
        final Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
        final int darkModeFlag = field.getInt(layoutParams);
        final Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
        if (dark) {
          extraFlagField.invoke(window, darkModeFlag, darkModeFlag); // 状态栏透明且黑色字体
        } else {
          extraFlagField.invoke(window, 0, darkModeFlag); // 清除黑色字体
        }
        result = true;
      } catch (final Exception e) {
        // do nothing
      }
    }
    return result;
  }

  public static int getStatusBarHeight(final Context context) {
    int resourceId = context.getResources()
        .getIdentifier(STATUS_BAR_NAME, STATUS_BAR_DEF_TYPE, STATUS_BAR_DEF_PACKAGE);
    if (resourceId > 0) {
      return context.getResources().getDimensionPixelSize(resourceId);
    }

    return DEFAULT_STATUS_BAR_HEIGHT;
  }
}