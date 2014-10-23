package com.mm.yamingapp.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.googlecode.android_scripting.Log;
import com.mm.yamingapp.core.MethodDescriptor;

public class MethodsPool {
	private Map<Class<? extends WrapperBase>,WrapperBase> mWrapperInstances = new HashMap<Class<? extends WrapperBase>, WrapperBase>();
	private MethodsPool mInstance;
	private Map<String, MethodDescriptor> mMethodsMap = new HashMap<String, MethodDescriptor>();
	private IContext mIContext;
	
	private MethodsPool(){
	}
	
	public MethodsPool getInstance(){
		if(mInstance != null){
			return mInstance;
		}
		mInstance = new MethodsPool();
		return mInstance;
	}

	public void initMethodsMap(List<Class<? extends WrapperBase>> classList){
		//mMethodWrappers = wrappers;
	    for (Class<? extends WrapperBase> receiverClass : classList) {
	      mWrapperInstances.put(receiverClass, null);
	      Collection<MethodDescriptor> methodList = MethodDescriptor.collectFrom(receiverClass);
	      for (MethodDescriptor m : methodList) {
	        if (mMethodsMap.containsKey(m.getName())) {
	          // We already know an RPC of the same name. We don't catch this anywhere because this is a
	          // programming error.
	          throw new RuntimeException("An RPC with the name " + m.getName() + " is already known.");
	        }
	        mMethodsMap.put(m.getName(), m);
	      }
	    }
	}
	
	public void initContext(IContext ictx){
		mIContext = ictx;
	}
	
	  private WrapperBase get(Class<? extends WrapperBase> clazz) {
	    WrapperBase object = mWrapperInstances.get(clazz);
	    if (object != null) {
	      return object;
	    }

	    Constructor<? extends WrapperBase> constructor;
	    try {
	      constructor = clazz.getConstructor(getClass());
	      object = constructor.newInstance(mIContext);
	      mWrapperInstances.put(clazz, object);
	    } catch (Exception e) {
	      Log.e(e);
	    }

	    return object;
	  }

	  public <T extends WrapperBase> T getWrapperInstance(Class<T> clazz) {
	    WrapperBase receiver = get(clazz);
	    return clazz.cast(receiver);
	  }

	  public MethodDescriptor getMethodDescriptor(String methodName) {
	    return mMethodsMap.get(methodName);
	  }

	  public Object invoke(Class<? extends WrapperBase> clazz, Method method, Object[] args)
	      throws Exception {
	    WrapperBase object = get(clazz);
	    return method.invoke(object, args);
	  }
	  
	  public Object invoke(String methodName, JSONArray args) throws Throwable{
		  MethodDescriptor md = getMethodDescriptor(methodName);
		  Object obj = getWrapperInstance(md.getDeclaringClass());
		  return md.invoke(obj, args);
	  }
	
	public MethodDescriptor getMethodItem(String MethodName){
		return null;
	}
}
