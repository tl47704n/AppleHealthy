package com.example.pc.applehealthy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class SetOnlineAlarm extends AppCompatActivity {
    long alarm1_in_mili;
    long alarm2_in_mili;
    long alarm3_in_mili;
    String alarm1;
    String alarm2;
    String alarm3;
    Button tomain;
    PendingIntent mPendingIntent;
    PendingIntent mPendingIntent2;
    PendingIntent mPendingIntent3;
    Context context;
    AlarmManager mAlarmManager;
    Button setOnlineAlarm;
    private int on_off1 = 0;
   TextView morning;
    TextView noon;
    TextView evening;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_online_alarm);
        tomain = (Button)findViewById(R.id.bt_setanother);
        setOnlineAlarm = (Button)findViewById(R.id.bt_setOnlineAlarm);
        morning = (TextView)findViewById(R.id.tv_alarm_set_1) ;
        noon = (TextView)findViewById(R.id.tv_alarm_set_2) ;
        evening = (TextView)findViewById(R.id.tv_alarm_set_3) ;
        this.context = this;
        mAlarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        final Calendar calendar = Calendar.getInstance();
        final Calendar calendar2 = Calendar.getInstance();
        final Calendar calendar3 = Calendar.getInstance();
        final Intent i = new Intent(SetOnlineAlarm.this,AlarmReceiver.class);

        DatabaseReference databaseReference =  FirebaseDatabase.getInstance().getReferenceFromUrl("https://applehealthy-fd979.firebaseio.com/Medicine");

        databaseReference.orderByChild("medicineName").equalTo(RealSearch.medicineName).addChildEventListener(new ChildEventListener() {
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                Medicines value = dataSnapshot.getValue(Medicines.class);
                alarm1_in_mili = value.getAlarm1_mili();
                alarm2_in_mili = value.getAlarm2_mili();
                alarm3_in_mili = value.getAlarm3_mili();
                alarm1 = value.getAlarm1();
                alarm2 = value.getAlarm2();
                alarm3 = value.getAlarm3();
                morning.setText(alarm1);
                noon.setText(alarm2);
                evening.setText(alarm3);

            }

            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        setOnlineAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(on_off1 == 0) {




                    i.putExtra("extra", "alarm on");

                    mPendingIntent = PendingIntent.getBroadcast(
                            SetOnlineAlarm.this,
                            3,
                            i,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    mAlarmManager.set(AlarmManager.RTC_WAKEUP, alarm1_in_mili, mPendingIntent);
                    mPendingIntent = PendingIntent.getBroadcast(
                            SetOnlineAlarm.this,
                            4,
                            i,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    mAlarmManager.set(AlarmManager.RTC_WAKEUP, alarm2_in_mili, mPendingIntent);
                    mPendingIntent = PendingIntent.getBroadcast(
                            SetOnlineAlarm.this,
                            5,
                            i,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    mAlarmManager.set(AlarmManager.RTC_WAKEUP, alarm3_in_mili, mPendingIntent);

                    on_off1=1;

                    setOnlineAlarm.setBackgroundResource(R.drawable.bt_on_green);
                    setOnlineAlarm.setText("ON");
                }
                else if (on_off1 == 1){


                    setOnlineAlarm.setBackgroundResource(R.drawable.bt_off_grey);
                    setOnlineAlarm.setText("OFF");
                    i.putExtra("extra","alarm off");

                    sendBroadcast(i);
                    mAlarmManager.cancel(mPendingIntent);


                    on_off1 = 0;
                }
            }
        });

        tomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetOnlineAlarm.this,MainActivity.class);

                startActivity(intent);

            }
        });



    }
}
