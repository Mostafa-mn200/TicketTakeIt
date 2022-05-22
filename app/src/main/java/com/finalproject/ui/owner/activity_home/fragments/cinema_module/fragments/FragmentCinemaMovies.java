package com.finalproject.ui.owner.activity_home.fragments.cinema_module.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.finalproject.R;
import com.finalproject.adapter.OwnerHistoryAdapter;
import com.finalproject.databinding.FragmentCinemaMoviesBinding;
import com.finalproject.model.PostModel;
import com.finalproject.mvvm.FragmentCinemaDataMvvm;
import com.finalproject.ui.common_uis.activity_base.BaseFragment;
import com.finalproject.ui.owner.activity_home.OwnerHomeActivity;
import com.finalproject.ui.owner.activity_owner_booking_details.OwnerBookingDetailsActivity;

import java.util.ArrayList;
import java.util.List;


public class FragmentCinemaMovies extends BaseFragment {
    private OwnerHomeActivity activity;
    private FragmentCinemaMoviesBinding binding;
    private OwnerHistoryAdapter historyAdapter;
    private FragmentCinemaDataMvvm mvvm;
    private List<PostModel> movieModelList;
    private ActivityResultLauncher<Intent> launcher;
    private int req;


    public static FragmentCinemaMovies newInstance() {
        FragmentCinemaMovies fragment = new FragmentCinemaMovies();

        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (OwnerHomeActivity) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cinema_movies, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        movieModelList = new ArrayList<>();
        mvvm = ViewModelProviders.of(this).get(FragmentCinemaDataMvvm.class);

        mvvm.getIsLoading().observe(activity, isLoading -> {
            binding.swipeRef.setRefreshing(isLoading);
        });

        mvvm.getOnDataSuccess().observe(activity, movieModels -> {
            Log.e("fllfl",movieModels.size()+"");
            if (movieModels != null && movieModels.size() > 0) {
                binding.tvNoData.setVisibility(View.GONE);
                movieModelList.clear();
                movieModelList.addAll(movieModels);
                if (historyAdapter != null) {
                    historyAdapter.updateList(movieModels);
                }
                if(movieModelList.size()==0){
                    binding.tvNoData.setVisibility(View.VISIBLE);

                }
            } else {
               // Log.e("kkkkk","lllll");
                binding.tvNoData.setVisibility(View.VISIBLE);
                historyAdapter.updateList(new ArrayList<>());
            }
        });

        mvvm.getMoviesOrShows(getUserModel().getData().getCinema().getId(), "move");
        historyAdapter = new OwnerHistoryAdapter(activity, this);
        binding.recViewMovies.setLayoutManager(new GridLayoutManager(activity, 2));
        binding.recViewMovies.setAdapter(historyAdapter);
        binding.swipeRef.setColorSchemeResources(R.color.primary_dark2);
        binding.swipeRef.setOnRefreshListener(() -> {
            mvvm.getMoviesOrShows(getUserModel().getData().getCinema().getId(), "move");
        });

        mvvm.getRemove().observe(activity, pos -> {
            if (historyAdapter != null) {

                Toast.makeText(activity, R.string.movie_removed, Toast.LENGTH_SHORT).show();
                movieModelList.remove(pos);
                mvvm.getOnDataSuccess().setValue(movieModelList);
            }else {
                binding.tvNoData.setVisibility(View.VISIBLE);
            }

        });
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (req == 1 && result.getResultCode()== Activity.RESULT_OK) {
               // mvvm.getMoviesOrShows(getUserModel().getData().getCinema().getId(), "move");
                if (movieModelList.size()>0){
                    historyAdapter.updateList(movieModelList);
                }else {
                    movieModelList.clear();
                    historyAdapter.updateList(new ArrayList<>());
                    historyAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    public void navigateToDetails(int adapterPosition, PostModel postModel) {

        Intent intent = new Intent(activity, OwnerBookingDetailsActivity.class);
        intent.putExtra("model", postModel);
        startActivity(intent);
    }

    public void delete(int adapterPosition, PostModel postModel) {
        req=1;
        Log.e("llll",adapterPosition+"");
        mvvm.removeFromCinema(activity, getUserModel().getData().getCinema().getId(), postModel.getId(), adapterPosition);
    }
}