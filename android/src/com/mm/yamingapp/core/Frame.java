package com.mm.yamingapp.core;


import java.util.ArrayList;
import java.util.List;

import android.app.Instrumentation;
import android.app.Service;
import android.content.Context;

import com.mm.yamingapp.config.Config;
import com.mm.yamingapp.network.NetworkSocket;
import com.mm.yamingapp.wrapper.SoloWrapper;

public class Frame implements IContext{
    private static Frame uniqueInstance = null;  
    private NetworkSocket mNS;
    private RpcCore mRpcCore = null;
    private Instrumentation mInstrumentation = null;
    private Service mService = null;
    
    private Frame() {  
    	List<Class<? extends WrapperBase>> cs = new ArrayList<Class<? extends WrapperBase>>();
    	cs.add(SoloWrapper.class);
    	mRpcCore = new RpcCore(cs, this);
    }  
   
    public static Frame getInstance() {  
       if (uniqueInstance == null) {  
           uniqueInstance = new Frame();  
       }  
       return uniqueInstance;  
    }  
    
    public void setInstrumentation(Instrumentation is){
    	mInstrumentation = is;
    }
    
    
    public void run(){
//    	mNS = new NetworkSocket(null);
//    	mNS.connect2Server(Config.SERVER_IP, Config.SERVER_PORT);
//    	mNS.waitForCommand();
    	
    	mRpcCore.test();
    }

	@Override
	public Context getContext() {
		if(mInstrumentation != null){
			return mInstrumentation.getContext();
		}else if(mService != null){
			return mService;
		}
		return null;
	}

	@Override
	public Instrumentation getInstrumentation() throws Exception {
		return mInstrumentation;
	}

	@Override
	public Service getService() throws Exception {
		return mService;
	}
}
