package com.finalproject.ui.owner.activities_owner_home.activity_shows;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.finalproject.R;
import com.finalproject.adapter.TheaterOwnerAdapter;
import com.finalproject.databinding.ActivityOwnerShowsBinding;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.owner.activity_add_show.AddShowActivity;

public class OwnerShowsActivity extends BaseActivity {
    private ActivityOwnerShowsBinding binding;
    TheaterOwnerAdapter theaterOwnerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_owner_shows);
        initView();
    }


    private void initView() {
        setUpToolbar(binding.toolbar, getString(R.string.shows_list), R.color.color2, R.color.white);
        binding.setLang(getLang());
        theaterOwnerAdapter = new TheaterOwnerAdapter(this);
        binding.recViewShowOwner.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.recViewShowOwner.setAdapter(theaterOwnerAdapter);

        binding.toolbar.llBack.setOnClickListener(view -> {finish();});

        binding.addaShow.setOnClickListener(view -> {
            Intent i=new Intent(OwnerShowsActivity.this, AddShowActivity.class);
            startActivity(i);
        });
    }
}