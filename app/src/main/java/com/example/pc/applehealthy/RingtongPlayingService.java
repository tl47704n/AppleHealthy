package com.example.pc.applehealthy;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by pc on 2017/4/26.
 */

public class RingtongPlayingService extends Service {
    @Nullable
    MediaPlayer media_ring;
    boolean isRunning;
    private int startId;
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        Intent intent1 = new Intent(this.getApplicationContext(),SetAlarm.class);
         PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent1,0);

        String state = intent.getExtras().getString("extra");

        Log.e("What's going on here",state);
        Log.e("What's going on here",state);
        Log.e("What's going on here",state);

        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        if(!this.isRunning && startId ==1){
            media_ring = MediaPlayer.create(this, R.raw.wedding);
            media_ring.start();
            this.isRunning = true;
            this.startId = 0;
        }
        else if(!this.isRunning && startId==0){

            this.isRunning = false;
            this.startId=0;
        }
        else if(this.isRunning && startId == 1){
            this.isRunning = true;
            this.startId = 0;

        }

        else
        {
            media_ring.stop();
            media_ring.reset();
            this.isRunning = false;
            this.startId = 0;
        }



        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {


        // Tell the user we stopped.
        Toast.makeText(this, "on Destroy called", Toast.LENGTH_SHORT).show();
    }



}
