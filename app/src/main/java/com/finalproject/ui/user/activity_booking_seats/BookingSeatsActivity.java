package com.finalproject.ui.user.activity_booking_seats;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.finalproject.R;
import com.finalproject.adapter.DayAdapter;
import com.finalproject.adapter.SeatAdapter;
import com.finalproject.adapter.TimeAdapter;
import com.finalproject.databinding.ActivityBookingSeatsBinding;
import com.finalproject.language.Language;
import com.finalproject.model.DayModel;
import com.finalproject.model.SeatModel;
import com.finalproject.model.TimeModel;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.user.activity_cinema_users.CinemasUserActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class BookingSeatsActivity extends BaseActivity {
    private String lang;
    private ActivityBookingSeatsBinding binding;
    private DayModel dayModel;
    private TimeModel timeModel;
    private List<DayModel> dayModelList;
    private List<TimeModel> timeModelList;
    private DayAdapter dayAdapter;
    private TimeAdapter timeAdapter;
    private SeatAdapter seatAdapter;
//    private SeatModel seatModel;
    private List<SeatModel> seatModelList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_booking_seats);
        initView();


    }

    private void initView() {
        setUpToolbar(binding.toolbar, getString(R.string.booking_seats), R.color.color2, R.color.white);
//        Paper.init(this);
//        lang = getLang();
//        binding.setLang(getLang());
        seatModelList=new ArrayList<>();
        seatModelList.add(new SeatModel());
        seatModelList.add(new SeatModel());
        seatModelList.add(new SeatModel());
        seatModelList.add(new SeatModel());
        seatModelList.add(new SeatModel());
        seatModelList.add(new SeatModel());
        seatModelList.add(new SeatModel());
        seatModelList.add(new SeatModel());
        seatModelList.add(new SeatModel());
        seatModelList.add(new SeatModel());
        seatModelList.add(new SeatModel());
        seatModelList.add(new SeatModel());
        SeatModel seatModel=seatModelList.get(0);
        seatModel.setChecked(true);
        seatModelList.set(0,seatModel);
        seatAdapter=new SeatAdapter(seatModelList,this);
        binding.recViewSeats.setLayoutManager(new GridLayoutManager(this,7));
        binding.recViewSeats.setAdapter(seatAdapter);
        dayModelList=new ArrayList<>();
        timeModelList=new ArrayList<>();
        dayModelList.add(new DayModel("SATURDAY"));
        dayModelList.add(new DayModel("SUNDAY"));
        dayModelList.add(new DayModel("MONDAY"));
        dayModelList.add(new DayModel("TUESDAY"));
        dayModelList.add(new DayModel("WEDNESDAY"));
        dayModelList.add(new DayModel("THURSDAY"));
        dayModelList.add(new DayModel("FRIDAY"));

        timeModelList.add(new TimeModel("10-12"," am"));
        timeModelList.add(new TimeModel("12-3"," pm"));
        timeModelList.add(new TimeModel("3-6"," am"));
        timeModelList.add(new TimeModel("6-9"," pm"));
        timeModelList.add(new TimeModel("9-12"," am"));

        dayAdapter = new DayAdapter(dayModelList, this);
        timeAdapter=new TimeAdapter(timeModelList,this);
        binding.recViewDay.setLayoutManager(new GridLayoutManager(this,3));
        binding.recViewTime.setLayoutManager(new GridLayoutManager(this,3));
        binding.recViewDay.setAdapter(dayAdapter);
        binding.recViewTime.setAdapter(timeAdapter);

        binding.llTime.setEnabled(false);
        binding.llDay.setOnClickListener(view -> binding.flDay.setVisibility(View.VISIBLE));
        binding.llTime.setOnClickListener(view -> binding.flTime.setVisibility(View.VISIBLE));
        binding.closeDay.setOnClickListener(view -> binding.flDay.setVisibility(View.GONE));
        binding.closeTime.setOnClickListener(view -> binding.flTime.setVisibility(View.GONE));
        binding.tvDone1.setOnClickListener(view -> {
            binding.flDay.setVisibility(View.GONE);
            binding.tvDay.setText(dayModel.getDay_text());
            binding.llTime.setEnabled(true);
        });
        binding.tvDone2.setOnClickListener(view -> {
            binding.flTime.setVisibility(View.GONE);
            binding.tvTime.setText(timeModel.getTime_text()+timeModel.getType());
        });
        binding.toolbar.llBack.setOnClickListener(view -> {
            Intent i=new Intent(BookingSeatsActivity.this, CinemasUserActivity.class);
            startActivity(i);
            finish();
        });
    }

    public void setDayItem(DayModel model) {
        binding.tvDone1.setVisibility(View.VISIBLE);
        this.dayModel = model;
    }

    public void setTimeItem(TimeModel model) {
        binding.tvDone2.setVisibility(View.VISIBLE);
        this.timeModel = model;
    }
}