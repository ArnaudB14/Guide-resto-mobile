package fr.creative.guide.ui.listing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.creative.guide.R;
import fr.creative.guide.models.Hotel;

public class AdapterHotel extends RecyclerView.Adapter<AdapterHotel.ViewHolder> {
    private int resId;
    private List<Hotel> hotels;
    private OnAdapterItemClick onAdapterItemClick;
    public AdapterHotel(int list_item, List<Hotel> hotelList, OnAdapterItemClick onAdapterItemClick) {
        resId = list_item;
        hotels = hotelList;
        this.onAdapterItemClick = onAdapterItemClick;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, category;

        public ViewHolder(@NonNull View itemView, OnAdapterItemClick adapterItemClick) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            category = itemView.findViewById(R.id.category);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);

        return new ViewHolder(myView, onAdapterItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hotel item = hotels.get(position);
        holder.name.setText(item.getName());
        holder.category.setText(item.getCategory());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdapterItemClick.onItemClick(holder.itemView, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }
}
