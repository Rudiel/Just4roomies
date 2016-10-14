package com.gloobe.just4roomies.Actividades;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.gloobe.just4roomies.Fragments.Fragment_Ajustes;
import com.gloobe.just4roomies.Fragments.Fragment_Chat;
import com.gloobe.just4roomies.Fragments.Fragment_Contactanos;
import com.gloobe.just4roomies.Fragments.Fragment_EditarPerfil;
import com.gloobe.just4roomies.Fragments.Fragment_Perfiles;
import com.gloobe.just4roomies.Fragments.Frgament_OfrecerHabitacion;
import com.gloobe.just4roomies.Interfaces.Just4Interface;
import com.gloobe.just4roomies.Modelos.Model_Chat;
import com.gloobe.just4roomies.Modelos.Model_Chat_Solicitudes;
import com.gloobe.just4roomies.Modelos.Model_Perfiles;
import com.gloobe.just4roomies.Modelos.Model_Profiles;
import com.gloobe.just4roomies.Modelos.Model_Room;
import com.gloobe.just4roomies.Modelos.Personalidad_String;
import com.gloobe.just4roomies.Modelos.RespuestaLoginFB;
import com.gloobe.just4roomies.R;
import com.gloobe.just4roomies.Utils.FontsOverride;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rudielavilaperaza on 24/08/16.
 */
