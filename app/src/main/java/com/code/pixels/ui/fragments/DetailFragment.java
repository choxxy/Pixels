package com.code.pixels.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.code.pixels.R;
import com.code.pixels.data.model.PhotoItem;
import com.code.pixels.databinding.FragmentDetailBinding;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


public class DetailFragment extends Fragment {

    private PhotoItem photoItem;
    private FragmentDetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            DetailFragmentArgs args =  DetailFragmentArgs.fromBundle(getArguments());
            this.photoItem = args.getPhotoItem();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(this.photoItem != null){
            Picasso.get()
                    .load(photoItem.getPhotoUrl())
                    .error(R.mipmap.ic_launcher)
                    .into(binding.photo, new Callback() {
                        @Override
                        public void onSuccess() {
                            binding.imageProgress.setVisibility(View.GONE);
                        }
                        @Override
                        public void onError(Exception e) {
                            binding.imageProgress.setVisibility(View.GONE);
                            Toast.makeText(requireContext(), R.string.unable_to_load_image,Toast.LENGTH_SHORT).show();
                        }

                    });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}