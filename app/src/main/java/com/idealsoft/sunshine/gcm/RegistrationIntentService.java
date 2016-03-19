package com.idealsoft.sunshine.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.idealsoft.sunshine.MainActivity;
import com.idealsoft.sunshine.R;

/**
 * Created by Usuario on 18/03/2016.
 */
public class RegistrationIntentService extends IntentService {
    public static final String TAG="RegIntentService";
    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

        try{
            //In the (unlikely) event that multiple refresh operations occur simultaneously,
            //ensure that they are processed sequentially.
            synchronized (TAG){
                //Initially this call goes out to the network to retrieve the token, subsequent calls
                //are local.
                InstanceID instanceID= InstanceID.getInstance(this);

                //TODO: gcm_default sender ID comes from the API console
                String senderId=getString(R.string.gcm_defaultSenderId);
                if(senderId.length()!=0){
                    String token =instanceID.getToken(senderId,
                            GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);
                    sendRegistrationToServer(token);
                }

                //You should store a boolean that indicates whether the generated token has been
                //sent to your server. if the boolean is false, send the token to your server,
                //otherwise your server should have already received the token.
                sharedPreferences.edit().putBoolean(MainActivity.SENT_TOKEN_TO_SERVER,true).apply();
            }
        }catch (Exception e){

        }

    }

    /*
    *   Normally, you would want to persist the registration to third-party servers. Because we do
    *   not have a server, and are faking it with a website, you'll want to log the token instead.
    *   That way you can see the value in logcat, and note it for future use in the website.
    *   @param token The new toke.
     */
    private void sendRegistrationToServer(String token) {
        Log.i(TAG,"GCM Registration Token: " + token);
    }
}
