package com.example.pc.applehealthy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadName extends AppCompatActivity {
        Button uploadname;
        EditText editText;
        DatabaseReference databasereference;
        String getName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_name);

        databasereference = FirebaseDatabase.getInstance().getReference().child("Medicines");

        uploadname = (Button)findViewById(R.id.bt_uploadname);
        editText = (EditText)findViewById(R.id.et_input_name);



        uploadname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getName = editText.getText().toString();



                Intent intent = new Intent(UploadName.this,SetAlarm.class);
                intent.putExtra("extra",getName);
                intent.putExtra("extra11",1);
                startActivity(intent);


            }
        });





    }


}
