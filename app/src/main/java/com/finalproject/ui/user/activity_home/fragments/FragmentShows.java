package com.finalproject.ui.user.activity_home.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finalproject.R;
import com.finalproject.adapter.ShowsAdapter;
import com.finalproject.databinding.FragmentShowsBinding;
import com.finalproject.mvvm.FragmentShowMVVM;
import com.finalproject.ui.activity_base.BaseFragment;
import com.finalproject.ui.user.activity_home.HomeActivity;
import com.finalproject.ui.user.activity_show_detiles.ShowDetailsActivity;


public class FragmentShows extends BaseFragment {
    private HomeActivity activity;
    private FragmentShowsBinding binding;
    ShowsAdapter showsAdapter;
    private FragmentShowMVVM mvvm;

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


        mvvm = ViewModelProviders.of(this).get(FragmentShowMVVM.class);
        showsAdapter = new ShowsAdapter(activity, this);
        binding.recyclerShows.setLayoutManager(new GridLayoutManager(activity,2));
        binding.recyclerShows.setAdapter(showsAdapter);
        mvvm.getIsLoading().observe(activity, isLoading -> binding.swipeRef.setRefreshing(isLoading));
        mvvm.getShows().observe(activity, showModels -> {
            if (showModels.size() > 0) {
                binding.progBarShows.setVisibility(View.GONE);
                binding.cardNoData.setVisibility(View.GONE);
                if (showsAdapter != null) {
                    showsAdapter.updateList(showModels);

                }
            } else {
                binding.cardNoData.setVisibility(View.VISIBLE);
            }
        });
        mvvm.getShowData(activity);

    }

    public void navigatetoShowDetilesActivity() {
        Intent i=new Intent(getContext(), ShowDetailsActivity.class);
        startActivity(i);
    }
}