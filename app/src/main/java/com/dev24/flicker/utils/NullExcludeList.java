package com.dev24.flicker.utils;

import java.util.ArrayList;

/**
 * Created by Yuriy Aizenberg
 */
public class NullExcludeList<T> extends ArrayList<T> {


    public NullExcludeList(T... items) {
        addAllItems(items);
    }

    public void addAllItems(T... items) {
        for (T item : items) {
            add(item);
        }
    }

    @Override
    public boolean add(T object) {
        return object != null && super.add(object);
    }

    @Override
    public void add(int index, T object) {
        if (object != null) {
            super.add(index, object);
        }
    }
}
