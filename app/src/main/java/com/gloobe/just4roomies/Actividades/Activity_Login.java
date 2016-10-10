package com.gloobe.just4roomies.Actividades;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


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
    private RelativeLayout btLoginFB;
    private ImageView iv_fondo;
    private ProgressDialog progressDialog;
    private ProfileTracker mProfileTracker;
    private Profile profile;
    private Typeface typeface;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.layout_login);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/MavenPro_Regular.ttf");

        iv_fondo = (ImageView) findViewById(R.id.iv_login_fondo);

        Glide.with(this).load(R.drawable.bg_login).centerCrop().into(iv_fondo);
        ((Button) findViewById(R.id.btLoginFB)).setTypeface(typeface);
        ((TextView) findViewById(R.id.tvTerminosyCondiciones)).setTypeface(typeface);

        progressDialog = new ProgressDialog(this, R.style.MyTheme);
        progressDialog.setCancelable(false);

        callbackManager = CallbackManager.Factory.create();


        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

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


                        progressDialog.show();

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
                                socialLogin.setSocial_id(object.getString("id"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                        Call<RespuestaLoginFB> perfil = service.socialLogin(socialLogin);

                        perfil.enqueue(new Callback<RespuestaLoginFB>() {
                            @Override
                            public void onResponse(Call<RespuestaLoginFB> call, Response<RespuestaLoginFB> response) {

                                if (response.body() != null) {

                                    progressDialog.dismiss();

                                    Gson gson = new Gson();
                                    Intent intent = new Intent(Activity_Login.this, Activity_Principal_Fragment.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.putExtra("obj", gson.toJson(response.body()));
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                                } else {
                                    progressDialog.dismiss();

                                    Intent intent = new Intent(Activity_Login.this, Activity_Personalidad.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                                }
                            }

                            @Override
                            public void onFailure(Call<RespuestaLoginFB> call, Throwable t) {
                                progressDialog.dismiss();

                            }
                        });
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
        btLoginFB = (RelativeLayout) findViewById(R.id.rlLogin);
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
}
