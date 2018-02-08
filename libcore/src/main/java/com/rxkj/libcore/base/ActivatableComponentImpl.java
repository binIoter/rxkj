package com.rxkj.libcore.base;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ActivatableComponentImpl {

  private final WeakReference<ActivatableComponent> component;
  private final List<Runnable> onResumeListeners = new ArrayList<>();
  private final List<Runnable> onResumeOnceListeners = new ArrayList<>();
  private final List<Runnable> onPauseListeners = new ArrayList<>();
  private boolean active;

  public ActivatableComponentImpl(@NonNull final ActivatableComponent component) {
    this.component = new WeakReference<>(component);
  }

  public void runOnUiThread(@NonNull final Runnable runnable) {
    final ActivatableComponent ac = component.get();
    if (active && (ac != null)) {
      final Activity activity = ac.getActivity();
      if (activity != null) {
        activity.runOnUiThread(runnable);
        return;
      }
    }
    onResumeOnceListeners.add(runnable);
  }

  // this method is for debugging only
  @Deprecated @NonNull public String log() {
    final ActivatableComponent ac = component.get();
    if (ac == null) {
      return "[null]";
    }
    return ac.getClass().getCanonicalName();
  }

  public void onResume() {
    active = true;
    for (final Runnable runnable : onResumeListeners) {
      runnable.run();
    }
    for (final Runnable runnable : onResumeOnceListeners) {
      runnable.run();
    }
    onResumeOnceListeners.clear();
  }

  public void onPause() {
    active = false;
    for (final Runnable runnable : onPauseListeners) {
      runnable.run();
    }
  }

  public void addOnPauseListener(@NonNull final Runnable runnable) {
    onPauseListeners.add(runnable);
  }

  public void addOnResumeListener(@NonNull final Runnable runnable) {
    onResumeListeners.add(runnable);
  }

  public void removeOnPauseListener(@NonNull final Runnable runnable) {
    onPauseListeners.remove(runnable);
  }

  public void removeOnResumeListener(@NonNull final Runnable runnable) {
    onResumeListeners.remove(runnable);
  }

  public interface ActivatableComponent {

    @Nullable Activity getActivity();

    @NonNull ActivatableComponentImpl getActivitableComponentImpl();
  }
}
