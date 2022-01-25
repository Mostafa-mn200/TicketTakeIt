package com.finalproject.adapter;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.R;
import com.finalproject.databinding.HistoryItemRowBinding;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Object> list;
    private Context context;
    private LayoutInflater inflater;
    private Fragment fragment;


    public HistoryAdapter(Context context, Fragment fragment) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HistoryItemRowBinding binding= DataBindingUtil.inflate(inflater, R.layout.history_item_row,parent,false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder=(MyHolder) holder;
        myHolder.binding.movieName.setText("SpiderMan");
        myHolder.binding.llHeader.setOnClickListener(view -> {
            if (myHolder.binding.cardImage.getVisibility()==View.GONE){
                TransitionManager.beginDelayedTransition(myHolder.binding.cardItem,new AutoTransition());
                myHolder.binding.arrowClicked.setImageResource(R.drawable.ic_arrow_up);
                myHolder.binding.cardImage.setVisibility(View.VISIBLE);
            }else {
                TransitionManager.beginDelayedTransition(myHolder.binding.cardItem,new AutoTransition());
                myHolder.binding.arrowClicked.setImageResource(R.drawable.ic_arrow_down);
                myHolder.binding.cardImage.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 5;
        }
    }

    public void updateList(List<Object> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public HistoryItemRowBinding binding;

        public MyHolder(HistoryItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
