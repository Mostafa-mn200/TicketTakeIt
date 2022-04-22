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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finalproject.R;
import com.finalproject.adapter.MoviesAdapter;
import com.finalproject.adapter.MoviesFilterAdapter;
import com.finalproject.databinding.FragmentMoviesBinding;
import com.finalproject.model.CategoryModel;
import com.finalproject.model.FilterModel;
import com.finalproject.model.MovieModel;
import com.finalproject.mvvm.FragmentHomeMVVM;
import com.finalproject.mvvm.FragmentMoviesMvvm;
import com.finalproject.ui.activity_base.BaseFragment;
import com.finalproject.ui.user.activity_home.HomeActivity;
import com.finalproject.ui.user.activity_trailar_movie.MovieDetailsActivity;
import com.google.android.exoplayer2.util.Log;

import java.util.ArrayList;
import java.util.List;


public class FragmentMovies extends BaseFragment {
    private HomeActivity activity;
    private FragmentMoviesBinding binding;
    private MoviesFilterAdapter moviesFilterAdapter;
    private MoviesAdapter moviesAdapter;
    private FragmentMoviesMvvm mvvm;
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
        mvvm = ViewModelProviders.of(this).get(FragmentMoviesMvvm.class);

        mvvm.getIsLoading().observe(activity, isLoading -> {
            binding.swipeRef.setRefreshing(isLoading);
        });
        mvvm.getOnCategorySuccess().observe(activity, categoryModels -> {
            if (categoryModels.size()>0){
                if (moviesFilterAdapter!=null){
                    moviesFilterAdapter.updateList(categoryModels);
                }
            }
        });
        mvvm.getCategory();
        mvvm.getOnMoviesSuccess().observe(activity, movieModels -> {
            if (movieModels.size()>0){
                binding.cardNoData.setVisibility(View.GONE);
            }else {
                binding.cardNoData.setVisibility(View.VISIBLE);
            }
            if (moviesAdapter!=null){
                moviesAdapter.updateList(movieModels);
            }
        });
        mvvm.getMovies();
        binding.swipeRef.setOnRefreshListener(() -> {
            if (mvvm.getCategoryId().getValue()!=null){
                mvvm.getMovies();
            }else {
                binding.swipeRef.setRefreshing(false);
            }
        });
        moviesFilterAdapter = new MoviesFilterAdapter(activity,this);
        binding.recyclerFilter.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));
        binding.recyclerFilter.setAdapter(moviesFilterAdapter);


        moviesAdapter = new MoviesAdapter(activity, this);
        binding.recyclerMovies.setLayoutManager(new GridLayoutManager(activity, 2));
        binding.recyclerMovies.setAdapter(moviesAdapter);
    }



    public void setItemCategory(CategoryModel categoryModel, int currentPos) {
        mvvm.getCategoryId().setValue(categoryModel.getId());
//        mvvm.setSelectedCategoryPos(currentPos);
        mvvm.getMovies();
    }

    public void navigateToMovieDetails(MovieModel movieModel) {
        Intent intent=new Intent(activity,MovieDetailsActivity.class);
        intent.putExtra("movie_id",movieModel.getId());
        startActivity(intent);
    }
}