package com.mm.yamingapp.core;


import java.util.ArrayList;
import java.util.List;

import com.mm.yamingapp.config.Config;
import com.mm.yamingapp.network.NetworkSocket;
import com.mm.yamingapp.wrapper.SoloWrapper;

public class Frame {
    private static Frame uniqueInstance = null;  
    private NetworkSocket mNS;
    private RpcCore mRpcCore = null;
    private Frame() {  
    	List<Class<? extends WrapperBase>> cs = new ArrayList<Class<? extends WrapperBase>>();
    	cs.add(SoloWrapper.class);
    	mRpcCore = new RpcCore(cs, null);
    }  
   
    public static Frame getInstance() {  
       if (uniqueInstance == null) {  
           uniqueInstance = new Frame();  
       }  
       return uniqueInstance;  
    }  
    
    public void run(){
//    	mNS = new NetworkSocket(null);
//    	mNS.connect2Server(Config.SERVER_IP, Config.SERVER_PORT);
//    	mNS.waitForCommand();
    	mRpcCore.test();
    }
}
