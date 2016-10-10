package com.gloobe.just4roomies.Actividades;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.gloobe.just4roomies.Interfaces.Just4Interface;
import com.gloobe.just4roomies.Modelos.RespuestaLoginFB;
import com.gloobe.just4roomies.Modelos.SocialLogin;
import com.gloobe.just4roomies.R;
import com.google.gson.Gson;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);

        progressDialog = new ProgressDialog(this, R.style.MyTheme);
        progressDialog.setCancelable(false);

        profile = Profile.getCurrentProfile();

        if (profile != null) {

            progressDialog.show();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getString(R.string.url_base))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Just4Interface service = retrofit.create(Just4Interface.class);

            SocialLogin socialLogin = new SocialLogin();
            socialLogin.setSocial_id(profile.getId());

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
}
