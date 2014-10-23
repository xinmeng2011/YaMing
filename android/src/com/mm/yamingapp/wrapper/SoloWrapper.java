package com.mm.yamingapp.wrapper;

import com.mm.yamingapp.core.IContext;
import com.mm.yamingapp.core.WrapperBase;

import android.app.Instrumentation;
import android.util.Log;

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
	
	public void clickOnButtonByIndex(int index){
		Log.i(tag, "clickOnButtonByIndex");
		//mSolo.clickOnButton(index);
	}
	
}
