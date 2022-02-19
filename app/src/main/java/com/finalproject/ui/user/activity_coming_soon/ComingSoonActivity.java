package com.finalproject.ui.user.activity_coming_soon;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.finalproject.R;
import com.finalproject.adapter.ComingSoonDetailsAdapter;
import com.finalproject.databinding.ActivityComingSoonBinding;
import com.finalproject.language.Language;
import com.finalproject.ui.activity_base.BaseActivity;

import java.util.Locale;

import io.paperdb.Paper;

public class ComingSoonActivity extends BaseActivity {
    private String lang;
    private ActivityComingSoonBinding binding;
    private ComingSoonDetailsAdapter mallComingSoonDetailsAdapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(Language.updateResources(newBase, "en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coming_soon);
        initView();
    }


    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(getLang());

        mallComingSoonDetailsAdapter = new ComingSoonDetailsAdapter(this);
        binding.comingRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.comingRecycler.setAdapter(mallComingSoonDetailsAdapter);
        binding.llBack.setOnClickListener(view -> {finish();});
    }
}