package com.mm.yamingapp.wrapper;

import com.mm.yamingapp.core.IContext;
import com.mm.yamingapp.core.WrapperBase;

import android.app.Instrumentation;
import android.content.Intent;
import android.util.Log;

import com.googlecode.android_scripting.rpc.Rpc;
import com.jayway.android.robotium.solo.Solo;


public class SoloWrapper extends WrapperBase{

	private Solo mSolo;
	private final String  tag = "SoloWrapper_cindy";
	public SoloWrapper(IContext ic){
		super(ic);
		try {
			mSolo = new Solo(getIContext().getInstrumentation());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Rpc(description = "")
	public void openMainActivity(String pkg, String clazz) throws Exception{
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setClassName(pkg, clazz);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		getIContext().getInstrumentation().startActivitySync(intent);
	}
	
	@Rpc(description = "")
	public void clickOnButtonByIndex(Integer index){
		Log.i(tag, "clickOnButtonByIndex");
		mSolo.clickOnButton(index);
	}
}
