package com.example.androidsubscribe;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.avos.avoscloud.AVOSCloud;
import com.example.utils.Utils;

import org.json.JSONObject;

/**
 * Created by mawenjing on 15/4/12.
 */
public class MessageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (intent.getAction().equals("com.mainactivity.action")) {
                JSONObject json = new JSONObject(intent.getExtras().getString("com.avos.avoscloud.Data"));
                final String message = json.getString("alert");
                Intent resultIntent = new Intent(AVOSCloud.applicationContext, MainActivity.class);
                Intent positiveIntent = new Intent(AVOSCloud.applicationContext, PositiveReceiver.class);
                Intent negativeIntent = new Intent(AVOSCloud.applicationContext, NegativeReceiver.class);

                PendingIntent resultPendingIntent =
                        PendingIntent.getActivity(AVOSCloud.applicationContext, 0, resultIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                PendingIntent positivePendingIntent =
                        PendingIntent.getBroadcast(AVOSCloud.applicationContext, 0, positiveIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                PendingIntent negativePendingIntent =
                        PendingIntent.getBroadcast(AVOSCloud.applicationContext, 0, negativeIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(AVOSCloud.applicationContext)
                                .setSmallIcon(R.drawable.ic_launcher)
                                .setContentTitle(
                                        AVOSCloud.applicationContext.getResources().getString(R.string.app_name))
                                .setContentText(message)
                                .setTicker(message)
                                .setContentIntent(resultPendingIntent)
                                .addAction(R.drawable.ic_launcher, Utils.positiveResponse, positivePendingIntent)
                                .addAction(R.drawable.ic_launcher, Utils.negativeResponse, negativePendingIntent)
                                .setAutoCancel(true);

                int mNotificationId = Utils.notificationId;
                NotificationManager mNotifyMgr =
                        (NotificationManager) AVOSCloud.applicationContext
                                .getSystemService(
                                        Context.NOTIFICATION_SERVICE);
                mNotifyMgr.notify(mNotificationId, mBuilder.build());
            }
        } catch (Exception e) {

        }
    }
}
