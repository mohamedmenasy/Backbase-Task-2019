package com.mohamedmenasy.backbasetask.features.about;

import androidx.annotation.Nullable;
import androidx.test.espresso.IdlingResource;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoadAboutIdlingResource implements IdlingResource {
  @Nullable
  private volatile ResourceCallback mCallback;
  private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);

  @Override
  public String getName() {
    return this.getClass().getName();
  }

  @Override
  public boolean isIdleNow() {
    return mIsIdleNow.get();
  }

  @Override
  public void registerIdleTransitionCallback(ResourceCallback callback) {
    mCallback = callback;
  }

  public void setIdleState(boolean isIdleNow) {
    mIsIdleNow.set(isIdleNow);
    if (isIdleNow && mCallback != null) {
      mCallback.onTransitionToIdle();
    }
  }
}
