package com.mm.yamingapp.core;


import com.mm.yamingapp.config.Config;
import com.mm.yamingapp.network.NetworkSocket;

public class Frame {
    private static Frame uniqueInstance = null;  
    private NetworkSocket mNS;
    private Frame() {  
    }  
   
    public static Frame getInstance() {  
       if (uniqueInstance == null) {  
           uniqueInstance = new Frame();  
       }  
       return uniqueInstance;  
    }  
    
    public void run(){
    	mNS = new NetworkSocket(null);
    	mNS.connect2Server(Config.SERVER_IP, Config.SERVER_PORT);
    	mNS.waitForCommand();
    }
}
