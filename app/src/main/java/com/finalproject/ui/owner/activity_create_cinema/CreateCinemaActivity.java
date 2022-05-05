package com.finalproject.ui.owner.activity_create_cinema;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.finalproject.R;
import com.finalproject.databinding.ActivityCreateCinemaBinding;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.owner.activity_home.OwnerHomeActivity;

public class CreateCinemaActivity extends BaseActivity {
    private ActivityCreateCinemaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_create_cinema);
        initView();
    }

    private void initView() {
        binding.btnCreate.setOnClickListener(view -> {
            Intent intent=new Intent(CreateCinemaActivity.this, OwnerHomeActivity.class);
            startActivity(intent);
        });
    }
}