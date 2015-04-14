package com.example.application;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVOSCloud;
import com.example.utils.*;
import com.example.androidsubscribe.*;

import android.app.Application;



public class SubscribeApplication extends Application {
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
	    AVOSCloud.initialize(this, Utils.appId,
	            Utils.appKey);
	    
	    AVAnalytics.enableCrashReport(this.getApplicationContext(), true);
	    
	    UserInformation.createUser();
	}
}