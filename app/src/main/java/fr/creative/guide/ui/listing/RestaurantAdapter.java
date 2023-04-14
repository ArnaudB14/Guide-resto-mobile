package fr.creative.guide.ui.listing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import fr.creative.guide.R;
import fr.creative.guide.models.Restaurant;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

    private int resId;
    private List<Restaurant> originalList;
    private List<Restaurant> filteredList;

    public RestaurantAdapter(@NonNull Context context, int resource, @NonNull List<Restaurant> objects) {
        super(context, resource, objects);
        resId = resource;
        originalList = new ArrayList<>(objects);
        filteredList = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder myViewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resId, parent, false);
            myViewHolder = new ViewHolder();

            myViewHolder.textViewName = convertView.findViewById(R.id.name);
            myViewHolder.textViewCategory = convertView.findViewById(R.id.category);

            convertView.setTag(myViewHolder);
        } else {
            // récupération de l'objet ViewHolder
            myViewHolder = (ViewHolder) convertView.getTag();
        }

        Restaurant item = filteredList.get(position);

        myViewHolder.textViewName.setText(item.getName());
        myViewHolder.textViewCategory.setText(item.getCategory());

        return convertView;
    }

    class ViewHolder {
        TextView textViewName, textViewCategory;
    }

    public void updateList(String query) {
        filteredList.clear();

        for (Restaurant restaurant : originalList) {
            if (restaurant.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(restaurant);
            }
        }

        notifyDataSetChanged();
    }
    public void setData(List<Restaurant> filteredList) {
        this.filteredList = filteredList;
        notifyDataSetChanged();
    }


    public void filterByCategory(String category) {
        filteredList.clear();
        for (Restaurant restaurant : originalList) {
            if (restaurant.getCategory().equals(category)) {
                filteredList.add(restaurant);
            }
        }
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Nullable
    @Override
    public Restaurant getItem(int position) {
        return filteredList.get(position);
    }
}
