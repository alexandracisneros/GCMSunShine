package com.idealsoft.sunshine.gcm;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by Usuario on 18/03/2016.
 */
public class MyInstanceIDListenerService extends InstanceIDListenerService {
    public static final String TAG="MyInstanceIDLS";

    /**
     * Called if InstanceID token is updated. This my occurred if the security of
     * the previous token had been compromised. This called is initiated by the
     * InstanceID provider.
     */
    @Override
    public void onTokenRefresh() {
        //Fetch updated Instance ID token.
        Intent intent=new Intent(this,RegistrationIntentService.class);
        startService(intent);
    }
}
