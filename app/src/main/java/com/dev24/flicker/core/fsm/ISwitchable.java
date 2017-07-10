package com.dev24.flicker.core.fsm;

import android.app.Fragment;

public interface ISwitchable {

    Class<? extends Fragment> getFragmentClass();
}
