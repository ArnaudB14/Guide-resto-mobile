package fr.creative.guide.ui.details;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.creative.guide.AppActivity;
import fr.creative.guide.R;
import fr.creative.guide.models.Hotel;
import fr.creative.guide.models.Restaurant;

public class DetailsActivity extends AppActivity {
    private ImageView detailImage;
    private TextView detailTitle, detailCategory;
    private AppCompatButton detailMail, detailPhone, detailSite;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        detailCategory = findViewById(R.id.detailCategory);
        detailMail = findViewById(R.id.detailMail);
        detailPhone = findViewById(R.id.detailPhone);
        detailSite = findViewById(R.id.detailSite);

        Restaurant item = (Restaurant) getIntent().getSerializableExtra("restaurant");
        Hotel itemHotel = (Hotel) getIntent().getSerializableExtra("hotel");

        if(item != null) {
            Picasso.get().load(item.getImage()).into(detailImage);
            detailTitle.setText(item.getName());
            detailCategory.setText(item.getCategory());
            detailMail.setText(item.getEmail());
            detailPhone.setText(item.getPhone());
            detailSite.setText(item.getUrl());
        }



        if(itemHotel != null) {
            Picasso.get().load(itemHotel.getImage()).into(detailImage);
            detailTitle.setText(itemHotel.getName());
            detailCategory.setText(itemHotel.getCategory());
            detailMail.setText(itemHotel.getEmail());
            detailPhone.setText(itemHotel.getPhone());
            detailSite.setText(itemHotel.getUrl());
        }

        detailSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
                builder.setTitle("Aller sur le site web ?");


                // CUSTOM VIEW
                View customView = new View(DetailsActivity.this);
                customView.setBackgroundColor(Color.WHITE);
                builder.setView(customView);

                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(detailSite.getText().toString()));
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Non", null);
                builder.show();
            }
        });

        detailPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
                builder.setTitle("Appeler " + item.getPhone() + " ?");

                // CUSTOM VIEW
                View customView = new View(DetailsActivity.this);
                customView.setBackgroundColor(Color.WHITE);
                builder.setView(customView);

                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + detailPhone.getText().toString()));
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Non", null);
                builder.show();
            }
        });

        detailMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
                builder.setTitle("Envoyer un mail à " + item.getEmail() + " ?");

                // CUSTOM VIEW
                View customView = new View(DetailsActivity.this);
                customView.setBackgroundColor(Color.WHITE);
                builder.setView(customView);

                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + detailMail.getText().toString()));
                        intent.setType("message/rfc822");
                        // SUJET DU MESSAGE
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Demande de réservation");
                        // DESTINATAIRE
                        // intent.putExtra(Intent.EXTRA_EMAIL, new String[] { item.getEmail() });
                        // EMAIL EN COPIE
                        intent.putExtra(Intent.EXTRA_CC, new String[] { item.getEmail() });
                        // EMAIL EN COPIE CACHEE
                        intent.putExtra(Intent.EXTRA_BCC, new String[] { item.getEmail() });
                        // CORPS DU MESSAGE
                        // intent.putExtra(Intent.EXTRA_TEXT,"Corps du message");
                        startActivity(intent );
                    }
                });
                builder.setNegativeButton("Non", null);
                builder.show();
            }
        });
    }
}