public class Activity_Principal_Fragment extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ImageView ivImagenPerfilMenu;
    private Profile profile;
    private NavigationView navigationView;
    public ImageView ivFiltros;
    public RespuestaLoginFB perfil;
    private ProgressDialog progressDialog;

    public Typeface typeFace;

    public List<Model_Profiles> arrRoomiesPreferidos;
    public List<Model_Profiles> arrRoomiesRechazados;
    public List<Model_Profiles> arrRomies;
    public List<Model_Profiles> arrRoomiesAux;

    public ArrayList<Model_Chat> arrChats;
    public ArrayList<Model_Chat_Solicitudes> arrSolicitudes;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.layuot_principal_fragment);

        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("obj");
        perfil = gson.fromJson(strObj, RespuestaLoginFB.class);

        if (perfil == null) {

            RespuestaLoginFB respuesta = new RespuestaLoginFB();

            com.gloobe.just4roomies.Modelos.Profile newProfile = new com.gloobe.just4roomies.Modelos.Profile();
            Personalidad_String personalidad_string = new Personalidad_String();

            Bundle bundle = getIntent().getExtras();

            if (bundle != null) {
                newProfile.setEmail(bundle.getString("user_email"));
                newProfile.setName(bundle.getString("user_name"));
                newProfile.setPhoto("");
                newProfile.setPhoto2("");
                newProfile.setPhoto3("");
                newProfile.setAge(bundle.getString("user_age"));
                newProfile.setLanguage(bundle.getString("user_lenguaje"));
                newProfile.setId(bundle.getInt("user_id"));
                newProfile.setNationality(bundle.getString("user_nacionalidad"));

                if (profile != null)
                    newProfile.setSocial_id(profile.getId());
                else
                    newProfile.setSocial_id(bundle.getString("user_socialid"));

                //personalidad

                if (bundle.getBoolean("user_active"))
                    personalidad_string.setActive("1");
                else
                    personalidad_string.setActive("0");

                if (bundle.getBoolean("user_cook"))
                    personalidad_string.setCook("1");
                else
                    personalidad_string.setCook("0");

                if (bundle.getBoolean("user_party"))
                    personalidad_string.setParty("1");
                else
                    personalidad_string.setParty("0");

                if (bundle.getBoolean("user_pet"))
                    personalidad_string.setPet("1");
                else
                    personalidad_string.setPet("0");

                if (bundle.getBoolean("user_smoke"))
                    personalidad_string.setSmoke("1");
                else
                    personalidad_string.setSmoke("0");

                if (bundle.getBoolean("user_student"))
                    personalidad_string.setStudent("1");
                else
                    personalidad_string.setStudent("0");

                personalidad_string.setComments("");
                personalidad_string.setUser_id(String.valueOf(bundle.getInt("user_id")));

            }

            respuesta.setProfile(newProfile);
            respuesta.setPersonality(personalidad_string);
            respuesta.setRoom(new Model_Room());

            perfil = respuesta;
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);

        ivImagenPerfilMenu = (ImageView) headerView.findViewById(R.id.ivImagenPerfilMenu);
        ivFiltros = (ImageView) findViewById(R.id.ivPrincipalFiltro);

        setSupportActionBar(toolbar);

        typeFace = Typeface.createFromAsset(getAssets(), "fonts/MavenPro_Regular.ttf");

        FontsOverride.setDefaultFont(this, "MAVENPRO", "fonts/MavenPro_Regular.ttf");

        profile = Profile.getCurrentProfile();

        progressDialog = new ProgressDialog(this, R.style.MyTheme);
        progressDialog.setCancelable(false);

        arrRoomiesPreferidos = new ArrayList<>();
        arrRoomiesRechazados = new ArrayList<>();
        arrRomies = new ArrayList<>();
        arrRoomiesAux = new ArrayList<>();

        arrChats = new ArrayList<>();
        arrSolicitudes = new ArrayList<>();

        getProfiles();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_cerrarsesion:
                        ivFiltros.setVisibility(View.INVISIBLE);
                        LoginManager.getInstance().logOut();
                        drawerLayout.closeDrawers();
                        cerrarSesion();
                        return true;

                    case R.id.menu_buscarroomie:
                        ivFiltros.setVisibility(View.INVISIBLE);
                        setFragment(new Fragment_Perfiles());
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.menu_ofrecer:
                        ivFiltros.setVisibility(View.INVISIBLE);
                        setFragment(new Frgament_OfrecerHabitacion());
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.menu_chats:
                        ivFiltros.setVisibility(View.INVISIBLE);
                        setFragment(new Fragment_Chat());
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.menu_editarperfil:
                        ivFiltros.setVisibility(View.INVISIBLE);
                        setFragment(new Fragment_EditarPerfil());
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.menu_ajustes:
                        ivFiltros.setVisibility(View.INVISIBLE);
                        setFragment(new Fragment_Ajustes());
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.menu_calificarapp:
                        ivFiltros.setVisibility(View.INVISIBLE);
                        puntuarApp();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.menu_invitaramigos:
                        ivFiltros.setVisibility(View.INVISIBLE);
                        compartirApp();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.menu_contactanos:
                        ivFiltros.setVisibility(View.INVISIBLE);
                        setFragment(new Fragment_Contactanos());
                        drawerLayout.closeDrawers();
                        break;

                }
                return false;
            }
        });

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.app_name, R.string.app_name
        );

        drawerLayout.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

        updateImagenPerfil();

    }

    public void iniciarFragment(Fragment fragment, boolean backstack) {
        if (backstack)
            getSupportFragmentManager().beginTransaction().replace(R.id.flContenedor, fragment).addToBackStack(null).commit();
        else
            getSupportFragmentManager().beginTransaction().replace(R.id.flContenedor, fragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    private void cerrarSesion() {
        Intent intent = new Intent(Activity_Principal_Fragment.this, Activity_Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.flContenedor, fragment).commit();
    }

    private void puntuarApp() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
        }

    }

    private void compartirApp() {
        try {
            Intent intentCompartir = new Intent(Intent.ACTION_SEND);
            intentCompartir.setType("text/plain");
            intentCompartir.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
            String texto = getResources().getString(R.string.invitar_amigos) + "\n";
            texto = texto + "\n" + "market://details?id=" + getPackageName();
            intentCompartir.putExtra(Intent.EXTRA_TEXT, texto);
            startActivity(Intent.createChooser(intentCompartir, "choose one"));
        } catch (Exception e) {
        }
    }

    private void getProfiles() {
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.url_base))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Just4Interface service = retrofit.create(Just4Interface.class);

        Call<Model_Perfiles> perfiles = service.listadoPerfiles(this.perfil.getProfile().getId());

        perfiles.enqueue(new Callback<Model_Perfiles>() {
            @Override
            public void onResponse(Call<Model_Perfiles> call, Response<Model_Perfiles> response) {
                if (response.body() != null) {
                    Log.d("LISTADO", response.body().toString());
                    arrRomies = response.body().getProfiles();
                    progressDialog.dismiss();
                    iniciarFragment(new Fragment_Perfiles(), false);

                } else {
                    Log.d("LISTADO_MAL", response.body().toString());
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Model_Perfiles> call, Throwable t) {
                Log.d("LISTADO_MAL", t.toString());
                progressDialog.dismiss();
            }
        });
    }

    public void updateImagenPerfil() {
        if (perfil.getProfile().getPhoto() != null && !perfil.getProfile().getPhoto().equals("")) {

            Glide.with(Activity_Principal_Fragment.this).load(perfil.getProfile().getPhoto()).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivImagenPerfilMenu) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getBaseContext().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    ivImagenPerfilMenu.setImageDrawable(circularBitmapDrawable);
                }
            });

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0x1) {
            Fragment_EditarPerfil fragment = (Fragment_EditarPerfil) getSupportFragmentManager()
                    .findFragmentById(R.id.flContenedor);
            fragment.onActivityResult(requestCode, resultCode, data);
        }

    }
}
