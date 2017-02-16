package com.gloobe.just4roomies;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.gloobe.just4roomies.Actividades.Activity_Conversacion;
import com.gloobe.just4roomies.Actividades.Activity_Principal_Fragment;
import com.gloobe.just4roomies.Actividades.Activity_SplashMaterial;
import com.gloobe.just4roomies.Utils.Utilerias;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import me.leolin.shortcutbadger.ShortcutBadger;

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
            try {
                if (extras.getString("message").equals("Tienes una solicitud de chat")) {
                    //solo inicio la aplicacion
                    if (Utilerias.getNotificationsEnabled(this))
                        sendNotificationSolicitud(extras.getString("message"), extras.getString("title"), extras.getString("image"));
                } else {
                    if (Utilerias.getNotificationsEnabled(this))
                        sendNotification(extras.getString("message"), extras.getString("title"), extras.getString("chat_id"), extras.getString("image"), extras.getString("id_receiver"));
                }
                Log.d("NOTIFICATION", "" + extras.toString());
            } catch (Exception e) {
            }

            saveNotificationBadge();
        }
        GMCBroadcastReceiver.completeWakefulIntent(intent);
    }

    private void sendNotification(String msg, String title, String chat_id, String chat_photo, String user_id) {
        NotificationManager mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, Activity_Conversacion.class);

        Bundle bundle = new Bundle();

        bundle.putInt("CHAT_ID", Integer.valueOf(chat_id));
        bundle.putInt("USER_ID", Integer.valueOf(user_id));
        bundle.putString("CHAT_PHOTO", chat_photo);
        bundle.putString("CHAT_NAME", title);

        intent.putExtras(bundle);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        stackBuilder.addNextIntentWithParentStack(intent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);

        android.support.v4.app.NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setLargeIcon(getBitmapFromURL(chat_photo))
                        .setSmallIcon(R.drawable.ic_app_notification)
                        .setContentTitle(title)
                        .setDefaults(android.support.v4.app.NotificationCompat.DEFAULT_SOUND)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setAutoCancel(true)
                        .setContentText(msg);

        mBuilder.setContentIntent(pendingIntent);
        mNotificationManager.notify(1, mBuilder.build());
    }

    private void sendNotificationSolicitud(String msg, String title, String chat_photo) {
        NotificationManager mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, Activity_SplashMaterial.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        stackBuilder.addNextIntentWithParentStack(intent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        android.support.v4.app.NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setLargeIcon(getBitmapFromURL(chat_photo))
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

    private Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return getCroppedBitmap(myBitmap);
        } catch (IOException e) {
            // Log exception
            return null;


        }
    }

    private Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
        //return _bmp;
        return output;
    }

    private void saveNotificationBadge() {

        int badgeCount;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        badgeCount = preferences.getInt("BADGE", 0);
        badgeCount = badgeCount + 1;

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("BADGE", badgeCount);
        editor.apply();

        ShortcutBadger.applyCount(getApplicationContext(), badgeCount); //for 1.1.4+
    }
}
