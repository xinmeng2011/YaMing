package com.mm.yamingapp.core;

public class WrapperBase {
	
	private IContext mIContext;
	
	public WrapperBase(IContext ictx){
		mIContext = ictx;
	}
	
	public IContext getIContext(){
		return mIContext;
	}
}
