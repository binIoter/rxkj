package com.rxkj.poetry.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.rxkj.poetry.R;

public class HomeFragment extends Fragment {
  private Toolbar mToolbar;

  public HomeFragment() {
  }

  public static HomeFragment newInstance() {
    return new HomeFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_home, container, false);
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mToolbar = view.findViewById(R.id.toolbar);
    initToolBar();
  }

  private void initToolBar() {
    mToolbar.inflateMenu(R.menu.search_menu);
    final SearchView searchView = mToolbar.findViewById(R.id.action_search);
    searchView.setQueryHint("搜索知识库");
    AppCompatImageView button =
        searchView.findViewById(android.support.v7.appcompat.R.id.search_button);
    button.setImageResource(R.drawable.ic_search);
    TextView textView = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
    textView.setTextColor(Color.WHITE);
  }
}
