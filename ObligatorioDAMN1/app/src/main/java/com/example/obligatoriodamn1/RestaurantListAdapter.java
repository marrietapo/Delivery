package com.example.obligatoriodamn1;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.obligatoriodamn1.model.restaurant.RestaurantData;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder> {


    private final List<RestaurantData> mRestaurantList;
    private String userId;
    private String key;



    public RestaurantListAdapter(List<RestaurantData> restaurantList, String key, String userId) {
        mRestaurantList = restaurantList;
        this.userId = userId;
        this.key = key;


    }

    @NotNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.locals_item, null, false);
        //View mItemView = mInflater.inflate(R.layout.locals_item, parent,false);
        return new RestaurantViewHolder(mItemView, this);
    }

    //enlaza la vista con los elementos
    @Override
    public void onBindViewHolder(RestaurantListAdapter.RestaurantViewHolder holder, int position) {

        holder.asignarDatos(mRestaurantList.get(position));

    }

    //cantidad de items activos en pantalla
    @Override
    public int getItemCount() {
        return mRestaurantList.size();
    }


    //evento click de cada elemento de la pantalla
    class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public final TextView restaurant_item_nombre;
        public final TextView restaurant_item_direccion;
        public final TextView restaurant_item_estado;
        final RestaurantListAdapter mAdapter;


        public RestaurantViewHolder(View itemView, RestaurantListAdapter mAdapter) {
            super(itemView);
            this.restaurant_item_nombre = itemView.findViewById(R.id.list_item_name);
            this.restaurant_item_direccion = itemView.findViewById(R.id.list_item_description);
            this.restaurant_item_estado = itemView.findViewById(R.id.list_item_state);
            this.mAdapter = mAdapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int mPosition = getLayoutPosition();
            RestaurantData element = mRestaurantList.get(mPosition);
            //mRestaurantList.set(mPosition, element);
            //mAdapter.notifyDataSetChanged();
            Log.d("MAIN_ACTIVITY_LOGIN", "El amigo hace click en Id:" + element.local._id);

            Intent intent = new Intent(v.getContext(), RestaurantDataActivity.class);
            //intent.putExtra("Restaurante", element);
            intent.putExtra("Nombre", element.local.nombre);
            intent.putExtra("Direccion", element.local.direccion);
            intent.putExtra("Estado", element.estado);
            intent.putExtra("Id", element.local._id.toString());
            intent.putExtra("UserId", userId);
            intent.putExtra("Token", key);
            v.getContext().startActivity(intent);

        }

        public void asignarDatos(RestaurantData restaurantData) {
            restaurant_item_nombre.setText(restaurantData.local.nombre);
            restaurant_item_direccion.setText(restaurantData.local.direccion);
            restaurant_item_estado.setText(restaurantData.estado);
        }
    }
}
