

package com.mm.yamingapp.core;

import android.app.Instrumentation;
import android.os.Bundle;
import android.test.InstrumentationTestRunner;
import android.widget.FrameLayout;

//adb shell am instrument  com.mm.yamingapp/com.mm.yamingapp.core.PrivateTestRunner 
// intent
public class PrivateTestRunner extends Instrumentation {

	String tag = "PrivateTestRunner_cindy";
	Frame mTestFrame = Frame.getInstance();
	@Override
	public void onCreate(Bundle arguments) {
		android.util.Log.i(tag, "runner onCreate");
		super.onCreate(arguments);
		start();
	}

	@Override
	public void onStart() {
		super.onStart();
		android.util.Log.i(tag, "runner onStart");
		mTestFrame.setInstrumentation(this);
		mTestFrame.run();
		android.util.Log.i(tag, "test frame run ok");
	}
	
	

	@Override
	public void onDestroy() {
		super.onDestroy();
		android.util.Log.i(tag, "runner onDestroy");
	}
}
