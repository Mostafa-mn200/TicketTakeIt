package com.finalproject.ui.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finalproject.R;
import com.finalproject.databinding.FragmentPaymentMetodBinding;
import com.finalproject.ui.activity_booking_seats.BookingSeasActivity;

import com.finalproject.ui.activity_home.HomeActivity;
import com.finalproject.ui.ctivity_booking_showSeats.BookingShowSeatsActivity;

public class PaymentMetodFragment extends AppCompatDialogFragment {

    FragmentPaymentMetodBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment_metod, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }
    private void initView() {
        binding.fFawry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.fFawry.setBackgroundResource(R.drawable.small_stroke_primary2);
                binding.fVod.setBackgroundResource(0);
            }
        });
        binding.fVod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.fVod.setBackgroundResource(R.drawable.small_stroke_primary2);
                binding.fFawry.setBackgroundResource(0);
            }
        });
    }
}