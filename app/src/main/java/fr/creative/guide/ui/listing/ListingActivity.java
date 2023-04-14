package fr.creative.guide.ui.listing;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.creative.guide.R;
import fr.creative.guide.models.Restaurant;
import fr.creative.guide.ui.details.DetailsActivity;

public class ListingActivity extends AppCompatActivity {

    private TextView listingTitle;
    private ListView listViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        listingTitle = findViewById(R.id.listingTitle);
        listViewName = findViewById(R.id.listViewName);

        /*String[] listItem = {"Item 1", "Item 2", "Item 3"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListingActivity.this, R.layout.list_item, R.id.name, listItem);
        listViewName.setAdapter(adapter);

        listViewName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String detailName = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListingActivity.this, DetailsActivity.class);
                intent.putExtra("DETAIL_NAME", detailName);
                startActivity(intent);
            }
        });*/

        boolean isRestaurant = getIntent().getBooleanExtra("isRestaurant", false);
        if (isRestaurant) {
            listingTitle.setText(R.string.listing_restaurant_title);

            List<Restaurant> restaurantList = new ArrayList<>();

            restaurantList.add(new Restaurant("Le Bistrot du Chef", "French cuisine", "contact@bistrotduchef.com", "+33 (0)2 31 50 05 13", "https://www.bistrotduchef.com/", "https://www.bistrotduchef.com/wp-content/uploads/2019/12/BistrotduChef-1-1024x683.jpg"));
            restaurantList.add(new Restaurant("L'Assiette Normande", "French cuisine", "contact@lassiettenormande.com", "+33 (0)2 31 86 32 89", "https://www.lassiettenormande.com/", "https://www.lassiettenormande.com/wp-content/uploads/2017/09/lassiette-normande-caen-restaurant-ambiance-3.jpg"));
            restaurantList.add(new Restaurant("Le Bistrot Saint-Michel", "French cuisine", "contact@bistrotsaintmichel.com", "+33 (0)2 31 50 20 50", "https://www.bistrotsaintmichel.com/", "https://www.bistrotsaintmichel.com/wp-content/uploads/2018/06/Bistrot-Saint-Michel-caen-cuisine-traditionnelle.jpg"));
            restaurantList.add(new Restaurant("Le Jardin des Sens", "Gastronomique", "contact@lejardindessens.com", "+33 (0)2 31 86 28 08", "https://www.lejardindessens.com/", "https://www.lejardindessens.com/wp-content/uploads/2019/03/le-jardin-des-sens-restaurant-caen.jpg"));
            restaurantList.add(new Restaurant("Le Dauphin", "Cuisine Japonaise", "contact@ledauphincaen.com", "+33 (0)2 31 86 29 24", "https://www.ledauphincaen.com/", "https://www.ledauphincaen.com/wp-content/uploads/2021/01/photo-restaurant-Le-Dauphin-caen.jpg"));
            restaurantList.add(new Restaurant("McDonald's", "Fast food", "contact@mcdonalds.fr", "+33 (0)1 30 66 19 19", "https://www.mcdonalds.fr", "https://www.mcdonalds.fr/sites/default/files/produits/mcdo-logo-big_0.png"));
            restaurantList.add(new Restaurant("Quick", "Fast food", "contact@quick.fr", "+33 (0)1 40 66 45 57", "https://www.quick.fr", "https://www.quick.fr/sites/default/files/produits/logo_quick_3.jpg"));
            restaurantList.add(new Restaurant("Burger King", "Fast food", "contact@burgerking.fr", "+33 (0)1 41 10 33 33", "https://www.burgerking.fr", "https://www.burgerking.fr/sites/default/files/logo_bk.png"));
            restaurantList.add(new Restaurant("KFC", "Fast food", "contact@kfc.fr", "+33 (0)1 49 22 00 00", "https://www.kfc.fr", "https://www.kfc.fr/sites/default/files/inline-images/KFC%20LOGO%20Vertical%20Transparent_2.png"));
            restaurantList.add(new Restaurant("Subway", "Fast food", "contact@subway.fr", "+33 (0)1 53 75 90 00", "https://www.subway.com/fr-fr", "https://www.subway.com/content/dam/fr/fr/subway/img/header-logo.png"));

            listViewName.setAdapter(new RestaurantAdapter(
                    ListingActivity.this,
                    R.layout.list_item,
                    restaurantList
            ));
        } else {
            listingTitle.setText(R.string.listing_hotel_title);
        }

        // OU

        /*if(getIntent().getExtras() != null) {
            boolean isRestaurant = getIntent().getExtras().getBoolean("isRestaurant");

            if(isRestaurant) {
                listingTitle.setText(R.string.listing_restaurant_title);
            } else {
                listingTitle.setText(R.string.listing_hotel_title);
            }
        } */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}