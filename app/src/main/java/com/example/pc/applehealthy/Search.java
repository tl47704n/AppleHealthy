package com.example.pc.applehealthy;

import android.app.SearchManager;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    public static String medicine_name;
    private RecyclerView mMedicine_list;
    private LinearLayoutManager mLayoutManager;
    private DatabaseReference mdatabase;
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Medicines,RecipeViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Medicines, RecipeViewHolder>(

                Medicines.class,
                R.layout.medicine_list,
                RecipeViewHolder.class,
                mdatabase

        ) {

            @Override
            protected void populateViewHolder(RecipeViewHolder viewHolder, Medicines model, int position) {




               viewHolder.setalarm(model.getAlarm1());
                viewHolder.setalarm2(model.getAlarm2());
                viewHolder.setalarm3(model.getAlarm3());
                viewHolder.setmedicineName(model.getMedicineName());

            }
        };
        mMedicine_list.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Medicine");
        mLayoutManager = new LinearLayoutManager(Search.this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mMedicine_list = (RecyclerView)findViewById(R.id.recipe_list);
        mMedicine_list.setHasFixedSize(true);
       mMedicine_list.setLayoutManager(mLayoutManager);

        Firebase.setAndroidContext(this);

    }


    public static class RecipeViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setmedicineName(String a){

            TextView post_name = (TextView)mView.findViewById(R.id.tv_medicinename);
            post_name.setText(a);
        }
        public void setalarm(String alarm){

            TextView post_alarm = (TextView)mView.findViewById(R.id.tv_alarm);
            post_alarm.setText(alarm);
        }
        public void setalarm2(String alarm){

            TextView post_alarm = (TextView)mView.findViewById(R.id.tv_alarm2);
            post_alarm.setText(alarm);
        }
        public void setalarm3(String alarm){

            TextView post_alarm = (TextView)mView.findViewById(R.id.tv_alarm3);
            post_alarm.setText(alarm);
        }
        public void setmedicineID(String ID){
            TextView post_id = (TextView)mView.findViewById(R.id.tv_medicinename);
        }
    }






  /*  public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menuSearch));
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        // Do not iconify the widget; expand it by default



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }*/
}
