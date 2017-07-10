package com.dev24.flicker.model;

import java.io.Serializable;
import java.text.MessageFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Yuriy Aizenberg
 */

@Getter
@Setter
public class Photo implements Serializable {

    private static final String URL_TEMPLATE = "https://farm{0}.staticflickr.com/{1}/{2}_{3}_{4}.jpg";

    public enum Size {
        Q,
        B
    }

    private String id;
    private String owner;
    private String secret;
    private String server;
    private int ispublic;
    private int isfriend;
    private int isfamily;
    private int farm;

    public String getPhotoUrl(Size size) {
        return MessageFormat.format(URL_TEMPLATE, farm, server, id, secret, size.name().toLowerCase()).replace(" ", "");
    }

}
