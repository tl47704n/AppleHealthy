package com.example.pc.applehealthy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class SetAlarm extends AppCompatActivity {

    AlarmManager mAlarmManager;
    DatabaseReference databasereference;
    TimePicker mTimepicker;
    TextView mStatus;
    TextView mStatus_noon;
    TextView mStatus_night;
    Context context;
    Button mSwitch;
    Button alarm_noon;
    Button alarm_night;
    String medicineName;
    Calendar calendar_to_all1;
    Calendar calendar_to_all2;
    Calendar calendar_to_all3;
    PendingIntent mPendingIntent;
    Button mAlarm_off;
    String getName;
     private int on_off1 = 0;
    private int on_off2=0;
    private int on_off3 = 0;
    long alarm1;
    long alarm2;
    long alarm3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        Bundle bundle = getIntent().getExtras();
        medicineName = bundle.getString("extra");

        databasereference = FirebaseDatabase.getInstance().getReference().child("Medicine");
        this.context = this;
        mAlarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        mTimepicker = (TimePicker)findViewById(R.id.timePicker);
        mStatus = (TextView)findViewById(R.id.tv_alarm_status);

        final Calendar calendar = Calendar.getInstance();
        final Calendar calendar2 = Calendar.getInstance();
        final Calendar calendar3 = Calendar.getInstance();
        final Intent i = new Intent(SetAlarm.this,AlarmReceiver.class);

        mSwitch = (Button)findViewById(R.id.bt_I_O);
        mSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(on_off1 == 0) {

                    Calendar now = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, mTimepicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE, mTimepicker.getCurrentMinute());
                    calendar_to_all1 = calendar;
                    int hour = mTimepicker.getCurrentHour();
                    int minute = mTimepicker.getCurrentMinute();

                    if (calendar.getTimeInMillis() < now.getTimeInMillis()) {
                        Log.e("is smaller than now", "111");

                        calendar.setTimeInMillis(calendar.getTimeInMillis() + 86400000);
                    }
                    alarm1 = calendar.getTimeInMillis();
                    String hour_string = String.valueOf(hour);
                    String minute_string = String.valueOf(minute);

                    if (minute < 10) {
                        minute_string = "0" + String.valueOf(minute);

                    }


                    i.putExtra("extra", "alarm on");

                    mPendingIntent = PendingIntent.getBroadcast(
                            SetAlarm.this,
                            0,
                            i,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    mAlarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), mPendingIntent);
                    mStatus.setText(hour_string + ":" + minute_string);
                    on_off1=1;

                    mSwitch.setBackgroundResource(R.drawable.bt_on_green);
                    mSwitch.setText("ON");
                }
                else if (on_off1 == 1){


                    mSwitch.setBackgroundResource(R.drawable.bt_off_grey);
                    mSwitch.setText("OFF");
                    i.putExtra("extra","alarm off");

                    sendBroadcast(i);
                    mAlarmManager.cancel(mPendingIntent);

                    mStatus.setText("Canceled");
                    on_off1 = 0;
                }
            }
        });

        mStatus_noon = (TextView)findViewById(R.id.tv_alarm_status_noon);
        alarm_noon = (Button)findViewById(R.id.bt_alarm_noon);
        alarm_noon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(on_off2 == 0) {

                    Calendar now = Calendar.getInstance();
                    calendar2.setTimeInMillis(System.currentTimeMillis());
                    calendar2.set(Calendar.HOUR_OF_DAY, mTimepicker.getCurrentHour());
                    calendar2.set(Calendar.MINUTE, mTimepicker.getCurrentMinute());
                    calendar_to_all2 = calendar2;
                    int hour = mTimepicker.getCurrentHour();
                    int minute = mTimepicker.getCurrentMinute();

                    if (calendar2.getTimeInMillis() < now.getTimeInMillis()) {
                        Log.e("is smaller than now", "111");

                        calendar2.setTimeInMillis(calendar2.getTimeInMillis() + 86400000);
                    }
                    alarm2 = calendar2.getTimeInMillis();
                    String hour_string = String.valueOf(hour);
                    String minute_string = String.valueOf(minute);

                    if (minute < 10) {
                        minute_string = "0" + String.valueOf(minute);

                    }


                    i.putExtra("extra", "alarm on");

                    mPendingIntent = PendingIntent.getBroadcast(
                            SetAlarm.this,
                            1,
                            i,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    mAlarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), mPendingIntent);
                    mStatus_noon.setText(hour_string + ":" + minute_string);
                    on_off2=1;

                    alarm_noon.setBackgroundResource(R.drawable.bt_on_green);
                    alarm_noon.setText("ON");
                }
                else if (on_off2 == 1){


                    alarm_noon.setBackgroundResource(R.drawable.bt_off_grey);
                    alarm_noon.setText("OFF");
                    i.putExtra("extra","alarm off");

                    sendBroadcast(i);
                    mAlarmManager.cancel(mPendingIntent);

                    mStatus_noon.setText("Canceled");
                    on_off2 = 0;
                }
            }
        });

        mStatus_night = (TextView)findViewById(R.id.tv_alarm_status_night);
        alarm_night = (Button)findViewById(R.id.bt_alarm_off_night);
        alarm_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(on_off3 == 0) {

                    Calendar now = Calendar.getInstance();
                    calendar3.setTimeInMillis(System.currentTimeMillis());
                    calendar3.set(Calendar.HOUR_OF_DAY, mTimepicker.getCurrentHour());
                    calendar3.set(Calendar.MINUTE, mTimepicker.getCurrentMinute());
                    calendar_to_all3 = calendar3;
                    int hour = mTimepicker.getCurrentHour();
                    int minute = mTimepicker.getCurrentMinute();

                    if (calendar3.getTimeInMillis() < now.getTimeInMillis()) {
                        Log.e("is smaller than now", "111");

                        calendar3.setTimeInMillis(calendar3.getTimeInMillis() + 86400000);
                    }
                    alarm3 = calendar3.getTimeInMillis();
                    String hour_string = String.valueOf(hour);
                    String minute_string = String.valueOf(minute);

                    if (minute < 10) {
                        minute_string = "0" + String.valueOf(minute);

                    }


                    i.putExtra("extra", "alarm on");

                    mPendingIntent = PendingIntent.getBroadcast(
                            SetAlarm.this,
                            2,
                            i,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    mAlarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), mPendingIntent);
                    mStatus_night.setText(hour_string + ":" + minute_string);
                    on_off3=1;

                    alarm_night.setBackgroundResource(R.drawable.bt_on_green);
                    alarm_night.setText("ON");
                }
                else if (on_off3 == 1){


                    alarm_night.setBackgroundResource(R.drawable.bt_off_grey);
                    alarm_night.setText("OFF");
                    i.putExtra("extra","alarm off");

                    sendBroadcast(i);
                    mAlarmManager.cancel(mPendingIntent);

                    mStatus_night.setText("Canceled");
                    on_off3 = 0;
                }
            }
        });












    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_finish_setting, menu);
        return super.onCreateOptionsMenu(menu);

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            // do something here
          Bundle bundle = getIntent().getExtras();
            int check=1;
            check = bundle.getInt("extra");
            int check2 =0;
                  check2  = bundle.getInt("extra11");
            if (check ==0 && check2 ==0){



                Intent intent = new Intent(SetAlarm.this,MainActivity.class);

                startActivity(intent);
            }else if(check == 0 && check2 != 0){
                setVisible(true);
                startposting();
                Intent intent = new Intent(SetAlarm.this,MainActivity.class);

                startActivity(intent);
            }else if(check==1 && check2 !=0){
                setVisible(true);
                startposting();
                Intent intent = new Intent(SetAlarm.this,MainActivity.class);

                startActivity(intent);
            }else {
                Intent intent = new Intent(SetAlarm.this,MainActivity.class);

                startActivity(intent);
            }


        }
        return super.onOptionsItemSelected(item);
    }


    private void startposting() {


        DatabaseReference newpost = databasereference.push();
        int a = calendar_to_all1.get(Calendar.AM_PM);
        String AM_or_PM;
        if (a == Calendar.AM){ AM_or_PM = "AM"; } else {AM_or_PM = "PM";}
        String alarm_1 = calendar_to_all1.get(Calendar.HOUR_OF_DAY)+ ":"+calendar_to_all1.get(Calendar.MINUTE) + " " + AM_or_PM ;

        int b = calendar_to_all2.get(Calendar.AM_PM);
        String AM_or_PM2;
        if (b == Calendar.AM){ AM_or_PM2 = "AM"; } else {AM_or_PM2 = "PM";}
        String alarm_2 = calendar_to_all2.get(Calendar.HOUR_OF_DAY)+ ":"+calendar_to_all2.get(Calendar.MINUTE) + " " + AM_or_PM2 ;

        int c = calendar_to_all3.get(Calendar.AM_PM);
        String AM_or_PM3;
        if (c == Calendar.AM){ AM_or_PM3 = "AM"; } else {AM_or_PM3 = "PM";}
        String alarm_3 = calendar_to_all3.get(Calendar.HOUR_OF_DAY)+ ":"+calendar_to_all3.get(Calendar.MINUTE) + " " + AM_or_PM3 ;


        ;

        newpost.child("medicineName").setValue(medicineName);
        newpost.child("alarm1").setValue(alarm_1);
        newpost.child("alarm2").setValue(alarm_2);
        newpost.child("alarm3").setValue(alarm_3);
        newpost.child("alarm1_mili").setValue(alarm1);
        newpost.child("alarm2_mili").setValue(alarm2);
        newpost.child("alarm3_mili").setValue(alarm3);

        Medicines m = new Medicines();
        m.setName(medicineName.toString());
        m.setAlarm1(alarm_1);
        m.setMedicineID(medicineName);
        m.setAlarm2(alarm_2);
        m.setAlarm3(alarm_3);
        m.setAlarm1_mili(alarm1);
        m.setAlarm2_mili(alarm2);
        m.setAlarm3_mili(alarm3);

    }
}
