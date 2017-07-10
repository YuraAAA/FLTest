package com.dev24.flicker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dev24.flicker.R;
import com.dev24.flicker.model.Photo;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by Yuriy Aizenberg
 */

public class PhotosAdapter extends BaseRVAdapter<Photo, PhotosAdapter.PhotoHolder> {

    public PhotosAdapter(Context context) {
        super(context);
        setHasStableIds(true);
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoHolder(inflateLayout(R.layout.photo_list_item, parent));
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        Photo photo = getItemByPosition(position);
        Picasso picasso = Picasso.with(context);
        String photoUrl = photo.getPhotoUrl(Photo.Size.Q);

        picasso.load(photoUrl)
                .fit()
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.imageView);


        setupTouchListener(holder.imageView, photo, holder.getAdapterPosition());
    }

    class PhotoHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public PhotoHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_photo_item);
        }
    }
}
