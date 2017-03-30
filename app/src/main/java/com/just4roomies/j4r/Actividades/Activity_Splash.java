package com.just4roomies.j4r.Actividades;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.just4roomies.j4r.Interfaces.Just4Interface;
import com.just4roomies.j4r.Modelos.RespuestaLoginFB;
import com.just4roomies.j4r.Modelos.SocialLogin;
import com.just4roomies.j4r.R;
import com.just4roomies.j4r.Utils.Utilerias;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activity_Splash extends AppCompatActivity {


    private static final int SPLASH_TIME_OUT = 3000;
    private ImageView ivLogo;
    private Profile profile;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.layout_splash);


        profile = Profile.getCurrentProfile();

        ivLogo = (ImageView) findViewById(R.id.ivSplashLogo);

        progressDialog = new ProgressDialog(this, R.style.MyTheme);
        progressDialog.setCancelable(false);

        //Glide.with(this).load(R.drawable.fondo).centerCrop().into(ivFondo);
        //Glide.with(this).load(R.drawable.logo_name).into(ivLogo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (profile != null) {

                    progressDialog.show();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Utilerias.URL_GLOBAL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    Just4Interface service = retrofit.create(Just4Interface.class);

                    SocialLogin socialLogin = new SocialLogin();
                    socialLogin.setSocial_id(profile.getId());

                    Call<RespuestaLoginFB> perfil = service.socialLogin(socialLogin);

                    perfil.enqueue(new Callback<RespuestaLoginFB>() {
                        @Override
                        public void onResponse(Call<RespuestaLoginFB> call, Response<RespuestaLoginFB> response) {

                            if (response.body() != null) {

                                progressDialog.dismiss();

                                Gson gson = new Gson();

                                Intent intent = new Intent(Activity_Splash.this, Activity_Principal_Fragment.class);
                                //Intent intent = new Intent(Activity_Splash.this, Activity_Personalidad.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("obj", gson.toJson(response.body()));
                                startActivity(intent);
                                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                            } else {
                                progressDialog.dismiss();

                                Intent intent = new Intent(Activity_Splash.this, Activity_Personalidad.class);
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
                    Intent intent = new Intent(Activity_Splash.this, Activity_Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                }
            }
        }, SPLASH_TIME_OUT);
    }
}

