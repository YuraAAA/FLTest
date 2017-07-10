package com.dev24.flicker.core;

import com.dev24.flicker.core.http.RetrofitService;
import com.dev24.flicker.core.http.model.PhotoResponseParent;
import com.dev24.flicker.utils.RxUtils;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Yuriy Aizenberg
 */

@EBean(scope = EBean.Scope.Singleton)
public class DataProvider {

    public static final int PER_PAGE = 30;
    @Bean
    RetrofitService service;

    public Subscription getPhotos(int page, Action1<PhotoResponseParent> photosCallback, Action1<Throwable> errorCallback) {
        return service.getService().getPopularPhotos("flickr.photos.getPopular", PER_PAGE, page)
                .compose(RxUtils.applySchedulers())
                .subscribe(photosCallback, errorCallback);
    }

}
