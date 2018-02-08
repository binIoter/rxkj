package com.rxkj.libcore;

import android.content.Context;
import com.rxkj.libcore.module.IModule;

/**
 * Created by zhangbin on 2018/2/8.
 */

public class LibCoreModule implements IModule {
  private Context context;
  private static class PandoraModuleHolder {
    public static final LibCoreModule INSTANCE = new LibCoreModule();
  }
  @Override public void init(Context context) {
    this.context = context;
  }

  public static Context getAppContext() {
    return getInstance().context;
  }

  public static LibCoreModule getInstance() {
    return PandoraModuleHolder.INSTANCE;
  }


}
