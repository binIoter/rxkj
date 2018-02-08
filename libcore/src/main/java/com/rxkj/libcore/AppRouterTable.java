package com.rxkj.libcore;

import java.util.HashMap;
import java.util.Map;

public class AppRouterTable {
  /**
   * key of router
   */
  public static final String KEY_MAIN_ACTIVITY = "home";
  public static final String KEY_ENTRY_ACTIVITY = "entry";

  /**
   * path of router
   */
  public static final String PATH_MAIN_ACTIVITY = "/app/home";
  public static final String PATH_ENTRY_ACTIVITY = "/app/entry";

  public static final Map<String, String> ROUTER_MAP = new HashMap<>();

  static {
    ROUTER_MAP.put(KEY_ENTRY_ACTIVITY, PATH_ENTRY_ACTIVITY);
    ROUTER_MAP.put(KEY_MAIN_ACTIVITY, PATH_MAIN_ACTIVITY);

  }
}
