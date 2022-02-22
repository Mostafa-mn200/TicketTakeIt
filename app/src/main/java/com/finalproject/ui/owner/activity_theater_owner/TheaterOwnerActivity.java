package com.finalproject.ui.owner.activity_theater_owner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.finalproject.R;
import com.finalproject.adapter.CinemaOwnerAdapter;
import com.finalproject.adapter.TheaterOwnerAdapter;
import com.finalproject.databinding.ActivityTheaterOwnerBinding;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.owner.activity_add_movie.AddMovieActivity;
import com.finalproject.ui.owner.activity_add_show.AddShowActivity;
import com.finalproject.ui.owner.activity_cinema_owner.CinemaOwnerActivity;

public class TheaterOwnerActivity extends BaseActivity {
    ActivityTheaterOwnerBinding binding;
    TheaterOwnerAdapter theaterOwnerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_theater_owner);
        initView();
    }


    private void initView() {
        binding.setLang(getLang());
        theaterOwnerAdapter = new TheaterOwnerAdapter(this);
        binding.recViewShowOwner.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.recViewShowOwner.setAdapter(theaterOwnerAdapter);

        binding.llBack.setOnClickListener(view -> {finish();});

        binding.addaShow.setOnClickListener(view -> {
            Intent i=new Intent(TheaterOwnerActivity.this, AddShowActivity.class);
            startActivity(i);
        });
    }
}