package com.rxkj.poetry.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rxkj.poetry.R;

public class MyFragment extends Fragment {

  public MyFragment() {
  }

  public static MyFragment newInstance() {
    return new MyFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_my, container, false);
  }
}

