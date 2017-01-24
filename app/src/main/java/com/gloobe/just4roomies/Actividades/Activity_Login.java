package com.gloobe.just4roomies.Actividades;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gloobe.just4roomies.Interfaces.Just4Interface;
import com.gloobe.just4roomies.Modelos.RespuestaLoginFB;
import com.gloobe.just4roomies.Modelos.SocialLogin;
import com.gloobe.just4roomies.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rudielavilaperaza on 17/08/16.
 */
public class Activity_Login extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private CardView btLoginFB;
    private ImageView iv_fondo;
    private ProgressDialog progressDialog;
    private ProfileTracker mProfileTracker;
    private Profile profile;
    private Typeface typeface;

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

    //
    private Bundle mbundle;
    private JSONObject jsonObject;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.layout_login);

        context = getApplicationContext();

        typeface = Typeface.createFromAsset(getAssets(), "fonts/MavenPro_Regular.ttf");

        iv_fondo = (ImageView) findViewById(R.id.iv_login_fondo);

        Glide.with(this).load(R.drawable.bg_login).centerCrop().into(iv_fondo);
        ((TextView) findViewById(R.id.tvLoginFB)).setTypeface(typeface);
        ((TextView) findViewById(R.id.tvTerminosyCondiciones)).setTypeface(typeface);

        progressDialog = new ProgressDialog(this, R.style.MyTheme);
        progressDialog.setCancelable(false);

        callbackManager = CallbackManager.Factory.create();


        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                progressDialog.show();

                if ((Profile.getCurrentProfile() == null)) {
                    mProfileTracker = new ProfileTracker() {
                        @Override
                        protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                            profile = currentProfile;
                            mProfileTracker.stopTracking();
                        }
                    };
                } else
                    profile = Profile.getCurrentProfile();

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        final Bundle bundle = new Bundle();

                        try {
                            String birthday = object.getString("birthday");
                            bundle.putString("birthday", birthday);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            String email = object.getString("email");
                            bundle.putString("email", email);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            String gender = object.getString("gender");
                            bundle.putString("gender", gender);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            String location = object.getString("location");
                            bundle.putString("location", location);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        mbundle = bundle;
                        jsonObject = object;

                        checarGMCRegister();

                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(Activity_Login.this, getResources().getString(R.string.mensaje_error), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(Activity_Login.this, getResources().getString(R.string.mensaje_error), Toast.LENGTH_SHORT).show();
            }
        });
        btLoginFB = (CardView) findViewById(R.id.rlLogin);
        btLoginFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.performClick();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.url_base))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Just4Interface service = retrofit.create(Just4Interface.class);

        SocialLogin socialLogin = new SocialLogin();
        if (profile != null)
            socialLogin.setSocial_id(profile.getId());
        else {
            try {
                socialLogin.setSocial_id(jsonObject.getString("id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        socialLogin.setToken(regid);
        socialLogin.setDevice("Android");


        Call<RespuestaLoginFB> perfil = service.socialLogin(socialLogin);

        perfil.enqueue(new Callback<RespuestaLoginFB>() {
            @Override
            public void onResponse(Call<RespuestaLoginFB> call, Response<RespuestaLoginFB> response) {

                if (response.body() != null) {

                    progressDialog.dismiss();

                    Gson gson = new Gson();
                    Intent intent = new Intent(Activity_Login.this, Activity_Principal_Fragment.class);
                    //Intent intent = new Intent(Activity_Login.this, Activity_Personalidad.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("obj", gson.toJson(response.body()));
                    startActivity(intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                } else {
                    progressDialog.dismiss();

                    Intent intent = new Intent(Activity_Login.this, Activity_Personalidad.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtras(mbundle);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                }
            }

            @Override
            public void onFailure(Call<RespuestaLoginFB> call, Throwable t) {

                progressDialog.dismiss();
                showDialog();
            }
        });
    }

    private void showDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage(R.string.mensaje_error)
                .setTitle(R.string.login_dialog_title);

        final android.app.AlertDialog dialog = builder.create();
        dialog.setCancelable(false);


        builder.setNeutralButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                iniciarSesion();
            }
        });
        dialog.show();
    }

}
