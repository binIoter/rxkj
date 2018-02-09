package com.rxkj.libcore.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import com.ofo.route.OfoRouter;
import com.rxkj.libcore.R;

public class DefaultActivity extends BaseActivity {
  protected Toolbar toolbar;
  protected static final String TITLE = "title";

  @Override protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    OfoRouter.getInstance().inject(this);
    addAppbar();
  }

  protected Toolbar addAppbar() {
    setContentView(R.layout.appbar);
    toolbar = findViewById(R.id.toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    setContainerView((FrameLayout) findViewById(R.id.frame));
    return toolbar;
  }

  @Override protected void onStart() {
    super.onStart();
  }
}
