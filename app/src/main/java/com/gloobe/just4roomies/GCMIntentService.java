package com.gloobe.just4roomies;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.gloobe.just4roomies.Actividades.Activity_Conversacion;
import com.gloobe.just4roomies.Actividades.Activity_Principal_Fragment;
import com.gloobe.just4roomies.Actividades.Activity_SplashMaterial;
import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * Created by rudielavilaperaza on 03/10/16.
 */
public class GCMIntentService extends IntentService {

    public GCMIntentService() {
        super("GCMIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty() && GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
            sendNotification(extras.getString("message"), extras.getString("title"), extras.getString("chat_id"));
            Log.d("NOTIFICATION", "" + extras.toString());
        }
        GMCBroadcastReceiver.completeWakefulIntent(intent);
    }

    private void sendNotification(String msg, String title, String chat_id) {
        NotificationManager mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, Activity_Conversacion.class);

        Bundle bundle = new Bundle();

        bundle.putInt("CHAT_ID", Integer.valueOf(chat_id));
        bundle.putInt("USER_ID", 1);
        bundle.putString("CHAT_PHOTO", "");
        bundle.putString("CHAT_NAME", "");

        intent.putExtras(bundle);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        stackBuilder.addNextIntentWithParentStack(intent);

        PendingIntent pendingIntent= stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        android.support.v4.app.NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setSmallIcon(R.drawable.ic_app_notification)
                        .setContentTitle(title)
                        .setDefaults(android.support.v4.app.NotificationCompat.DEFAULT_SOUND)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setAutoCancel(true)
                        .setContentText(msg);

        mBuilder.setContentIntent(pendingIntent);
        mNotificationManager.notify(0, mBuilder.build());
    }

}
