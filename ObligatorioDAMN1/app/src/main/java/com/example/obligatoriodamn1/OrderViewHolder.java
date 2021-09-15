package com.example.obligatoriodamn1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

class OrderViewHolder extends RecyclerView.ViewHolder {
    private final TextView orderItemView;

    private OrderViewHolder(View itemView) {
        super(itemView);
        orderItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        orderItemView.setText(text);
    }

    static OrderViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_order, parent, false);
        return new OrderViewHolder(view);
    }
}
