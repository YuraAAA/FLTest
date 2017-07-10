package com.dev24.flicker.utils;

import java.io.IOException;

import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.RealResponseBody;

/**
 * Created by Yuriy Aizenberg
 */

public class Sanitizer {

    public static Response sanitize(Response response) {
        RealResponseBody realResponseBody = (RealResponseBody) response.body();
        try {
            String string = realResponseBody.string();
            int wrongIndex = string.indexOf("(");
            if (wrongIndex != -1) {
                string = string.substring(wrongIndex + 1);
            }
            int lastWrongIndex = string.lastIndexOf(")");
            if (lastWrongIndex != -1) {
                string = string.substring(0, lastWrongIndex);
            }
            ResponseBody responseBody = RealResponseBody.create(realResponseBody.contentType(), string);
            return response.newBuilder().body(responseBody).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
