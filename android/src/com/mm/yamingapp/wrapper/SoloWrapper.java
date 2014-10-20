package com.mm.yamingapp.wrapper;

import com.mm.yamingapp.core.IContext;
import android.app.Instrumentation;
import com.jayway.android.robotium.solo.Solo;


public class SoloWrapper {

	private IContext mIContext;
	private Solo mSolo;
	public SoloWrapper(IContext ic){
		mIContext = ic;
		try {
			mSolo = new Solo(mIContext.getInstrumentation());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickOnButtonByIndex(int index){
		mSolo.clickOnButton(index);
	}
	
}
