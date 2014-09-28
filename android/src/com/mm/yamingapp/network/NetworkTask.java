package com.mm.yamingapp.network;

import java.io.InputStream;
import java.net.Socket;

import android.os.AsyncTask;
import android.util.Log;

public class NetworkTask extends AsyncTask<Void, Void, String>{

	String tag = "NetworkTask_cindy";
	@Override
	protected String doInBackground(Void... params) {
		
		try{
			Socket socket = new Socket("192.168.0.1", 6655);
			InputStream inputStream = s.getInputStream()
		}catch(Exception ex){
			Log.i(tag, "error happens");
		}
		return null;
	}
	
}