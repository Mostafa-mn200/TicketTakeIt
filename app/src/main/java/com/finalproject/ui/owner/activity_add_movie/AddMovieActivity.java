package com.finalproject.ui.owner.activity_add_movie;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.finalproject.R;
import com.finalproject.databinding.ActivityAddMovieBinding;
import com.finalproject.ui.activity_base.BaseActivity;

public class AddMovieActivity extends BaseActivity {
    private ActivityAddMovieBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_movie);
        initView();
    }

    private void initView() {

    }
}