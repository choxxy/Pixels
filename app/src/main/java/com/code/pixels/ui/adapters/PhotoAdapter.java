package com.code.pixels.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.code.pixels.R;
import com.code.pixels.data.model.PhotoItem;
import com.code.pixels.databinding.LayoutListItemBinding;
import com.code.pixels.ui.callbacks.OnListItemSelected;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private List<PhotoItem> items = new ArrayList<>();
    private final OnListItemSelected listener;

    public PhotoAdapter(OnListItemSelected listener) {
        this.listener = listener;
    }

    public void setItems(List<PhotoItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutListItemBinding binding = LayoutListItemBinding.inflate(LayoutInflater.from(parent.getContext())
                , parent, false);
        return new PhotoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        PhotoItem item = items.get(position);
        holder.bind(item);
        holder.itemView.setOnClickListener(view -> listener.itemSelect(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder {

        LayoutListItemBinding binding;

        public PhotoViewHolder(LayoutListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(PhotoItem item) {
            Picasso.get().load(item.getThumbnailUrl()).error(R.mipmap.ic_launcher).into(binding.thumbnail, new Callback() {
                @Override
                public void onSuccess() {
                    binding.imageProgress.setVisibility(View.GONE);
                }
                @Override
                public void onError(Exception e) {
                    binding.imageProgress.setVisibility(View.GONE);
                }

            });
            binding.title.setText(item.getTitle());
        }
    }
}
