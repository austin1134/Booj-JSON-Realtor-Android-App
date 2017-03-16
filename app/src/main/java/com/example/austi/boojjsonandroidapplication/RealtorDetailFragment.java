package com.example.austi.boojjsonandroidapplication;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.austi.boojjsonandroidapplication.Response.selectedRealtor;


/**
 * A fragment representing a single Realtor detail screen.
 * This fragment is either contained in a {@link RealtorListActivity}
 * in two-pane mode (on tablets) or a {@link RealtorDetailActivity}
 * on handsets.
 */
public class RealtorDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static String ARG_ITEM_ID = "item_id";


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RealtorDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.realtor_detail, container, false);

        Context context = getActivity().getApplicationContext();



        ImageView realtorPhoto = (ImageView) rootView.findViewById(R.id.realtorPhoto);
        String imageUrl = selectedRealtor.photo;
        Picasso.with(context).load(imageUrl).into(realtorPhoto);




        // Show the dummy content as text in a TextView.
        if (selectedRealtor != null) {
            ((TextView) rootView.findViewById(R.id.realtorName)).setText("Name: " + selectedRealtor.first_name + selectedRealtor.last_name);
            ((TextView) rootView.findViewById(R.id.realtorPhoneNumber)).setText("Phone Number: " + selectedRealtor.phone_number);
            ((TextView) rootView.findViewById(R.id.rebrand)).setText("Rebrand: " + selectedRealtor.rebrand);
            ((TextView) rootView.findViewById(R.id.office)).setText("Office: " + selectedRealtor.office);
        }

        return rootView;
    }
}
