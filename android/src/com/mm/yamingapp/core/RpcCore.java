package com.mm.yamingapp.core;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.database.CursorJoiner.Result;
import android.util.Log;

import com.googlecode.android_scripting.jsonrpc.JsonRpcResult;
import com.mm.yamingapp.config.Config;
import com.mm.yamingapp.network.INetworkHandler;;

public class RpcCore  implements INetworkHandler{

	private String tag = "RpcCore_cindy";

	public RpcCore(List<Class<? extends WrapperBase>> classes, IContext ctx){
		MethodsPool.getInstance().initMethodsMap(classes);
		MethodsPool.getInstance().initContext(ctx);
	}
	
	public void test(){
		try {
			JSONArray j2 = new JSONArray();
			j2.put(Config.TESTED_APP_PACKAGE);
			j2.put(Config.TESTED_APP_CLASS);
			MethodsPool.getInstance().invoke("openMainActivity", j2);
			
			JSONArray jArray = new JSONArray();
			jArray.put(0);
			MethodsPool.getInstance().invoke("clickOnButtonByIndex", jArray);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Object doWith(String data) {
		int id = -1;
		String methodString = "can not find this method";
		try{
		      JSONObject request = new JSONObject(data);
		      id = request.getInt("id");
		      methodString = request.getString("method");
		      JSONArray params = request.getJSONArray("params");
		      Log.i(tag, String.valueOf(id) + " " + methodString);
		      Object result = MethodsPool.getInstance().invoke(methodString, params);
		      return JsonRpcResult.result(id, result);
		}
		catch( Throwable t){
			Log.i(tag, t.toString());
			try {
				return JsonRpcResult.error(id, t);
			} catch (JSONException e) {
				return null;
			}
		}
	}
}
