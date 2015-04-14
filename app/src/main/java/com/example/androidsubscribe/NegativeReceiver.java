package com.example.androidsubscribe;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;
import com.example.utils.BLog;
import com.example.utils.Utils;

/**
 * Created by mawenjing on 15/4/12.
 */
public class NegativeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        BLog.d("Receive Negative Answer.");

        AVObject responseObject = new AVObject(Utils.responseObject);
        responseObject.put(String.valueOf(Utils.notificationId), String.valueOf(false));

        responseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    BLog.d("Save Response Successfully.");
                } else {
                    BLog.d("Save Response Failed.");
                }
            }
        });

        NotificationManager mNotifyMgr =
                (NotificationManager) AVOSCloud.applicationContext
                        .getSystemService(
                                Context.NOTIFICATION_SERVICE);
        mNotifyMgr.cancel(Utils.notificationId);
    }
}
