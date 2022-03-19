package com.finalproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.finalproject.R;
import com.finalproject.databinding.SpinnerFilterRowBinding;
import com.finalproject.model.FilterModel;

import java.util.List;

public class SpinnerFilterAdapter extends BaseAdapter {
    private List<FilterModel> modelList;
    private Context context;

    public SpinnerFilterAdapter(List<FilterModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (modelList==null){
            return 0;
        }else {
            return modelList.size();
        }

    }

    @Override
    public Object getItem(int i) {
        return modelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") SpinnerFilterRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.spinner_filter_row,viewGroup,false);
        binding.setTitle(modelList.get(i).getTitle());
        return binding.getRoot();
    }

    public void updateList(List<FilterModel> modelList){
        if (modelList!=null){
            this.modelList = modelList;
        }
        notifyDataSetChanged();
    }
}
