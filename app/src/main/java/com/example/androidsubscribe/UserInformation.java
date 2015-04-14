package com.example.androidsubscribe;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.example.utils.BLog;
import com.example.utils.Utils;

public class UserInformation {
	public static void createUser() {
		AVUser user = new AVUser();
		user.setUsername(Utils.userName);
		user.setPassword(Utils.userPassword);
		
		user.signUpInBackground(new SignUpCallback() {
			@Override
			public void done(AVException e) {
				// TODO Auto-generated method stub
				if (e == null) {
					BLog.d("User Sign Up Successfully.");
				} else {
					BLog.d("User Sign Up Failed.");
				}
			}
		});
	}
	
	public static AVUser getCurrentUser() {
		AVUser currentUser = AVUser.getCurrentUser();
		
		if (currentUser != null) {
			return currentUser;
		} else {
			return null;
		}
	}
}