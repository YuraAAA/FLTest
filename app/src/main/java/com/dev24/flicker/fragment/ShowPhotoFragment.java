package com.dev24.flicker.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.dev24.flicker.R;
import com.dev24.flicker.model.Photo;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Yuriy Aizenberg
 */

@EFragment(R.layout.fragment_show_photo)
public class ShowPhotoFragment extends BaseFragment {

    public static final String KEY = "DATA";

    @ViewById(R.id.img_photo)
    ImageView imageView;

    @FragmentArg(value = KEY)
    Photo photo;

    @AfterViews
    public void show() {
        Picasso.with(getActivity())
                .load(photo.getPhotoUrl(Photo.Size.B))
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(imageView);
    }

}
