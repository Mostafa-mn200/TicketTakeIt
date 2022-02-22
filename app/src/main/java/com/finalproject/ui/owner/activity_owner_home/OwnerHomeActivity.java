package com.finalproject.ui.owner.activity_owner_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.finalproject.R;
import com.finalproject.databinding.ActivityOwnerHomeBinding;
import com.finalproject.language.Language;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.owner.activity_cinema_owner.CinemaOwnerActivity;
import com.finalproject.ui.owner.activity_theater_owner.TheaterOwnerActivity;

import io.paperdb.Paper;

public class OwnerHomeActivity extends BaseActivity {
    ActivityOwnerHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_owner_home);
        initView();
    }


    private void initView() {
        binding.setLang(getLang());
        binding.cardMoviesOwner.setOnClickListener(view -> {
            Intent i=new Intent(OwnerHomeActivity.this, CinemaOwnerActivity.class);
            startActivity(i);
        });

        binding.cardShowsOwner.setOnClickListener(view -> {
            Intent i=new Intent(OwnerHomeActivity.this, TheaterOwnerActivity.class);
            startActivity(i);
        });
    }

}