package com.dev24.flicker.utils;

import java.util.List;

public class GenericUtils {

    public static <T> T orElse(T data, T def) {
        return data != null ? data : def;
    }


    public static boolean  notNull(Object... t) {
        for (Object t1 : t) {
            if (t1 == null) return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> dropNulls(List<T> dataSource) {
        List<T> ts = new NullExcludeList<>();
        for (T t : dataSource) {
            ts.add(t);
        }
        return ts;

    }

}
