package com.example.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

/**
 * 
 * @hide
 *
 */
public class BLog {
	public static boolean logEnable = true;

	public static void d() {
		if(Thread.currentThread().getStackTrace().length == 0){
			
		}else{
			Log.d(getFileName(), getMethodName());
		}
	}
	
	public static void d(String s) {
		if(Thread.currentThread().getStackTrace().length == 0){
			
		}else{
			Log.d(getFileName(), s);
		}
	}
	
	public static void e(String s) {
		if(Thread.currentThread().getStackTrace().length == 0){
			
		}else{
			Log.e(getFileName(), s);
		}
	}

	public static String getShortTraceInfo() {
		StringBuffer sb = new StringBuffer();

		StackTraceElement[] stacks = new Throwable().getStackTrace();
		sb.append("[method: ").append(stacks[2].getMethodName())
				.append("; line: ").append(stacks[2].getLineNumber())
				.append("; class: ").append(stacks[2].getFileName())
				.append("]");

		return sb.toString();
	}
	
	public static String getTraceInfo() {
		StringBuffer sb = new StringBuffer();

		StackTraceElement[] stacks = new Throwable().getStackTrace();
		sb.append("method: ").append(stacks[2].getMethodName())
				.append("; line: ").append(stacks[2].getLineNumber())
				.append("; class: ").append(stacks[2].getClassName());

		return sb.toString();
	}

	public static String getLineInfo() {
		StackTraceElement ste = new Throwable().getStackTrace()[2];
		return ste.getFileName() + ": Line " + ste.getLineNumber();
	}
	
	public static String getMethodName() {
		StackTraceElement ste = new Throwable().getStackTrace()[2];
		return ste.getMethodName();
	}
	
	public static String getFileName() {
		StackTraceElement ste;
		ste = new Throwable().getStackTrace()[2];
		return ste.getFileName() + "[" +ste.getLineNumber() + "]";
	}
	
	public static String getPackageName(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
		String packageName = cn.getPackageName();
		return packageName;
	}
}
