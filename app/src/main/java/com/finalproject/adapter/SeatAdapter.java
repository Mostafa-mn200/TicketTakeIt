package com.finalproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.R;
import com.finalproject.databinding.SeatRowBinding;
import com.finalproject.model.SeatModel;
import com.finalproject.ui.user.activity_booking_seats.BookingSeatsActivity;

import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.MyHolder> {

    private List<SeatModel> seatModelList;
    private Context context;

    public SeatAdapter(List<SeatModel> seatModelList, Context context) {
        this.seatModelList = seatModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SeatRowBinding seatRowBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.seat_row, parent, false);
        return new MyHolder(seatRowBinding);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
//        SeatModel seatModel = seatModelList.get(position);
//        seatModel.setChecked(false);
        myHolder.seatRowBinding.setModel(seatModelList.get(position));

            myHolder.itemView.setOnClickListener(view -> {
                SeatModel seatModel1 = seatModelList.get(holder.getLayoutPosition());

                if (seatModel1.isSelected()) {
                    seatModel1.setSelected(false);
                } else {
                    seatModel1.setSelected(true);
                }
                seatModelList.set(holder.getLayoutPosition(), seatModel1);
                notifyItemChanged(holder.getLayoutPosition());
            });

    }

    @Override
    public int getItemCount() {
        return seatModelList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private SeatRowBinding seatRowBinding;

        public MyHolder(@NonNull SeatRowBinding seatRowBinding) {
            super(seatRowBinding.getRoot());
            this.seatRowBinding = seatRowBinding;
        }
    }
}
