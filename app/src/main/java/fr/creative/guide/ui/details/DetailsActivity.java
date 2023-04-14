package fr.creative.guide.ui.details;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.creative.guide.R;
import fr.creative.guide.models.Restaurant;

public class DetailsActivity extends AppCompatActivity {
    private ImageView detailImage;
    private TextView detailTitle, detailCategory;
    private AppCompatButton detailMail, detailPhone, detailSite;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        detailCategory = findViewById(R.id.detailCategory);
        detailMail = findViewById(R.id.detailMail);
        detailPhone = findViewById(R.id.detailPhone);
        detailSite = findViewById(R.id.detailSite);

        Restaurant item = (Restaurant) getIntent().getExtras().getSerializable("restaurant");

        Picasso.get().load(item.getImage()).into(detailImage);
        detailTitle.setText(item.getName());
        detailCategory.setText(item.getCategory());
        detailMail.setText(item.getEmail());
        detailPhone.setText(item.getPhone());
        detailSite.setText(item.getUrl());

        detailSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
                builder.setTitle("Aller sur le site web ?");
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
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
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + item.getPhone()));
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
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + item.getEmail()));
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Non", null);
                builder.show();
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