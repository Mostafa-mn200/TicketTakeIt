package com.finalproject.ui.owner.activities_owner_home.activity_list_of_bookings;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.finalproject.R;
import com.finalproject.adapter.HistoryAdapter;
import com.finalproject.adapter.OwnerHistoryAdapter;
import com.finalproject.databinding.ActivityBookingListBinding;
import com.finalproject.ui.activity_base.BaseActivity;

public class BookingListActivity extends BaseActivity {
    private ActivityBookingListBinding binding;
    private OwnerHistoryAdapter historyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_booking_list);
        initView();
    }

    private void initView() {
        setUpToolbar(binding.toolbar, getString(R.string.bookings_list), R.color.color2, R.color.white);
        historyAdapter = new OwnerHistoryAdapter(this);
        binding.recyclerHistory.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerHistory.setAdapter(historyAdapter);
        binding.toolbar.llBack.setOnClickListener(view -> finish());
    }
}