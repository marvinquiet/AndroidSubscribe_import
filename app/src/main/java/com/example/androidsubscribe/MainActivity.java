package com.example.androidsubscribe;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.PushService;
import com.avos.avoscloud.SaveCallback;
import com.example.utils.BLog;
import com.example.utils.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tv_installationId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        // user login
        AVUser.logInInBackground(Utils.userName, Utils.userPassword, new LogInCallback<AVUser>() {
			@Override
			public void done(AVUser user, AVException e) {
				// TODO Auto-generated method stub
				if (user != null) {
					BLog.d("User Login In Successfully");
				} else {
					BLog.d("User Login In Failed");
				}
			}
		});
        
        // set subscriber
        PushService.setDefaultPushCallback(this, MainActivity.class);
        PushService.subscribe(this, "public", MainActivity.class);
        PushService.subscribe(this, "private", Callback1.class);
        PushService.subscribe(this, "protected", Callback2.class);
        
        // installation ID
        Utils.installationId = AVInstallation.getCurrentInstallation().getInstallationId();
        BLog.d(Utils.installationId);
        
        tv_installationId = (TextView) findViewById(R.id.installation_id);
        tv_installationId.setText(Utils.installationId);
        
        // save current installation
        AVInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
            	if (e == null) {
	                AVUser currentUser = UserInformation.getCurrentUser();
	                if (currentUser != null) {
	                	currentUser.put("installationId", Utils.installationId);

	                	currentUser.saveInBackground(new SaveCallback() {
							
							@Override
							public void done(AVException e) {
								// TODO Auto-generated method stub
								if (e == null) {
									BLog.d("Installation ID saved successfully");
								} else {
									BLog.d("Installation ID saved failed");
								}
							}
						});
	                } else {
	                	BLog.d("Current User is Null");
	                }
	                
	        		AVInstallation.getCurrentInstallation().saveInBackground();
            	} else {
            		BLog.d("Information saved failed.");
            	}
            }
        });
        
	}
}
