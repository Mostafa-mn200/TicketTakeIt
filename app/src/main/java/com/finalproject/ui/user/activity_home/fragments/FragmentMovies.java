package com.finalproject.ui.user.activity_home.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finalproject.R;
import com.finalproject.adapter.MoviesAdapter;
import com.finalproject.adapter.MoviesFilterAdapter;
import com.finalproject.databinding.FragmentMoviesBinding;
import com.finalproject.model.FilterModel;
import com.finalproject.ui.activity_base.BaseFragment;
import com.finalproject.ui.user.activity_home.HomeActivity;
import com.finalproject.ui.user.activity_trailar_movie.MovieDetailsActivity;

import java.util.ArrayList;
import java.util.List;


public class FragmentMovies extends BaseFragment {
    private HomeActivity activity;
    private FragmentMoviesBinding binding;
    private MoviesFilterAdapter moviesFilterAdapter;
    private MoviesAdapter moviesAdapter;
    private List<FilterModel> filterModelList;

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (HomeActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        filterModelList=new ArrayList<>();

        filterModelList.add(new FilterModel("action"));
        filterModelList.add(new FilterModel("drama"));
        filterModelList.add(new FilterModel("comedy"));
        filterModelList.add(new FilterModel("other"));
        moviesFilterAdapter = new MoviesFilterAdapter(filterModelList,activity);
        binding.recyclerFilter.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));
        binding.recyclerFilter.setAdapter(moviesFilterAdapter);

        moviesAdapter = new MoviesAdapter(activity, this);
        binding.recyclerMovies.setLayoutManager(new GridLayoutManager(activity, 2));
        binding.recyclerMovies.setAdapter(moviesAdapter);
    }

    public void navigatetoMovieTrailerActivity() {
        Intent i = new Intent(getContext(), MovieDetailsActivity.class);
        startActivity(i);
    }

    public void setItemFilter(FilterModel filterModel, int currentPos) {
//        mvvm.setSelectedFilterPos(currentPos);
//        updateFilters(filterModel);
    }
}