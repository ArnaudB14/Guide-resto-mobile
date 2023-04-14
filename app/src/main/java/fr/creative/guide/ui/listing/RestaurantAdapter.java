package fr.creative.guide.ui.listing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

import fr.creative.guide.R;
import fr.creative.guide.models.Restaurant;

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

    private int resId;

    public RestaurantAdapter(@NonNull Context context, int resource, @NonNull List<Restaurant> objects) {
        super(context, resource, objects);
        resId = resource;
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

        Restaurant item = getItem(position);

        myViewHolder.textViewName.setText(item.getName());
        myViewHolder.textViewCategory.setText(item.getCategory());

        return convertView;
    }

    class ViewHolder {
        TextView textViewName, textViewCategory;
    }
}
