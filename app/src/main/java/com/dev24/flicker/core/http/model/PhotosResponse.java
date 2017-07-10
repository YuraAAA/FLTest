package com.dev24.flicker.core.http.model;

import com.dev24.flicker.model.Photo;

import java.util.List;

import lombok.Getter;

/**
 * Created by Yuriy Aizenberg
 */
@Getter
public class PhotosResponse {

    private int page;
    private int pages;
    private int perpage;
    private int total;
    private List<Photo> photo;

}
