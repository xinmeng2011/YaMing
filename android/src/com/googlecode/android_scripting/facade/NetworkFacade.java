package com.googlecode.android_scripting.facade;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.util.Log;

import com.googlecode.android_scripting.jsonrpc.RpcReceiver;
import com.googlecode.android_scripting.rpc.Rpc;
import com.googlecode.android_scripting.rpc.RpcParameter;

/**
 * Droid API - Facade for check device network connection.
 * 
 */
public class NetworkFacade extends RpcReceiver {

	public NetworkFacade(FacadeManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
	}
	
	@Rpc(description = "Test if a web server (port 80) is reachable. Timeout is 10s.",
			returns = "True if it can be connected. False otherwise.")
	public Boolean isNetworkReachable(@RpcParameter(name = "text", description = "The target host.") String host) {
		try {
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress(host, 80),	10000);
			socket.close();
			Log.i("NetworkFacade", host + " connected.");
			return true;
		} catch (UnknownHostException u) {
			Log.e("NetworkFacade", "Unknown host - " + host);
		} catch (Exception e) {
			Log.e("NetworkFacade", host + " isn't reachable. " + e.getMessage());
		}
		return false;
	}
}
