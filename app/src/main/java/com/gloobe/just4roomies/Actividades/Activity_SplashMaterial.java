package com.gloobe.just4roomies.Actividades;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.gloobe.just4roomies.Interfaces.Just4Interface;
import com.gloobe.just4roomies.Modelos.RespuestaLoginFB;
import com.gloobe.just4roomies.Modelos.SocialLogin;
import com.gloobe.just4roomies.R;
import com.gloobe.just4roomies.Utils.Utilerias;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rudielavilaperaza on 03/10/16.
 */
public class Activity_SplashMaterial extends AppCompatActivity {

    private Profile profile;
    private ProgressDialog progressDialog;

    //GMC
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private String SENDER_ID = "1599014313";
    static final String TAG = "GCMDemo";

    GoogleCloudMessaging gcm;
    AtomicInteger msgId = new AtomicInteger();
    SharedPreferences prefs;
    Context context;

    String regid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);

        progressDialog = new ProgressDialog(this, R.style.MyTheme);
        progressDialog.setCancelable(false);

        profile = Profile.getCurrentProfile();

        context = getApplicationContext();

        inicio();

    }

    private void checarGMCRegister() {
        if (isGooglePlayServicesAvailable(this)) {
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId(context);
            //si el id que se tiene en sahred es igual al del perfil no se guarda nada
            //if(regid!=perfil.getProfile().getToken())
            if (regid.isEmpty()) {
                registerInBackground();
            } else
                iniciarSesion();
        } else {
        }
    }

    public boolean isGooglePlayServicesAvailable(Activity activity) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, 2404).show();
            }
            return false;
        }
        return true;
    }

    private String getTokenDevice() {
        return regid;
    }

    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGCMPreferences(context);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            return "";
        }
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing registration ID is not guaranteed to work with
        // the new app version.
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            return "";
        }
        return registrationId;
    }

    private SharedPreferences getGCMPreferences(Context context) {
        return getSharedPreferences(Activity_Personalidad.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }

    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    private void registerInBackground() {
        new registerID().execute();
    }

    public class registerID extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            String msg = "";
            try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(context);
                }
                regid = gcm.register(SENDER_ID);
                msg = "Device registered, registration ID=" + regid;
                Log.d(TAG, msg);
                storeRegistrationId(context, regid);
                //update token id

            } catch (IOException ex) {
                msg = "Error :" + ex.getMessage();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            iniciarSesion();
            progressDialog.dismiss();
        }
    }

    private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getGCMPreferences(context);
        int appVersion = getAppVersion(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.commit();
    }

    private void iniciarSesion() {
        if (profile != null) {

            progressDialog.show();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getString(R.string.url_base))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Just4Interface service = retrofit.create(Just4Interface.class);

            SocialLogin socialLogin = new SocialLogin();
            socialLogin.setSocial_id(profile.getId());
            socialLogin.setDevice("Android");
            socialLogin.setToken(regid);

            Call<RespuestaLoginFB> perfil = service.socialLogin(socialLogin);

            perfil.enqueue(new Callback<RespuestaLoginFB>() {
                @Override
                public void onResponse(Call<RespuestaLoginFB> call, Response<RespuestaLoginFB> response) {
                    if (response.code() == 200) {

                        progressDialog.dismiss();

                        Gson gson = new Gson();

                        Intent intent = new Intent(Activity_SplashMaterial.this, Activity_Principal_Fragment.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("obj", gson.toJson(response.body()));
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    } else {
                        progressDialog.dismiss();

                        Intent intent = new Intent(Activity_SplashMaterial.this, Activity_Personalidad.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    }
                }

                @Override
                public void onFailure(Call<RespuestaLoginFB> call, Throwable t) {
                    progressDialog.dismiss();

                }
            });

        } else {
            Intent intent = new Intent(Activity_SplashMaterial.this, Activity_Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            //overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
    }

    private void mostrarDialogo() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.revisar_conexion))
                .setTitle(getString(R.string.app_name));

        final android.app.AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
                inicio();
            }
        });
        dialog.show();
    }

    private void inicio() {
        if (Utilerias.haveNetworkConnection(this))
            checarGMCRegister();
        else
            mostrarDialogo();
    }


}
