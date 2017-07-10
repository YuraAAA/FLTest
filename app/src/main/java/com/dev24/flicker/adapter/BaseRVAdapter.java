package com.dev24.flicker.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;

/**
 * Created by Yuriy Aizenberg
 */

public abstract class BaseRVAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private List<T> data = new ArrayList<>();
    private LayoutInflater inflater;
    protected Context context;
    @Setter
    private ITouchListener<T> touchListener;

    public BaseRVAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    protected View inflateLayout(@LayoutRes int layoutId, ViewGroup container) {
        return inflater.inflate(layoutId, container, false);
    }

    public void setData(@NonNull List<T> data) {
        this.data = data;
        notifyItemRangeInserted(0, data.size() - 1);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void addData(@NonNull List<T> data) {
        int size = getItemCount();
        this.data.addAll(data);
        notifyItemRangeInserted(size, this.data.size() - 1);
    }

    protected void setupTouchListener(View view, T object, int position) {
        view.setOnClickListener(v -> {
            if (touchListener != null) touchListener.onTouch(object, position);
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public T getItemByPosition(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface ITouchListener<T> {

        void onTouch(T data, int position);

    }
}
