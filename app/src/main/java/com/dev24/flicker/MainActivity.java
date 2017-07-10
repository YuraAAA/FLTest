package com.dev24.flicker;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

import com.dev24.flicker.core.fsm.Switcher;
import com.dev24.flicker.fragment.PhotoListFragment_;

import org.androidannotations.annotations.EActivity;


@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Switcher.createSwitcher(this, R.id.container_main).switchTo(PhotoListFragment_.class);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
