package fr.creative.guide.ui.listing;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.creative.guide.R;
import fr.creative.guide.models.Restaurant;
import fr.creative.guide.ui.details.DetailsActivity;

public class ListingActivity extends AppCompatActivity {

    private TextView listingTitle, countResult;
    private ListView listViewName;
    private EditText searchEditText;
    private List<Restaurant> restaurantList;
    private Spinner categorySpinner;

    private RestaurantAdapter restaurantAdapter;

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
        searchEditText = findViewById(R.id.searchEditText);
        countResult = findViewById(R.id.countResult);
        categorySpinner = findViewById(R.id.categorySpinner);

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

            restaurantList = new ArrayList<>();

            restaurantList.add(new Restaurant("Le Bistronome", "Cuisine Française", "resa@bistronome-caen.com", "02 31 93 64 23", "https://bistronome-caen.com/", "https://media-cdn.tripadvisor.com/media/photo-s/18/08/4a/87/20190622-210802-largejpg.jpg"));
            restaurantList.add(new Restaurant("A Contre Sens", "Gastronomique", "info@acontresens.fr", "02 31 97 44 48", "https://www.acontresens.fr", "https://cdt14.media.tourinsoft.eu/upload/2---Le-10---Vue-1--A-CONTRE-SENS.jpg"));
            restaurantList.add(new Restaurant("Bonjour Bonsoir", "Cuisine Française", "contact@bonjourbonsoircaen.fr", "02 31 24 90 10", "https://www.bonjourbonsoircaen.fr", "https://i.f1g.fr/media/eidos/orig/2019/04/10/XVMbcf6af82-5ae3-11e9-8592-2e68e187fb35.jpg"));
            restaurantList.add(new Restaurant("McDonald's", "Fast food", "contact@mcdonalds.fr", "+33 (0)1 30 66 19 19", "https://www.mcdonalds.fr", "https://www.creads.com/wp-content/uploads/2021/05/header-2-10.jpg"));
            restaurantList.add(new Restaurant("Quick", "Fast food", "contact@quick.fr", "+33 (0)1 40 66 45 57", "https://www.quick.fr", "https://static.lpnt.fr/images/2023/02/16/24184344lpw-24184485-article-jpg_9341873_1250x625.jpg"));
            restaurantList.add(new Restaurant("Burger King", "Fast food", "contact@burgerking.fr", "+33 (0)1 41 10 33 33", "https://www.burgerking.fr", "https://images.lanouvellerepublique.fr/image/upload/60fa128f37dcf51d408b48a6.jpg"));
            restaurantList.add(new Restaurant("KFC", "Fast food", "contact@kfc.fr", "+33 (0)1 49 22 00 00", "https://www.kfc.fr", "https://elwatan-dz.com/storage/2472/KFC.jpg"));
            restaurantList.add(new Restaurant("Subway", "Fast food", "contact@subway.fr", "+33 (0)1 53 75 90 00", "https://www.subway.com/fr-fr", "https://business.lesechos.fr/medias/2018/09/25/312583_subway-roi-des-sandwiches-en-franchise-1499760159-subway-52566.jpg"));

            restaurantAdapter = new RestaurantAdapter(
                    ListingActivity.this,
                    R.layout.list_item,
                    restaurantList);

            listViewName.setAdapter(restaurantAdapter);

            listViewName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Restaurant item = restaurantList.get(position);

                    Intent intent = new Intent(ListingActivity.this, DetailsActivity.class);

                    // il faut ajouter implements Serializable dans Restaurant.java
                    intent.putExtra("restaurant", item);

                    startActivity(intent);
                }
            });

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

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ((RestaurantAdapter) listViewName.getAdapter()).updateList(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                int count = restaurantAdapter.getCount();
                countResult.setText(count + " restaurant(s) trouvé(s)");
            }
        });


        // Trier les restaurants par catégories
        Set<String> uniqueCategories = new HashSet<>();

        for (Restaurant restaurant : restaurantList) {
            uniqueCategories.add(restaurant.getCategory());
        }

        List<String> categories = new ArrayList<>(uniqueCategories);
        categories.add("Voir tout");
        Collections.reverse(categories);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = (String) parent.getItemAtPosition(position);
                if (selectedCategory.equals("Voir tout")) {
                    restaurantAdapter.setData(restaurantList);
                    int count = restaurantAdapter.getCount();
                    countResult.setText(count + " restaurant(s) trouvé(s)");
                } else {
                    // Afficher les restaurants de la catégorie sélectionnée
                    List<Restaurant> filteredList = new ArrayList<>();
                    for (Restaurant restaurant : restaurantList) {
                        if (restaurant.getCategory().equals(selectedCategory)) {
                            filteredList.add(restaurant);
                        }
                    }
                    restaurantAdapter.setData(filteredList);
                    int count = restaurantAdapter.getCount();
                    countResult.setText(count + " restaurant(s) trouvé(s)");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                restaurantAdapter.setData(restaurantList);
            }
        });


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