package com.mm.yamingapp.core;

import java.util.List;

import com.mm.yamingapp.network.INetworkHandler;;

public class RpcCore  implements INetworkHandler{

	private MethodsPool mMethodsPool;

	public RpcCore(List<Class<? extends WrapperBase>> classes, IContext ctx){
		mMethodsPool.getInstance().initMethodsMap(classes);
		mMethodsPool.getInstance().initContext(ctx);
	}
	
	public void test(){
		try {
			mMethodsPool.invoke("clickOnButtonByIndex", null);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean doWith(String data) {
		return false;
	}
}
