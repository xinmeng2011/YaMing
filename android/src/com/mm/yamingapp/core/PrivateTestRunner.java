

package com.mm.yamingapp.core;

import android.app.Instrumentation;
import android.os.Bundle;

public class PrivateTestRunner extends Instrumentation {

	String tag = "PrivateTestRunner_cindy";
	@Override
	public void onCreate(Bundle arguments) {
		super.onCreate(arguments);
		start();
	}

	@Override
	public void onStart() {
		super.onStart();
		android.util.Log.i(tag, "runner onStart");
	}
	
	

	@Override
	public void onDestroy() {
		super.onDestroy();
		android.util.Log.i(tag, "runner onDestroy");
	}
}
