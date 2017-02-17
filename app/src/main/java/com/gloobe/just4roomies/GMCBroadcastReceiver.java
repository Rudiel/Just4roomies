package com.gloobe.just4roomies;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.gloobe.just4roomies.Actividades.Activity_Conversacion;
import com.gloobe.just4roomies.Actividades.Activity_Principal_Fragment;


/**
 * Created by rudielavilaperaza on 03/10/16.
 */
public class GMCBroadcastReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ComponentName comp = new ComponentName(context.getPackageName(),
                GCMIntentService.class.getName());

        //if(isRunning(context)){
        if (Activity_Conversacion.isActive) {
            try {
                Activity_Conversacion.getConversacion(Activity_Conversacion.chat_id, Activity_Conversacion.user_id, context);
            } catch (Exception e) {
            }
        } else {
            startWakefulService(context, (intent.setComponent(comp)));
            setResultCode(Activity.RESULT_OK);
        }
    }

 /*   public boolean isRunning(Context ctx) {
        ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(Integer.SIZE);

        for (ActivityManager.RunningTaskInfo task : tasks) {
            if (task.topActivity.getClassName().equals("com.gloobe.just4roomies.Actividades.Activity_Conversacion"))
                return true;
        }

        return false;
    }*/
}
