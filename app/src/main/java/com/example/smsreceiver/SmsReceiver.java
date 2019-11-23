package com.example.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;



public class SmsReceiver extends BroadcastReceiver{

        //For intercept reception sms
        public static final String RECEIVE_MSG = "android.provider.Telephony.SMS_RECEIVED";

        @Override
        public void onReceive(Context context, Intent intent) {


            if(intent.getAction().equals(RECEIVE_MSG)){

                try {
                    Bundle extra = intent.getExtras();
                    if (extra != null) {
                        Object[] pdus = (Object[]) extra.get("pdus");

                        final SmsMessage[] messages = new SmsMessage[pdus.length];
                        for (int i = 0; i < pdus.length; i++) {
                            messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        }

                        if (messages.length > -1) {
                            for (int i = 0; i < messages.length; i++) {
                                final String messageBody = messages[i].getMessageBody();
                                final String phoneNumber = messages[i].getDisplayOriginatingAddress();

                                Toast.makeText(context, "Expediteur : " + phoneNumber, Toast.LENGTH_LONG).show();
                                Toast.makeText(context, "Message : " + messageBody, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }catch (Exception e){
                    Log.e("MySMSReceiver", "Exception : "+ e);
                }
            }
        }
}
