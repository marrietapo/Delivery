package com.example.obligatoriodamn1;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.obligatoriodamn1.model.meal.MealQuantity;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {


    private final List<MealQuantity> mOrders;

    public OrderAdapter(List<MealQuantity> list) {
        mOrders = list;
    }

    @NotNull
    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, null, false);
        return new OrderViewHolder(mItemView, this);
    }

    //enlaza la vista con los elementos
    @Override
    public void onBindViewHolder(OrderAdapter.OrderViewHolder holder, int position) {

        holder.asignarDatos(mOrders.get(position));

    }

    //cantidad de items activos en pantalla
    @Override
    public int getItemCount() {
        return mOrders.size();
    }


    //evento click de cada elemento de la pantalla
    static class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public final TextView meal_item_name;
        public final TextView meal_item_qty;
        final OrderAdapter mAdapter;


        public OrderViewHolder(View itemView, OrderAdapter mAdapter) {
            super(itemView);
            this.meal_item_name = itemView.findViewById(R.id.editTextOrderItemName);
            this.meal_item_qty = itemView.findViewById(R.id.editTextOrderItemQty);
            this.mAdapter = mAdapter;

        }

        @Override
        public void onClick(View v) {

        }

        public void asignarDatos(MealQuantity mealData) {
            meal_item_name.setText(mealData.nombre);
            meal_item_qty.setText(mealData.cantidad);
        }
    }
}
