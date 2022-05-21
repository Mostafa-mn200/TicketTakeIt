package com.finalproject.ui.owner.activity_home.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.finalproject.R;
import com.finalproject.adapter.DayOwnerAdapter;
import com.finalproject.adapter.ShowsAdapter;
import com.finalproject.adapter.TimeOwnerAdapter;
import com.finalproject.databinding.FragmentOwnerShowsBinding;
import com.finalproject.model.AddDayTimeModel;
import com.finalproject.model.Day;
import com.finalproject.model.PostModel;
import com.finalproject.model.Time;
import com.finalproject.mvvm.FragmentShowMVVM;
import com.finalproject.ui.common_uis.activity_base.BaseFragment;
import com.finalproject.ui.owner.activity_home.OwnerHomeActivity;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class FragmentOwnerShows extends BaseFragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener
{
    private OwnerHomeActivity activity;
    private FragmentOwnerShowsBinding binding;
    private ShowsAdapter showsAdapter;
    private FragmentShowMVVM mvvm;
    private BottomSheetBehavior behavior;
    private DayOwnerAdapter dayAdapter;
    private List<Day> dayModelList;
    private TimeOwnerAdapter timeOwnerAdapter;
    private List<PostModel> showModelList;
    private String date = null;
    private List<Time> list;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private AddDayTimeModel addDayTimeModel;
    private Day dayModel;

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (OwnerHomeActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_owner_shows, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }
    private void initView() {
        showModelList = new ArrayList<>();
        dayModelList = new ArrayList<>();
        list = new ArrayList<>();
        behavior = BottomSheetBehavior.from(binding.sheet.root);
        binding.sheet.setLang(getLang());
        mvvm = ViewModelProviders.of(this).get(FragmentShowMVVM.class);

        binding.sheet.llChooseDay.setOnClickListener(view -> datePickerDialog.show(activity.getFragmentManager(), ""));

        createDateDialog();

        createTimeDialog();

        dayAdapter = new DayOwnerAdapter(dayModelList, this, activity);
        binding.sheet.recViewDays.setLayoutManager(new GridLayoutManager(activity, 2, LinearLayoutManager.HORIZONTAL, false));
        binding.sheet.recViewDays.setAdapter(dayAdapter);

        showsAdapter = new ShowsAdapter(activity, this);
        binding.recyclerShows.setLayoutManager(new GridLayoutManager(activity, 2));
        binding.recyclerShows.setAdapter(showsAdapter);

        mvvm.getIsLoading().observe(activity, isLoading -> binding.swipeRef.setRefreshing(isLoading));
        binding.swipeRef.setColorSchemeResources(R.color.colorPrimary);
        mvvm.getShowsSuccess().observe(activity, showModels -> {
            if (showModels.size() > 0) {
                binding.cardNoData.setVisibility(View.GONE);
                if (showsAdapter != null) {
                    showModelList.clear();
                    showModelList.addAll(showModels);
                    showsAdapter.updateList(showModels);
                }
            } else {
                binding.cardNoData.setVisibility(View.VISIBLE);
                showsAdapter.updateList(new ArrayList<>());
            }
        });

        mvvm.getShowData(activity,null,getUserModel().getData().getCinema().getId(),getUserModel().getData().getId());

        binding.swipeRef.setOnRefreshListener(() -> mvvm.getShowData(activity,null,getUserModel().getData().getCinema().getId(),getUserModel().getData().getId()));

        showsAdapter = new ShowsAdapter(activity, this);
        binding.recyclerShows.setLayoutManager(new GridLayoutManager(activity,2));
        binding.recyclerShows.setAdapter(showsAdapter);

        mvvm.addDayTime.observe(activity, addedDayTime -> {
            if (addedDayTime){
                Toast.makeText(activity, "Day and time added successfully", Toast.LENGTH_SHORT).show();
                Toast.makeText(activity, R.string.added_success, Toast.LENGTH_SHORT).show();
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        mvvm.add.observe(activity, added -> {
            if (added) {
                mvvm.AddDayAndTime(activity, addDayTimeModel);
            }
        });
    }



    public void setItemChecked(PostModel postModel, int position) {
        openSheet(postModel, position);
    }


    private void openSheet(PostModel postModel, int position) {
        addDayTimeModel = new AddDayTimeModel();
        addDayTimeModel.setCinema_id(getUserModel().getData().getCinema().getId());
        addDayTimeModel.setPost_id(postModel.getId());
        binding.sheet.btnConfirm.setOnClickListener(view -> {
            if (addDayTimeModel != null) {
                if (dayModelList.size()>0){
                    mvvm.addToCinema(getUserModel().getData().getCinema().getId(), postModel.getId());
                }else {
                    Toast.makeText(activity, R.string.choose_at_least_one_time, Toast.LENGTH_SHORT).show();
                }
            }

        });
        binding.sheet.btnCancel.setOnClickListener(view -> {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            dayModelList.clear();
            list.clear();
            dayAdapter.updateList(dayModelList);
            binding.sheet.tvDate.setText("");
        });

        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        behavior.setDraggable(false);
    }


//    private void setHaveMovie(PostModel postModel, int position) {
//        added = "1";
//        postModel.setAdded(added);
//        showModelList.set(position, postModel);
//        showsAdapter.updateList(showModelList);
//
//    }
//
//    private void setHaveNotMovie(PostModel postModel, int position) {
//        added = "0";
//        postModel.setAdded(added);
//        showModelList.set(position, postModel);
//        showsAdapter.updateList(showModelList);
//    }

    private void createDateDialog() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
        datePickerDialog.dismissOnPause(true);
        datePickerDialog.setAccentColor(ActivityCompat.getColor(activity, R.color.colorPrimary));
        datePickerDialog.setCancelColor(ActivityCompat.getColor(activity, R.color.gray12));
        datePickerDialog.setOkColor(ActivityCompat.getColor(activity, R.color.colorPrimary));
        datePickerDialog.setMinDate(calendar);
        datePickerDialog.setOkText(getString(R.string.select));
        datePickerDialog.setCancelText(getString(R.string.cancel));
        datePickerDialog.setVersion(DatePickerDialog.Version.VERSION_2);

    }

    private void createTimeDialog() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        timePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), false);
        timePickerDialog.dismissOnPause(true);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            if(dayModel !=null&&!dateFormat.parse(dayModel.getDay()).after(dateFormat.parse(dateFormat.format(calendar.getTimeInMillis())))){
                timePickerDialog.setMinTime(calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND));
            }
        } catch (Exception e) {
//            Log.e("ffff",e.toString());
            //  e.printStackTrace();
        }
        timePickerDialog.setAccentColor(ActivityCompat.getColor(activity, R.color.colorPrimary));
        timePickerDialog.setCancelColor(ActivityCompat.getColor(activity, R.color.gray4));
        timePickerDialog.setOkColor(ActivityCompat.getColor(activity, R.color.colorPrimary));
        timePickerDialog.setVersion(TimePickerDialog.Version.VERSION_2);


    }


    @Override
    public void onDateSet(DatePickerDialog datePicker, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
        date = dateFormat.format(new Date(calendar.getTimeInMillis()));
        binding.sheet.tvDate.setText(date);
        Day dayModel = new Day(date);

        boolean inList = isItemInDayList(dayModel);
        if (!inList) {
            dayModel.setTimeModelList(new ArrayList<>());
            dayModelList.add(0, dayModel);
            dayAdapter.updateList(dayModelList);
            addDayTimeModel.setDays(dayModelList);

        } else {
            Toast.makeText(activity, R.string.day_added_before, Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        String time = new SimpleDateFormat("HH:mm", Locale.ENGLISH).format(calendar.getTime());
        Time timeModel = new Time(time);
        boolean inList = isItemInTimeList(timeModel);
        if (!inList) {
            list.add(timeModel);
            timeOwnerAdapter.updateList(list);
        } else {
            Toast.makeText(activity, getResources().getString(R.string.time_added_before), Toast.LENGTH_SHORT).show();
        }
    }

    public void addNewTime(Day dayModel, int adapterPosition, List<Time> timeModels, TimeOwnerAdapter adapter) {
        // list.clear();
        Calendar calendar = Calendar.getInstance();
        this.list = timeModels;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        // dayModel.setTimeModelList(list);
        this.timeOwnerAdapter = adapter;
        this.dayModel =dayModel;
        // adapter.notifyDataSetChanged();
        try {
            if(this.dayModel !=null&&!dateFormat.parse(this.dayModel.getDay()).after(dateFormat.parse(dateFormat.format(calendar.getTimeInMillis())))){
                calendar.setTimeInMillis(System.currentTimeMillis());

                timePickerDialog.setMinTime(calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND));
            }
            else{
                timePickerDialog.setMinTime(0,0,0);
            }
        } catch (Exception e) {
            Log.e("ffff",e.toString());
            //  e.printStackTrace();
        }
        timePickerDialog.show(activity.getFragmentManager(), "");

    }

    public void deleteSelectedDay(int adapterPosition) {
        dayModelList.remove(adapterPosition);
        dayAdapter.notifyItemRemoved(adapterPosition);

    }

    public void deleteSelectedTime(int position, int adapterPosition) {
        dayAdapter.remove(position, adapterPosition);
//        dayModelList.get(position).getTimeModelList().remove(adapterPosition);
//        timeOwnerAdapter.notifyItemRemoved(adapterPosition);
    }

    private boolean isItemInDayList(Day dayModel) {
        for (Day model : dayModelList) {
            if (dayModel.getDay().equals(model.getDay())) {
                return true;
            }
        }
        return false;
    }

    private boolean isItemInTimeList(Time timeModel) {
        for (Time model : list) {
            if (timeModel.getHour().equals(model.getHour())) {
                return true;
            }
        }
        return false;
    }

}