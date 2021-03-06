package com.mm.yamingapp.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.googlecode.android_scripting.jsonrpc.JsonRpcResult;

import android.util.Log;

public class NetworkSocket{

	private String tag = "NetworkSocket_cindy";
	private final int buffer_size = 2048;
	private Socket mMainSocket;
	private INetworkHandler mINetworkHandler;
	
	public NetworkSocket(INetworkHandler  handler){
		mINetworkHandler = handler;
	}
	
	public void connect2Server(String ip , int port){
		try {
			Log.i(tag, "connect to server");
			mMainSocket = new Socket(ip, port);
		} catch (UnknownHostException e) {
			Log.i(tag, "can not connect to server");
		} catch (IOException e) {
			Log.i(tag, "can not connect to server"+ e.getMessage());
		}
	}
	
	public void waitForCommand(){
		if(mMainSocket == null){
			Log.i(tag, "connection not built");
		}
		try{
			InputStream inputStream = mMainSocket.getInputStream();
			OutputStream outputStream = mMainSocket.getOutputStream();
			DataInputStream inputData = new DataInputStream(inputStream);
			DataOutputStream outputData = new DataOutputStream(outputStream);
			byte[] data = new byte[buffer_size];
			
			while (true){
				int length = inputData.read(data);
				String parsedData = new String(data, 0, length, "gb2312");
				Log.i(tag, "rcved  " + parsedData);
				if(mINetworkHandler != null){
					Object resultObject = mINetworkHandler.doWith(parsedData);
					outputData.writeBytes(resultObject.toString());
				}
			}
		}catch(Exception ex){
			Log.i(tag, "exception rcving");
		}
	}
	
	public void closeSocket(){
		try {
			mMainSocket.close();
		} catch (IOException e) {
			Log.i(tag,"Socket close");
		}
	}
}