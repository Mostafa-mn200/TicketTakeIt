package com.finalproject.ui.owner.activities_owner_home.activity_movies;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.finalproject.R;

import com.finalproject.adapter.CinemaOwnerAdapter;

import com.finalproject.adapter.MoviesFilterAdapter;
import com.finalproject.databinding.ActivityOwnerMoviesBinding;
import com.finalproject.model.FilterModel;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.owner.activity_add_movie.AddMovieActivity;

import java.util.ArrayList;
import java.util.List;

public class OwnerMoviesActivity extends BaseActivity {

    private ActivityOwnerMoviesBinding binding;
    private CinemaOwnerAdapter cinemaOwnerAdapter;
    private List<FilterModel> filterModelList;
    private MoviesFilterAdapter filterAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_movies);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_owner_movies);
        initView();

    }

    private void initView() {
        setUpToolbar(binding.toolbar, getString(R.string.movies_list), R.color.color2, R.color.white);

        filterModelList=new ArrayList<>();
        filterModelList.add(new FilterModel("action"));
        filterModelList.add(new FilterModel("drama"));
        filterModelList.add(new FilterModel("comedy"));
        filterModelList.add(new FilterModel("other"));
        binding.setLang(getLang());
        cinemaOwnerAdapter = new CinemaOwnerAdapter(this);
        filterAdapter=new MoviesFilterAdapter(filterModelList,this);
        binding.recyclerFilter.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.recyclerFilter.setAdapter(filterAdapter);
        binding.recyclerMovies.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerMovies.setAdapter(cinemaOwnerAdapter);
        binding.toolbar.llBack.setOnClickListener(view -> {finish();});

        binding.addaMovie.setOnClickListener(view -> {
            Intent i=new Intent(OwnerMoviesActivity.this, AddMovieActivity.class);
            startActivity(i);
        });
    }

    public void setItemFilter(FilterModel filterModel, int currentPos) {

    }
}