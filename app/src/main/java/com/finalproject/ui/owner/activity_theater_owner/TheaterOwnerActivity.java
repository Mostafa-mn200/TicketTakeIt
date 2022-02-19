package com.finalproject.ui.owner.activity_theater_owner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.finalproject.R;
import com.finalproject.adapter.CinemaOwnerAdapter;
import com.finalproject.adapter.TheaterOwnerAdapter;
import com.finalproject.databinding.ActivityTheaterOwnerBinding;

public class TheaterOwnerActivity extends AppCompatActivity {
    ActivityTheaterOwnerBinding binding;
    TheaterOwnerAdapter mtheaterOwnerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_theater_owner);
        initView();
    }


    private void initView() {
        mtheaterOwnerAdapter = new TheaterOwnerAdapter(this);
        binding.TheaterShowsRecyclerO.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.TheaterShowsRecyclerO.setAdapter(mtheaterOwnerAdapter);

        binding.llBack.setOnClickListener(view -> {finish();});

        binding.fabShow.setOnClickListener(view -> {
            //Intent i=new Intent(CinemaOwnerActivity.this, AddMovieActivity.class);
            //startActivity(i);
        });
    }
}