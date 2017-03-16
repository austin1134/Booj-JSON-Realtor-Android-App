package com.example.austi.boojjsonandroidapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by austi on 3/12/2017.
 */

public class CustomAdapter extends BaseAdapter {
    private List<Response.RealtorsBean> mRealtoritem;
    private Response.RealtorsBean mRealtor;
    private Context mContext;
    private LayoutInflater inflater;

    public CustomAdapter(List<Response.RealtorsBean> mRealtoritem, Context mContext) {
        this.mRealtoritem = mRealtoritem;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return mRealtoritem.size();
    }

    @Override
    public Object getItem(int position) {
        return mRealtoritem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.realtor_list_content, parent, false);
        Response.RealtorsBean item = (Response.RealtorsBean) getItem(position);

        ImageView thumbnail = (ImageView) rowView.findViewById(R.id.thumbnail);
        ImageView realtorPhoto = (ImageView) rowView.findViewById(R.id.realtorPhoto);
        String imageUrl = item.getPhoto();
        Picasso.with(mContext).load(imageUrl).into(thumbnail);
//        Picasso.with(mContext).load(imageUrl).into(realtorPhoto);

        TextView firstName = (TextView) rowView.findViewById(R.id.realtorFirstName);
        TextView lastName = (TextView) rowView.findViewById(R.id.realtorLastName);
        TextView phoneNumber = (TextView) rowView.findViewById(R.id.realtorPhoneNumber);
/*        TextView firstName = (TextView) rowView.findViewById(R.id.firstName);
        TextView firstName = (TextView) rowView.findViewById(R.id.firstName);
        TextView firstName = (TextView) rowView.findViewById(R.id.firstName);*/

        firstName.setText(item.getFirst_name());
        lastName.setText(item.getLast_name());
        phoneNumber.setText(item.getPhone_number());

        return rowView;
    }

}
