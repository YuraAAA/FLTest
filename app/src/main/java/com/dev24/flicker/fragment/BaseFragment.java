package com.dev24.flicker.fragment;

import android.app.Fragment;
import android.widget.Toast;

import rx.functions.Action1;

/**
 * Created by Yuriy Aizenberg
 */

public class BaseFragment extends Fragment implements Action1<Throwable>{


    @Override
    public void call(Throwable throwable) {
        if (getActivity() != null) {
            throwable.printStackTrace();
            Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
