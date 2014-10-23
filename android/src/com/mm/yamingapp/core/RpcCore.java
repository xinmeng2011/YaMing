package com.mm.yamingapp.core;

import java.util.List;

import org.json.JSONArray;

import com.mm.yamingapp.network.INetworkHandler;;

public class RpcCore  implements INetworkHandler{


	public RpcCore(List<Class<? extends WrapperBase>> classes, IContext ctx){
		MethodsPool.getInstance().initMethodsMap(classes);
		MethodsPool.getInstance().initContext(ctx);
	}
	
	public void test(){
		try {
			JSONArray jArray = new JSONArray();
			jArray.put(1);
			MethodsPool.getInstance().invoke("clickOnButtonByIndex", jArray);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean doWith(String data) {
		return false;
	}
}
