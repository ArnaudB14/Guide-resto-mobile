package fr.creative.guide.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import fr.creative.guide.R;
import fr.creative.guide.ui.listing.ListingActivity;
import fr.creative.guide.ui.listing.ListingHotelActivity;
import fr.creative.guide.ui.listing.ListingRestaurantActivity;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout restoReservation;
    private LinearLayout hotelReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        restoReservation = findViewById(R.id.restoReservation);
        hotelReservation = findViewById(R.id.hotelReservation);

        restoReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ListingRestaurantActivity.class);
                startActivity(intent);
            }
        });

        hotelReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ListingHotelActivity.class);
                startActivity(intent);
            }
        });

    }
}