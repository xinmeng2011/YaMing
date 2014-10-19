package com.googlecode.android_scripting.facade;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.telephony.SmsManager;

import com.android.internal.telephony.ITelephony;
import com.googlecode.android_scripting.jsonrpc.RpcReceiver;
import com.googlecode.android_scripting.rpc.Rpc;
import com.googlecode.android_scripting.rpc.RpcParameter;

/**
 * Droid API - Facade for call/sms operations.
 * 
 */
public class PhoneFacade extends RpcReceiver {

    private static final Uri SMS_URI = Uri.parse("content://sms");
    
	private final Context mCtx;
	private final ContentResolver mContentResolver;

	public PhoneFacade(FacadeManager manager) {
		super(manager);
		mCtx = manager.getService();
		mContentResolver = mCtx.getContentResolver();
	}

	@Rpc(description = "Make a call to the given phone number.")
	public void call(@RpcParameter(name = "number") String number) {
		String uri = "tel:" + number;
		Intent i = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mCtx.startActivity(i);
	}

	@Rpc(description = "End the ongoing call.", returns = "True if operation success, otherwise false.")
	public Boolean endCall() {
		try {
			ITelephony tpCallModule = ITelephony.Stub
					.asInterface(ServiceManager
							.getService(Context.TELEPHONY_SERVICE));
			return tpCallModule.endCall();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Rpc(description = "Send a sms to the given phone number.")
	public void sendSms(@RpcParameter(name = "number") String number, 
                @RpcParameter(name = "message", description = "The text content of message.") String message) {
		SmsManager smsManager = SmsManager.getDefault();
		ArrayList<String> msgArray = smsManager.divideMessage(message);
		if (msgArray.size() == 1) {
			smsManager.sendTextMessage(number, null, message, null, null);
		} else if (msgArray.size() > 1) {
			smsManager.sendMultipartTextMessage(number, null, msgArray, null,
					null);
		}
	}
	
	@Rpc(description = "Query the latest received SMS.", returns = "The JSON object with the content of sms.")
	public JSONObject getLatestReceivedSms() throws JSONException {
		JSONObject message = null;
		Uri.Builder builder = SMS_URI.buildUpon();
		builder.appendPath("inbox");
		Uri uri = builder.build();
		String[] columns = new String[] {"_id", "address", "date", "body"};
		Cursor cursor = mContentResolver.query(uri, columns, null, null, "date desc");
		if (cursor != null && cursor.moveToFirst()) {
			message = new JSONObject();
			for (int i = 0; i < columns.length; i++) {
				message.put(columns[i], cursor.getString(i));
			}
		}
		return message;
	}

	@Override
	public void shutdown() {
	}
}
