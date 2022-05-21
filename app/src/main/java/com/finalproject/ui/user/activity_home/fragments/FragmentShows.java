package com.finalproject.ui.user.activity_home.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finalproject.R;
import com.finalproject.adapter.ShowsAdapter;
import com.finalproject.databinding.FragmentShowsBinding;
import com.finalproject.model.PostModel;
import com.finalproject.mvvm.FragmentShowMVVM;
import com.finalproject.ui.common_uis.activity_base.BaseFragment;
import com.finalproject.ui.common_uis.activity_login.LoginActivity;
import com.finalproject.ui.user.activity_home.HomeActivity;
import com.finalproject.ui.user.activity_details.DetailsActivity;


public class FragmentShows extends BaseFragment {
    private HomeActivity activity;
    private FragmentShowsBinding binding;
    private ShowsAdapter showsAdapter;
    private FragmentShowMVVM mvvm;

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (HomeActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shows, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {

        mvvm = ViewModelProviders.of(this).get(FragmentShowMVVM.class);
        showsAdapter = new ShowsAdapter(activity, this);
        binding.recyclerShows.setLayoutManager(new GridLayoutManager(activity,2));
        binding.recyclerShows.setAdapter(showsAdapter);
        mvvm.getIsLoading().observe(activity, isLoading -> binding.swipeRef.setRefreshing(isLoading));
        binding.swipeRef.setColorSchemeResources(R.color.primary_dark2);
        mvvm.getShowsSuccess().observe(activity, showModels -> {
            if (showModels.size() > 0) {
                binding.progBarShows.setVisibility(View.GONE);
                binding.cardNoData.setVisibility(View.GONE);
                if (showsAdapter != null) {
                    showsAdapter.updateList(showModels);
                }
            } else {
                binding.cardNoData.setVisibility(View.VISIBLE);
            }
        });
        mvvm.getShowData(activity,null,null,getUserModel().getData().getId());
        binding.swipeRef.setOnRefreshListener(() -> {
            mvvm.getShowData(activity,binding.edtSearch.getText().toString(),null,getUserModel().getData().getId());
        });

        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mvvm.getShowData(activity,s.toString(),null,getUserModel().getData().getId());
            }
        });
    }

    public void navigateToShowDetailsActivity(PostModel postModel, int position) {
        if (getUserModel()!=null){
            Intent intent=new Intent(getContext(), DetailsActivity.class);
            intent.putExtra("post_id", postModel.getId());
            startActivity(intent);
        }else {
            navigateToLoginActivity();
        }

    }

    private void navigateToLoginActivity() {
        Intent intent=new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }
}