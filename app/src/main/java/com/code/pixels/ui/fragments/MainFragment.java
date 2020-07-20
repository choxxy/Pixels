package com.code.pixels.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.code.pixels.data.model.PhotoItem;
import com.code.pixels.databinding.MainFragmentBinding;
import com.code.pixels.ui.adapters.PhotoAdapter;
import com.code.pixels.ui.callbacks.OnListItemSelected;
import com.code.pixels.ui.viewmodels.MainViewModel;
import com.mancj.materialsearchbar.MaterialSearchBar;

import kotlin.Lazy;

import static org.koin.androidx.viewmodel.compat.ViewModelCompat.viewModel;


public class MainFragment extends Fragment  implements OnListItemSelected, MaterialSearchBar.OnSearchActionListener{

    private Lazy<MainViewModel> mainViewModel = viewModel(this, MainViewModel.class);
    private MainFragmentBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MainFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.searchView.setOnSearchActionListener(this);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        PhotoAdapter photoAdapter = new PhotoAdapter(this);
        binding.photoList.setAdapter(photoAdapter);
        binding.photoList.setLayoutManager(layoutManager);

        mainViewModel.getValue().getSearchResponse().observe(getViewLifecycleOwner(), response ->{
            if(response != null){
                photoAdapter.setItems(response);
            }
        });
    }


    @Override
    public void itemSelect(PhotoItem photoItem) {
        MainFragmentDirections.ActionToDetailFragment direction =  MainFragmentDirections.actionToDetailFragment(photoItem);
        NavHostFragment.findNavController(this).navigate(direction);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        mainViewModel.getValue().search(text.toString());
    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }
}