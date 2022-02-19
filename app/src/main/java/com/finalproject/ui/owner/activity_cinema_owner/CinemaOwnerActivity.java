package com.finalproject.ui.owner.activity_cinema_owner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.finalproject.R;

import com.finalproject.adapter.CinemaOwnerAdapter;
import com.finalproject.adapter.ComingSoonDetailsAdapter;

import com.finalproject.databinding.ActivityCinemaOwnerBinding;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.owner.activity_add_movie.AddMovieActivity;

public class CinemaOwnerActivity extends BaseActivity {

    ActivityCinemaOwnerBinding binding;
    CinemaOwnerAdapter mcinemaOwnerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_owner);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_cinema_owner);
        initView();

    }

    private void initView() {
        mcinemaOwnerAdapter = new CinemaOwnerAdapter(this);
        binding.cinemaMoviesRecyclerO.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.cinemaMoviesRecyclerO.setAdapter(mcinemaOwnerAdapter);

        binding.llBack.setOnClickListener(view -> {finish();});

        binding.fab.setOnClickListener(view -> {
            //Intent i=new Intent(CinemaOwnerActivity.this, AddMovieActivity.class);
            //startActivity(i);
        });
    }
}