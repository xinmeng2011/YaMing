package com.mm.yamingapp.ui;

import android.app.Instrumentation;

import com.jayway.android.robotium.solo.Solo;

public class SoloProxy {

	Solo mSolo;
	Instrumentation mIns;
	public SoloProxy(Instrumentation ins){
		mIns = ins;
		mSolo = new Solo(mIns);
	}
	
	
}
