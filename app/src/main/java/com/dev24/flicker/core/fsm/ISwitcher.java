package com.dev24.flicker.core.fsm;

import android.app.Fragment;
import android.os.Bundle;

public interface ISwitcher {

    void switchTo(ISwitchable switchable, Bundle args, boolean addToBackStack);

    void switchTo(ISwitchable switchable, Bundle args);

    void switchTo(ISwitchable switchable, boolean addToBackStack);

    void switchTo(ISwitchable switchable);

    void switchTo(Class<? extends Fragment> clazz, Bundle args, boolean addToBackStack);

    void switchToAsAdd(Class<? extends Fragment> clazz, Bundle args);

    void switchTo(Class<? extends Fragment> clazz, Bundle args);

    void switchTo(Class<? extends Fragment> clazz, boolean addToBackStack);

    void switchTo(Class<? extends Fragment> clazz);

    ISwitcher clearBackStack();

    ISwitcher clearAnimation();

    ISwitcher setAnimations(int inAnimResource, int outAnimResource);

    ISwitcher setAnimations(int inAnimResource, int outAnimResource, int reverseInAnimResource,
                            int reverseOutAnimResource);

    ISwitcher withAnimations(int inAnimResource, int outAnimResource);

    ISwitcher withAnimations(int inAnimResource, int outAnimResource, int reverseInAnimResource,
                             int reverserOutAnimResource);

    ISwitcher withoutAnimation();
}
