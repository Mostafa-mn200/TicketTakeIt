package com.finalproject.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.R;
import com.finalproject.databinding.CinemaItemBinding;
import com.finalproject.ui.activity_booking_seats.BookingSeasActivity;
import com.finalproject.ui.activity_cinema_users.CinemasUserActivity;
import com.finalproject.ui.activity_maps.CinemaMapsActivity;
import com.finalproject.ui.fragments.FragmentMovies;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CinemaUsersModel extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Object> cinemaList ;
    private Context context;

    CinemasUserActivity cinemasUserActivity;
    private LayoutInflater inflater;


    public CinemaUsersModel(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        CinemaItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.cinema_item, parent, false);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;
        //vh.binding.NameOfcinema.setText("City stars cinema");
//        vh.binding.bookingImg.setImageResource(R.drawable.booking);
//        vh.binding.mapImg.setImageResource(R.drawable.map);

        vh.binding.ImgBook.setOnClickListener(view -> {

            cinemasUserActivity=(CinemasUserActivity) context;
            cinemasUserActivity.navigateToBookingSeasActivity();
        });


    }

    @Override
    public int getItemCount() {
        if (cinemaList != null) {
            return cinemaList.size();
        } else {
            return 8;
        }
    }
    public void updateList(List<Object> list) {
        this.cinemaList = list;
        notifyDataSetChanged();
    }


    public static class VH extends RecyclerView.ViewHolder{
        public CinemaItemBinding binding;
        public VH( CinemaItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
