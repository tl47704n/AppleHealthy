package com.example.pc.applehealthy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;




import java.util.ArrayList;




public class MainActivity extends AppCompatActivity {

    Button tolocal;
    Button toonline;
    Button tosearch;
    Button newReminder;
    ListView listView;
    String get_result_name;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();


        setContentView(R.layout.activity_main);


        tolocal = (Button)findViewById(R.id.bt_tolocalalarm);
        toonline = (Button)findViewById(R.id.bt_toonlinealarm);


        tosearch = (Button)findViewById(R.id.bt_tosearch);
        newReminder = (Button)findViewById(R.id.bt_new_reminder);
        tosearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RealSearch.class);
                startActivity(i);


            }
        });
                newReminder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent i = new Intent(MainActivity.this, UploadName.class);
                        startActivityForResult(i, 1);

                    }
                });

        toonline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SetOnlineAlarm.class);
                startActivity(intent);
            }
        });

        tolocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SetAlarm.class);
                intent.putExtra("extra",0);
                startActivity(intent);
            }
        });








    }







}
