package com.finalproject.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finalproject.R;
import com.finalproject.adapter.ShowsAdapter;
import com.finalproject.databinding.FragmentShowsBinding;
import com.finalproject.ui.activity_home.HomeActivity;


public class FragmentShows extends Fragment {
    private HomeActivity activity;
    private FragmentShowsBinding binding;
    ShowsAdapter showsAdapter;

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (HomeActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shows, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        showsAdapter = new ShowsAdapter(activity, this);
        binding.recyclerShows.setLayoutManager(new GridLayoutManager(activity, 2));
        binding.recyclerShows.setAdapter(showsAdapter);
    }
}