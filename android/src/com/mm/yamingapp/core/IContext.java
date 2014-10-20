package com.mm.yamingapp.core;

import android.app.Instrumentation;
import android.app.Service;
import android.content.Context;

public interface IContext {
	 Context getContext();
	 Instrumentation getInstrumentation() throws Exception;
	 Service getService() throws Exception;
}
