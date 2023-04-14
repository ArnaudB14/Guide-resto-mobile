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

        String[] listItem = {"Item 1", "Item 2", "Item 3"};

        listViewName = findViewById(R.id.listViewName);
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
        });

        if(getIntent().getExtras() != null) {
            boolean isRestaurant = getIntent().getExtras().getBoolean("isRestaurant");

            if(isRestaurant) {
                listingTitle.setText(R.string.listing_restaurant_title);
            } else {
                listingTitle.setText(R.string.listing_hotel_title);
            }
        }
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