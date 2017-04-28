package com.example.pc.applehealthy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by pc on 2017/4/26.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String get_string = intent.getExtras().getString("extra");

        Intent service_intent = new Intent(context, RingtongPlayingService.class);

        service_intent.putExtra("extra",get_string);

        context.startService(service_intent);


    }
}
