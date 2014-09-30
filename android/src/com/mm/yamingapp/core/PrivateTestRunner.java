

package com.mm.yamingapp.core;

import android.app.Instrumentation;
import android.os.Bundle;

public class PrivateTestRunner extends Instrumentation {

	String tag = "PrivateTestRunner_cindy";
	Frame mTestFrame;
	@Override
	public void onCreate(Bundle arguments) {
		super.onCreate(arguments);
		start();
	}

	@Override
	public void onStart() {
		super.onStart();
		android.util.Log.i(tag, "runner onStart");
		mTestFrame.run();
		android.util.Log.i(tag, "test frame run ok");
	}
	
	

	@Override
	public void onDestroy() {
		super.onDestroy();
		android.util.Log.i(tag, "runner onDestroy");
	}
}
