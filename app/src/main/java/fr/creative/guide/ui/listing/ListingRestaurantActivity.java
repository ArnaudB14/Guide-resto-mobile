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

import fr.creative.guide.R;
import fr.creative.guide.ui.details.DetailsActivity;
import fr.creative.guide.ui.home.HomeActivity;

public class ListingRestaurantActivity extends AppCompatActivity {

    private ListView listViewRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_restaurant);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        String[] restaurants = {"Restaurant 1", "Restaurant 2", "Restaurant 3"};

        listViewRestaurant = findViewById(R.id.listViewRestaurant);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListingRestaurantActivity.this, R.layout.list_item, R.id.name, restaurants);
        listViewRestaurant.setAdapter(adapter);

        listViewRestaurant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String detailName = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListingRestaurantActivity.this, DetailsActivity.class);
                intent.putExtra("DETAIL_NAME", detailName);
                startActivity(intent);
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