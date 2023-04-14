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

import fr.creative.guide.R;
import fr.creative.guide.ui.details.DetailsActivity;

public class ListingHotelActivity extends AppCompatActivity {

    private ListView listViewHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_hotel);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        String[] hotels = {"Hotel 1", "Hotel 2", "Hotel 3"};

        listViewHotel = findViewById(R.id.listViewHotel);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListingHotelActivity.this, R.layout.list_item, R.id.name, hotels);

        listViewHotel.setAdapter(adapter);

        listViewHotel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String detailName = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListingHotelActivity.this, DetailsActivity.class);
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