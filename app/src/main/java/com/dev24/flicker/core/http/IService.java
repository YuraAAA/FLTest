package com.dev24.flicker.core.http;

import com.dev24.flicker.core.http.model.PhotoResponseParent;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Yuriy Aizenberg
 */

public interface IService {

    //flickr.photos.getPopular
    @GET("services/rest/")
    Observable<PhotoResponseParent> getPopularPhotos(@Query("method") String method, @Query("per_page") int perPage, @Query("page") int page);

}
