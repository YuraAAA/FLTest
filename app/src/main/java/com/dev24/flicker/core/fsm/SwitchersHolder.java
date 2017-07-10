package com.dev24.flicker.core.fsm;

import android.app.Activity;
import android.util.Log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class SwitchersHolder {

    private static final String TAG = "SwitchersHolder";
    private static Map<Class<? extends Activity>, ISwitcher> switcherMap =
        new ConcurrentHashMap<>();

    static void registerMnemonicSwitcher(Class<? extends Activity> clazz, ISwitcher switcher) {
        if (clazz == null) throw new SwitcherException("Class can't be null");
        if (switcher == null) throw new SwitcherException("Switcher can't be null");

        if (switcherMap.containsKey(clazz)) {
            Log.w(TAG,
                String.format("Switcher already registered for key %s", clazz.getSimpleName()));
        }
        switcherMap.put(clazz, switcher);
    }

    static void unregisterSwitcher(Class<? extends Activity> clazz) {
        if (clazz == null) throw new SwitcherException("Class can't be null");

        if (!switcherMap.containsKey(clazz)) {
            Log.w(TAG, String.format("Switcher not registered for key %s", clazz.getSimpleName()));
        } else {
            switcherMap.remove(clazz);
            Log.w(TAG, String.format("Switcher for %s unregistered", clazz.getSimpleName()));
        }
    }

    static ISwitcher obtainSwitcher(Class<? extends Activity> clazz) {
        if (clazz == null) throw new SwitcherException("Class can't be null");
        return switcherMap.get(clazz);
    }
}
