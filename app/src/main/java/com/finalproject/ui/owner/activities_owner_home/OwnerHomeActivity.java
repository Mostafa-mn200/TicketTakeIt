package com.finalproject.ui.owner.activities_owner_home;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;


import com.finalproject.R;
import com.finalproject.databinding.ActivityOwnerHomeBinding;
import com.finalproject.language.Language;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.activity_login.LoginActivity;
import com.finalproject.ui.owner.activities_owner_home.activity_list_of_bookings.BookingListActivity;
import com.finalproject.ui.owner.activities_owner_home.activity_movies.OwnerMoviesActivity;
import com.finalproject.ui.owner.activities_owner_home.activity_shows.OwnerShowsActivity;
import com.finalproject.ui.user.activity_language.LanguageActivity;

import io.paperdb.Paper;

public class OwnerHomeActivity extends BaseActivity {
    private ActivityOwnerHomeBinding binding;
    private int req;
    private ActivityResultLauncher<Intent> launcher;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_owner_home);
        initView();
    }


    private void initView() {
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (req==1&&result.getResultCode()==RESULT_OK&&result.getData()!=null){
                String lang = result.getData().getStringExtra("lang");
                refreshActivity(lang);
            }
        });
        setSupportActionBar(binding.toolbar);
        Paper.init(this);

        binding.setLang(getLang());

        binding.cardMoviesOwner.setOnClickListener(view -> {
            Intent i = new Intent(OwnerHomeActivity.this, OwnerMoviesActivity.class);
            startActivity(i);
        });

        binding.cardShowsOwner.setOnClickListener(view -> {
            Intent i = new Intent(OwnerHomeActivity.this, OwnerShowsActivity.class);
            startActivity(i);
        });

        binding.listOfBookings.setOnClickListener(view -> {
            Intent intent=new Intent(OwnerHomeActivity.this, BookingListActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.owner_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.lang) {
            req = 1;
            Intent intent = new Intent(this, LanguageActivity.class);
            launcher.launch(intent);
        } else if (id == R.id.lLogOut) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void refreshActivity(String lang) {
        Paper.book().write("lang", lang);
        Language.setNewLocale(this, lang);
        new Handler()
                .postDelayed(() -> {

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }, 500);


    }


}