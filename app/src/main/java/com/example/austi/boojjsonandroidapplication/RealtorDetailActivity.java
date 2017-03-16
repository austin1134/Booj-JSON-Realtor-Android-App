package com.example.austi.boojjsonandroidapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.austi.boojjsonandroidapplication.Response.selectedRealtor;

/**
 * An activity representing a single Realtor detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link RealtorListActivity}.
 */
public class RealtorDetailActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtor_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);


        LayoutInflater inflater = (LayoutInflater)  this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.realtor_detail, null, false);


        ImageView realtorPhoto = (ImageView) view.findViewById(R.id.realtorPhoto);
        String imageUrl = selectedRealtor.photo;
        this.context = this.getApplicationContext();
        Picasso.with(context).load(imageUrl).into(realtorPhoto);


        TextView realtorName = (TextView) view.findViewById(R.id.realtorName);
        TextView phoneNumber = (TextView) view.findViewById(R.id.realtorPhoneNumber);
        TextView rebrand = (TextView) view.findViewById(R.id.rebrand);
        TextView office = (TextView) view.findViewById(R.id.office);


        realtorName.setText("Realtor Name: " + selectedRealtor.first_name + selectedRealtor.last_name);
        phoneNumber.setText("Phone Number: " + selectedRealtor.phone_number);
        rebrand.setText("Rebrand: " + selectedRealtor.rebrand);
        office.setText("Office: " + selectedRealtor.office);


        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(RealtorDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(RealtorDetailFragment.ARG_ITEM_ID));
            RealtorDetailFragment fragment = new RealtorDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.realtor_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, RealtorListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
