package com.mm.yamingapp.core;

import java.util.List;

import org.json.JSONArray;

import com.mm.yamingapp.config.Config;
import com.mm.yamingapp.network.INetworkHandler;;

public class RpcCore  implements INetworkHandler{


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
	public boolean doWith(String data) {
		return false;
	}
}
