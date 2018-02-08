package com.rxkj.poetry;

import android.app.Application;
import com.ofo.route.OfoRouter;
import com.ofo.route.RouterTable;
import com.rxkj.libcore.AppRouterTable;

/**
 * Created by zhangbin on 2018/2/8.
 */

public class App extends Application {
  @Override public void onCreate() {
    super.onCreate();
    OfoRouter.getInstance()
        .init(this, new RouterTable(1, true, AppRouterTable.ROUTER_MAP), null, BuildConfig.DEBUG);
  }
}
