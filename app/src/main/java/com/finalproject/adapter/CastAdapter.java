package com.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.R;
import com.finalproject.databinding.CastItemBinding;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Object> list ;
    private Context context;
    private LayoutInflater inflater;

    public CastAdapter( Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        CastItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.cast_item, parent, false);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        VH vh=(VH)holder;
        vh.binding.castImg.setImageResource(R.drawable.venom);
        vh.binding.authorName.setText("Hero1");
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 8;
        }
    }

    public void updateList(List<Object> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class VH extends RecyclerView.ViewHolder {
        public CastItemBinding binding;
        public VH( CastItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
