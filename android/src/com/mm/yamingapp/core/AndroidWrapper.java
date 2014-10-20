package com.mm.yamingapp.core;

import android.app.Instrumentation;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

import com.googlecode.android_scripting.facade.FacadeManagerFactory;
import com.googlecode.android_scripting.jsonrpc.RpcReceiverManagerFactory;
import com.mm.yamingapp.network.INetworkHandler;

public class AndroidWrapper implements INetworkHandler , IContext{

	private final RpcReceiverManagerFactory mReceiverManagerFactory = null;
	
	private boolean mIsService = true;
	private Instrumentation mInstrumentation;
	private Service mService ;
	private Context mContext;
	public AndroidWrapper(Instrumentation instrumentation){
		mIsService = false;
		mInstrumentation = instrumentation;
		mContext = instrumentation.getContext();
	}
	
	public AndroidWrapper(Service service){
		mIsService = true;
		mService = service;
		mContext = mService.getBaseContext();
	}
	
	@Override
	public boolean doWith(String data) {
		return false;
	}

	@Override
	public Context getContext() {
		return mContext;
	}

	@Override
	public Instrumentation getInstrumentation() throws Exception {
		if(mIsService){
			throw new Exception("Service Wrapper, None instrumentation");
		}
		return mInstrumentation;
	}

	@Override
	public Service getService() throws Exception{
		if(!mIsService){
			throw new Exception("Instrumentation Wrapper, None service");
		}
		return mService;
	}
	

}
