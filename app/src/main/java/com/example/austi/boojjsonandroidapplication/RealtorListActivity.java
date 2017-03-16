package com.example.austi.boojjsonandroidapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.example.austi.boojjsonandroidapplication.dummy.DummyContent;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.Serializable;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.example.austi.boojjsonandroidapplication.Response.selectedRealtor;

/**
 * An activity representing a list of Realtors. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RealtorDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RealtorListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    static boolean mTwoPane;
    static Integer mRealtorPosition;
    public static Response.RealtorsBean mRealtor;

    ListView listView;
    Response responseObj;
    CustomAdapter adapter;
    String url = "http://www.denverrealestate.com/rest.php/mobile/realtor/list?app_key=f7177163c833dff4b38fc8d2872f1ec6";
    Gson gson;
    AsyncHttpClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtor_list);
        listView = (ListView) findViewById(R.id.realtor_list);
        client = new AsyncHttpClient();
        client.get(RealtorListActivity.this, url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responsestr = new String(responseBody);
                String responsestrToObject = "{\"realtors\":"+ responsestr +"}";

                gson = new Gson();

                try
                {
                    responseObj = gson.fromJson(responsestrToObject,Response.class);
                    adapter = new CustomAdapter(responseObj.getRealtors(), RealtorListActivity.this);
                    listView.setAdapter(adapter);
                }
                catch (IllegalStateException | JsonSyntaxException exception)
                {
                    exception.getMessage();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());



//        View listView = findViewById(R.id.realtor_list);
//        assert listView != null;
//        setupListView((ListView) listView);

        if (findViewById(R.id.realtor_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        final ListView realtorList = (ListView) findViewById(R.id.realtor_list);
        realtorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            Context context = listView.getContext();
            Intent intent = new Intent(context, RealtorDetailActivity.class);
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Response.RealtorsBean mRealtor = responseObj.getRealtors().get(position);
                selectedRealtor = mRealtor;

                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putInt("realtor", position);
                    RealtorDetailFragment fragment = new RealtorDetailFragment();
                    fragment.setArguments(arguments);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.realtor_detail_container, fragment)
                            .commit();
                } else {


                    Bundle bundle = new Bundle();
                    mRealtorPosition = position;
                    bundle.putSerializable("realtor", mRealtor.id);
                    intent.putExtra(RealtorDetailFragment.ARG_ITEM_ID, mRealtor.id);

                    context.startActivity(intent);
                }
//                Context context = view.getContext();
//                Intent intent = new Intent();
//                intent.setClass(context, RealtorDetailActivity.class);
//                intent.putExtra("position", position);
//                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });
    }

//    private void setupListView(@NonNull ListView listView) {
//        adapter = new CustomAdapter(responseObj.getRealtors(), RealtorListActivity.this);
//        listView.setAdapter(adapter);
//    }

//    public class SimpleItemListViewAdapter
//            extends RecyclerView.Adapter<SimpleItemListViewAdapter.ViewHolder> {
//
//        private final List<Response.RealtorsBean> mValues;
//
//        public SimpleItemListViewAdapter(List<Response.RealtorsBean> items) {
//            mValues = items;
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.realtor_list_content, parent, false);
//            return new ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.mItem = mValues.get(position);
//            holder.mIdView.setText(mValues.get(position).id);
//            holder.mFirstNameView.setText(mValues.get(position).first_name);
//            holder.mLastNameView.setText(mValues.get(position).last_name);
//            holder.mRebrandView.setText(mValues.get(position).rebrand);
//            holder.mOfficeView.setText(mValues.get(position).office);
////            holder.mIsTeamView.setText(mValues.get(position).is_team);
//            holder.mPhoneNumberView.setText(mValues.get(position).phone_number);
//            holder.mPhotoView.setText(mValues.get(position).photo);
//
//
//
//
//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mTwoPane) {
//                        Bundle arguments = new Bundle();
//                        arguments.putString(RealtorDetailFragment.ARG_ITEM_ID, holder.mItem.id);
//                        RealtorDetailFragment fragment = new RealtorDetailFragment();
//                        fragment.setArguments(arguments);
//                        getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.realtor_detail_container, fragment)
//                                .commit();
//                    } else {
//                        Context context = v.getContext();
//                        Intent intent = new Intent(context, RealtorDetailActivity.class);
//                        intent.putExtra(RealtorDetailFragment.ARG_ITEM_ID, holder.mItem.id);
//
//                        context.startActivity(intent);
//                    }
//                }
//            });
//        }

//        @Override
//        public int getItemCount() {
//            return mValues.size();
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder {
//            public View mView;
//            public TextView mIdView;
//            public TextView mFirstNameView;
//            public TextView mLastNameView;
//            public TextView mRebrandView;
//            public TextView mOfficeView;
//            public TextView mIsTeamView;
//            public TextView mPhoneNumberView;
//            public TextView mPhotoView;
//            public Response.RealtorsBean mItem;
//
///*            public final View mView;
//            public final TextView mIdView;
//            public final TextView mContentView;
//            public final TextView mTest;*/
//
//
//
//            public ViewHolder(View view) {
//                super(view);
////                mView = findViewById(R.id.realtor_list);
//                mView = view;
//                mIdView = (TextView) view.findViewById(R.id.id);
//
//                mFirstNameView = (TextView) view.findViewById(R.id.realtorFirstName);
//                mLastNameView = (TextView) view.findViewById(R.id.realtorFirstName);
//                mRebrandView = (TextView) view.findViewById(R.id.realtorFirstName);
//                mOfficeView = (TextView) view.findViewById(R.id.realtorFirstName);
//                mIsTeamView = (TextView) view.findViewById(R.id.realtorFirstName);
//                mPhoneNumberView = (TextView) view.findViewById(R.id.realtorFirstName);
//                mPhotoView = (TextView) view.findViewById(R.id.realtorFirstName);
//
//            }
//
//            @Override
//            public String toString() {
//                return super.toString() + " '" + mFirstNameView.getText() + "'";
//            }
        }
//    }
