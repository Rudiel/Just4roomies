package com.gloobe.just4roomies.Actividades;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.gloobe.just4roomies.Adaptadores.Adapter_PlacesAutoComplete;
import com.gloobe.just4roomies.Interfaces.Interface_RecyclerView_Sugerencia;
import com.gloobe.just4roomies.Interfaces.Just4Interface;
import com.gloobe.just4roomies.Modelos.AddUser;
import com.gloobe.just4roomies.Modelos.RespuestaLoginFB;
import com.gloobe.just4roomies.Modelos.RespuestaUsuario;
import com.gloobe.just4roomies.Modelos.SocialLogin;
import com.gloobe.just4roomies.Modelos.User;
import com.gloobe.just4roomies.R;
import com.gloobe.just4roomies.Utils.Utilerias;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.gson.Gson;
import com.mukesh.countrypicker.fragments.CountryPicker;
import com.mukesh.countrypicker.interfaces.CountryPickerListener;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rudielavilaperaza on 17/08/16.
 */
public class Activity_Personalidad extends AppCompatActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, Interface_RecyclerView_Sugerencia {

    private EditText etNombre, etIdioma, etEdad, etNacionalidad, etPersonalidad_Ubicacion;
    private Button btGuardar, btNacionalidad;
    private ImageView ivImagenPersonalidad, ivFondo, ivPersonalidadUbicacionHint;

    private ImageView ivEstudiasTrue, ivEstudiasFalse;
    private ImageView ivCocinasTrue, ivCocinasFalse;
    private ImageView ivMascotasTrue, ivMascotasFalse;
    private ImageView ivFiesteroTrue, ivFiesteroFalse;
    private ImageView ivActivoTrue, ivActivoFalse;
    private ImageView ivFumasTrue, ivFumasFalse;

    private final int LOAD_IMAGE_CODE = 1;
    private String file;
    private String longitud, latitud, place;

    private int FUMAS_ESTADO;
    private int MASCOTA_ESTADO;
    private int ACTIVO_ESTADO;
    private int FIESTERO_ESTADO;
    private int ESTUDIA_ESTADO;
    private int COCINA_ESTADO;

    private Profile profile;

    private ProgressDialog progressDialog;
    private ProgressBar pbPersonalidadUbicacion;

    private String correo, cumple, genero, location;

    public static final String USER_DATA_FB = "USER_DATA_FB";

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
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private Geocoder geocoder;
    private LocationRequest mLocationRequest;

    //Recyclerview Sugerencias

    public static final LatLngBounds BOUNDS_MEX = new LatLngBounds(
            new LatLng(-0, 0), new LatLng(0, 0));

    private EditText etSugerencias;
    private RecyclerView rvSugerencias;
    private LinearLayoutManager linearLayoutManager;
    private Adapter_PlacesAutoComplete adapter_placesAutoComplete;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.layout_personalidad);

        context = getApplicationContext();

        if (isGooglePlayServicesAvailable(this)) {
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId(context);
            if (regid.isEmpty()) {
                registerInBackground();
            }
        } else {
        }

        buildGoogleApiClient();

        typeface = Typeface.createFromAsset(getAssets(), "fonts/MavenPro_Regular.ttf");

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            if (bundle.getString("email") != null) {
                correo = bundle.getString("email");
                guardarShared("email", correo);
            }
            if (bundle.getString("birthday") != null) {
                cumple = bundle.getString("birthday");
                guardarShared("birthday", cumple);
            }
            if (bundle.getString("gender") != null) {
                genero = bundle.getString("gender");
                guardarShared("gender", genero);
            }
            if (bundle.getString("location") != null) {
                location = bundle.getString("location");
                guardarShared("location", location);
            }
        } else {
            getshared();
        }

        profile = Profile.getCurrentProfile();

        ivImagenPersonalidad = (ImageView) findViewById(R.id.ivImagenPerfil);
        btGuardar = (Button) findViewById(R.id.btPersonalidadGuardar);
        ivFondo = (ImageView) findViewById(R.id.ivFondoPersonalidad);
        etPersonalidad_Ubicacion = (EditText) findViewById(R.id.etPersonalidad_Ubicacion);
        ivPersonalidadUbicacionHint = (ImageView) findViewById(R.id.ivPersonalidadUbicacionHint);
        pbPersonalidadUbicacion = (ProgressBar) findViewById(R.id.pbPersonalidadUbicacion);
        btNacionalidad = (Button) findViewById(R.id.etPersonalidad_NacionalidadTest);

        Glide.with(this).load(R.drawable.bg_perfil).centerCrop().into(ivFondo);

        //mascota
        ivMascotasTrue = (ImageView) findViewById(R.id.ivMascotasTrue);
        ivMascotasFalse = (ImageView) findViewById(R.id.ivMascotasFalse);

        //fumas
        ivFumasTrue = (ImageView) findViewById(R.id.ivFumasTrue);
        ivFumasFalse = (ImageView) findViewById(R.id.ivFumasFalse);

        //activo
        ivActivoTrue = (ImageView) findViewById(R.id.ivActivoTrue);
        ivActivoFalse = (ImageView) findViewById(R.id.ivActivoFalse);

        //fiestero
        ivFiesteroTrue = (ImageView) findViewById(R.id.ivFiesteroTrue);
        ivFiesteroFalse = (ImageView) findViewById(R.id.ivFiesteroFalse);

        //dedicacion
        ivEstudiasTrue = (ImageView) findViewById(R.id.ivEstudiasTrue);
        ivEstudiasFalse = (ImageView) findViewById(R.id.ivEstudiasFalse);

        //cocinas
        ivCocinasTrue = (ImageView) findViewById(R.id.ivCocinasTrue);
        ivCocinasFalse = (ImageView) findViewById(R.id.ivCocinasFalse);

        etNombre = (EditText) findViewById(R.id.etPersonalidad_Nombre);
        etNacionalidad = (EditText) findViewById(R.id.etPersonalidad_Nacionalidad);
        etIdioma = (EditText) findViewById(R.id.etPersonalidad_Idioma);
        etEdad = (EditText) findViewById(R.id.etPersonalidad_Edad);
        etSugerencias = (EditText) findViewById(R.id.etPersonalidad_Ubicacion);

        etNombre.setTypeface(typeface);
        etNacionalidad.setTypeface(typeface);
        etIdioma.setTypeface(typeface);
        etEdad.setTypeface(typeface);
        btGuardar.setTypeface(typeface);
        etPersonalidad_Ubicacion.setTypeface(typeface);

        ((TextView) findViewById(R.id.tvPersonalidad)).setTypeface(typeface);
        ((TextView) findViewById(R.id.tvPersonalidadFumas)).setTypeface(typeface);
        ((TextView) findViewById(R.id.tvPersonalidadMascota)).setTypeface(typeface);
        ((TextView) findViewById(R.id.tvPersonalidadActivo)).setTypeface(typeface);
        ((TextView) findViewById(R.id.tvPersonalidadCocinas)).setTypeface(typeface);
        ((TextView) findViewById(R.id.tvPersonalidadEstudias)).setTypeface(typeface);
        ((TextView) findViewById(R.id.tvPersonalidadFiestero)).setTypeface(typeface);

        ivImagenPersonalidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarImagen();
            }
        });

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (regid != null)
                    guardarUsuario();
            }
        });

        ivMascotasTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(Activity_Personalidad.this).load(R.drawable.mascota_true_activo).into(ivMascotasTrue);
                Glide.with(Activity_Personalidad.this).load(R.drawable.mascota_false_inactivo).into(ivMascotasFalse);

                MASCOTA_ESTADO = 1;
            }
        });

        ivMascotasTrue.performClick();

        ivMascotasFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(Activity_Personalidad.this).load(R.drawable.mascota_false_activo).into(ivMascotasFalse);
                Glide.with(Activity_Personalidad.this).load(R.drawable.mascota_true_inactivo).into(ivMascotasTrue);

                MASCOTA_ESTADO = 2;
            }
        });

        ivFumasTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(Activity_Personalidad.this).load(R.drawable.fumas_true_activo).into(ivFumasTrue);
                Glide.with(Activity_Personalidad.this).load(R.drawable.fumas_false_inactivo).into(ivFumasFalse);

                FUMAS_ESTADO = 1;
            }
        });
        ivFumasTrue.performClick();

        ivFumasFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(Activity_Personalidad.this).load(R.drawable.fumas_false_activo).into(ivFumasFalse);
                Glide.with(Activity_Personalidad.this).load(R.drawable.fumas_true_inactivo).into(ivFumasTrue);

                FUMAS_ESTADO = 2;
            }
        });

        ivActivoTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(Activity_Personalidad.this).load(R.drawable.activo_true_activo).into(ivActivoTrue);
                Glide.with(Activity_Personalidad.this).load(R.drawable.activo_false_inactivo).into(ivActivoFalse);

                ACTIVO_ESTADO = 1;
            }
        });
        ivActivoTrue.performClick();

        ivActivoFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(Activity_Personalidad.this).load(R.drawable.activo_false_activo).into(ivActivoFalse);
                Glide.with(Activity_Personalidad.this).load(R.drawable.activo_true_inactivo).into(ivActivoTrue);

                ACTIVO_ESTADO = 2;

            }
        });

        ivFiesteroTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(Activity_Personalidad.this).load(R.drawable.fiestero_true_activo).into(ivFiesteroTrue);
                Glide.with(Activity_Personalidad.this).load(R.drawable.fiestero_false_inactivo).into(ivFiesteroFalse);

                FIESTERO_ESTADO = 1;
            }
        });
        ivFiesteroTrue.performClick();

        ivFiesteroFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(Activity_Personalidad.this).load(R.drawable.fiestero_false_activo).into(ivFiesteroFalse);
                Glide.with(Activity_Personalidad.this).load(R.drawable.fiestero_true_inactivo).into(ivFiesteroTrue);

                FIESTERO_ESTADO = 2;
            }
        });

        ivEstudiasTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(Activity_Personalidad.this).load(R.drawable.estudia_true_activo).into(ivEstudiasTrue);
                Glide.with(Activity_Personalidad.this).load(R.drawable.estudia_false_inactivo).into(ivEstudiasFalse);

                ESTUDIA_ESTADO = 1;
            }
        });
        ivEstudiasTrue.performClick();

        ivEstudiasFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(Activity_Personalidad.this).load(R.drawable.estudia_false_activo).into(ivEstudiasFalse);
                Glide.with(Activity_Personalidad.this).load(R.drawable.estudia_true_inactivo).into(ivEstudiasTrue);

                ESTUDIA_ESTADO = 2;

            }
        });

        ivCocinasTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(Activity_Personalidad.this).load(R.drawable.cocina_true_activo).into(ivCocinasTrue);
                Glide.with(Activity_Personalidad.this).load(R.drawable.cocina_false_inactivo).into(ivCocinasFalse);

                COCINA_ESTADO = 1;
            }
        });
        ivCocinasTrue.performClick();

        ivCocinasFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(Activity_Personalidad.this).load(R.drawable.cocina_false_activo).into(ivCocinasFalse);
                Glide.with(Activity_Personalidad.this).load(R.drawable.cocina_true_inactivo).into(ivCocinasTrue);

                COCINA_ESTADO = 2;
            }
        });
        ivPersonalidadUbicacionHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingActiveRequest();

                settingActiveRequest();

                pbPersonalidadUbicacion.setVisibility(View.VISIBLE);
                ivPersonalidadUbicacionHint.setVisibility(View.INVISIBLE);

            }
        });

        if (profile != null) {
            etNombre.setText(profile.getName());
        }

        etSugerencias.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                rvSugerencias.setVisibility(View.INVISIBLE);
                if (!charSequence.toString().equals("") && mGoogleApiClient.isConnected()) {
                    adapter_placesAutoComplete.getFilter().filter(charSequence.toString());
                } else if (!mGoogleApiClient.isConnected()) {

                } else {
                    rvSugerencias.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals(""))
                    rvSugerencias.setVisibility(View.INVISIBLE);
                else
                    rvSugerencias.setVisibility(View.VISIBLE);
            }
        });

        progressDialog = new ProgressDialog(this, R.style.MyTheme);
        progressDialog.setCancelable(false);

        btNacionalidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CountryPicker picker = CountryPicker.newInstance("Select Country");
                picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");
                picker.setListener(new CountryPickerListener() {
                    @Override
                    public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                        // Implement your code here
                        Toast.makeText(Activity_Personalidad.this, name, Toast.LENGTH_SHORT).show();
                        picker.dismiss();
                    }
                });
            }
        });


        adapter_placesAutoComplete = new Adapter_PlacesAutoComplete(this, R.layout.layout_autocomplete, mGoogleApiClient, BOUNDS_MEX, null, Activity_Personalidad.this, typeface);
        rvSugerencias = (RecyclerView) findViewById(R.id.rvPersonalidadSugerencias);
        rvSugerencias.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        rvSugerencias.setLayoutManager(linearLayoutManager);
        rvSugerencias.setAdapter(adapter_placesAutoComplete);

        geocoder = new Geocoder(this, Locale.getDefault());

    }

    private void editarImagen() {
        Intent intent = CropImage.getPickImageChooserIntent(this);
        startActivityForResult(intent, CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE);
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == this.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);

            // For API >= 23 we need to check specifically that we have permissions to read external storage.
            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageUri)) {
                // request permissions and handle the result in onRequestPermissionsResult()
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
            } else {
                // no permissions required or already grunted, can start crop image activity
                startCropImageActivity(imageUri);
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == this.RESULT_OK) {
                Uri resultUri = result.getUri();
                file = resultUri.getPath();

                Glide.with(Activity_Personalidad.this).load(file).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivImagenPersonalidad) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getBaseContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        ivImagenPersonalidad.setImageDrawable(circularBitmapDrawable);
                    }
                });

                ((ImageView) findViewById(R.id.ivIconoIamgenPerfil)).setVisibility(View.GONE);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
        if (requestCode == 0x1) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    if (checkLocationPermission()) {
                        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    settingActiveRequest();
                    break;
            }
        }
    }

    private void guardarUsuario() {

        progressDialog.show();

        final AddUser addUser = new AddUser();


        if (validarCampos()) {

            addUser.setName(etNombre.getText().toString());
            if (file != null)
                addUser.setPhoto(convertImagetoBase64(file));
            addUser.setSocial_id(profile.getId());

            if (correo == null || correo == "")
                getshared();

            addUser.setEmail(correo);
            addUser.setAge(etEdad.getText().toString());
            addUser.setNationality(etNacionalidad.getText().toString());
            addUser.setLanguaje(etIdioma.getText().toString());
            addUser.setGender(genero);

            if (FUMAS_ESTADO == 1)
                addUser.setSmoke(true);
            else
                addUser.setSmoke(false);

            if (ACTIVO_ESTADO == 1)
                addUser.setActive(true);
            else
                addUser.setActive(false);

            if (ESTUDIA_ESTADO == 1)
                addUser.setStudent(true);
            else
                addUser.setStudent(false);

            if (COCINA_ESTADO == 1)
                addUser.setCook(true);
            else
                addUser.setCook(false);

            if (MASCOTA_ESTADO == 1)
                addUser.setPet(true);
            else
                addUser.setPet(false);

            if (FIESTERO_ESTADO == 1)
                addUser.setParty(true);
            else
                addUser.setParty(false);

            addUser.setDevice("Android");
            addUser.setToken(getTokenDevice());

            if (place != null) {
                addUser.setPlace(place);
                addUser.setLongitud(longitud);
                addUser.setLatitud(latitud);
            }

        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utilerias.URL_GLOBAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Just4Interface service = retrofit.create(Just4Interface.class);

        Call<RespuestaUsuario> user = service.registarUsuario(addUser);

        user.enqueue(new Callback<RespuestaUsuario>() {
            @Override
            public void onResponse(Call<RespuestaUsuario> call, Response<RespuestaUsuario> response) {
                if (response.body() != null) {

                    User usuario = response.body().getUser();
                    if (usuario != null)
                        inciarSesion();
                    else
                        mostrarAlerta(getResources().getString(R.string.personalidad_dialogo_titulo), getResources().getString(R.string.personalidad_dailogo_mal));
                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
                    mostrarAlerta(getResources().getString(R.string.personalidad_dialogo_titulo), getResources().getString(R.string.personalidad_dailogo_mal));
                }

            }

            @Override
            public void onFailure(Call<RespuestaUsuario> call, Throwable t) {
                progressDialog.dismiss();
                mostrarAlerta(getResources().getString(R.string.personalidad_dialogo_titulo), getResources().getString(R.string.personalidad_dailogo_mal));
            }
        });

    }

    private void inciarSesion() {

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
                    Gson gson = new Gson();

                    progressDialog.dismiss();


                    Intent intent = new Intent(Activity_Personalidad.this, Activity_Principal_Fragment.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("obj", gson.toJson(response.body()));
                    startActivity(intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                } else {
                    mostrarAlerta("", "");
                }


            }

            @Override
            public void onFailure(Call<RespuestaLoginFB> call, Throwable t) {

            }
        });

    }

    private boolean validarCampos() {
        if (etNombre.getText().toString().trim().isEmpty()) {
            mostrarAlerta(getResources().getString(R.string.personalidad_dialogo_titulo), getResources().getString(R.string.personalidad_agregar_dialogo_nombre));
            return false;
        }
        if (etNacionalidad.getText().toString().trim().isEmpty()) {
            mostrarAlerta(getResources().getString(R.string.personalidad_dialogo_titulo), getResources().getString(R.string.personalidad_agregar_dialogo_nacionalidad));
            return false;
        }
        if (etIdioma.getText().toString().trim().isEmpty()) {
            mostrarAlerta(getResources().getString(R.string.personalidad_dialogo_titulo), getResources().getString(R.string.personalidad_agregar_dialogo_idioma));
            return false;
        }
        if (etEdad.getText().toString().trim().isEmpty()) {
            mostrarAlerta(getResources().getString(R.string.personalidad_dialogo_titulo), getResources().getString(R.string.personalidad_agregar_dialogo_edad));
            return false;
        }
        return true;

    }

    public void mostrarAlerta(String titulo, String mensaje) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage(mensaje)
                .setTitle(titulo);
        final android.app.AlertDialog dialog = builder.create();
        dialog.setButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void guardarShared(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences(USER_DATA_FB, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    private void getshared() {
        SharedPreferences preferences = getSharedPreferences(USER_DATA_FB, MODE_PRIVATE);
        correo = preferences.getString("email", "");
        cumple = preferences.getString("birthday", "");
        genero = preferences.getString("gender", "");
        location = preferences.getString("location", "");
    }

    private String getTokenDevice() {
        return regid;
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

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                //Mostarmos una alerta al usuario sin bloquear el hilo
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 22);
            }
        }
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void clickSugerencia(View view, int position, final Adapter_PlacesAutoComplete.PlaceAutocomplete placeAutocomplete) {
        final String placeId = String.valueOf(placeAutocomplete.placeId);


        PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                .getPlaceById(mGoogleApiClient, placeId);
        placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
            @Override
            public void onResult(PlaceBuffer places) {
                if (places.getCount() == 1) {
                    latitud = String.valueOf(places.get(0).getLatLng().latitude);
                    longitud = String.valueOf(places.get(0).getLatLng().longitude);
                    place = placeAutocomplete.description.toString();
                } else {
                    Toast.makeText(Activity_Personalidad.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        etSugerencias.setText(placeAutocomplete.description);

        rvSugerencias.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLocationChanged(Location location) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5000);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PermissionChecker.PERMISSION_GRANTED)
            return;
        mLocation = location;

        if (mLocation != null) {
            /*place = obtenerNombreCiudad(mLocation.getLatitude(), mLocation.getLongitude());
            latitud = String.valueOf(mLocation.getLatitude());
            longitud = String.valueOf(mLocation.getLongitude());
            pbPersonalidadUbicacion.setVisibility(View.INVISIBLE);
            ivPersonalidadUbicacionHint.setVisibility(View.VISIBLE);
            etSugerencias.setText(obtenerNombreCiudad(mLocation.getLatitude(), mLocation.getLongitude()));
            rvSugerencias.setVisibility(View.INVISIBLE);*/
            getCurrentLocation();
        }

        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    public String obtenerNombreCiudad(Double latitude, Double longitud) {
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(latitude, longitud, 1);
            if (addresses.size() > 0) {
                return addresses.get(0).getLocality().toString();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return "";

    }


    public class registerID extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String msg = "";
            try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(context);
                }
                regid = gcm.register(SENDER_ID);
                msg = "Device registered, registration ID=" + regid;
                storeRegistrationId(context, regid);

            } catch (IOException ex) {
                msg = "Error :" + ex.getMessage();
            }
            return msg;
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 22: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
            }
        }
    }


    private void settingActiveRequest() {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);

        final LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);

        final PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();
                final LocationSettingsStates states = locationSettingsResult.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        if (checkLocationPermission())
                            mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

                        if (mLocation != null) {
                            /*place = obtenerNombreCiudad(mLocation.getLatitude(), mLocation.getLongitude());
                            latitud = String.valueOf(mLocation.getLatitude());
                            longitud = String.valueOf(mLocation.getLongitude());
                            pbPersonalidadUbicacion.setVisibility(View.INVISIBLE);
                            ivPersonalidadUbicacionHint.setVisibility(View.VISIBLE);
                            etSugerencias.setText(obtenerNombreCiudad(mLocation.getLatitude(), mLocation.getLongitude()));
                            rvSugerencias.setVisibility(View.INVISIBLE);*/
                            getCurrentLocation();
                        } else {
                            mGoogleApiClient.connect();
                        }
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(Activity_Personalidad.this, 0x1);
                        } catch (IntentSender.SendIntentException e) {
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;

                }

            }
        });
    }

    private void startCropImageActivity(Uri imageUri) {
        Intent intent = CropImage.activity(imageUri)
                .setBorderLineColor(getResources().getColor(R.color.naranja_degradado))
                .setBackgroundColor(getResources().getColor(R.color.naranja_degradado_transparente))
                .setActivityTitle(getString(R.string.app_name))
                .getIntent(this);
        startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    private String convertImagetoBase64(String imagePath) {

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inSampleSize = 2;
        options.inPurgeable = true;

        Bitmap bm = BitmapFactory.decodeFile(imagePath, options);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 75, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();

        return Base64.encodeToString(b, Base64.NO_WRAP);

    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                //TODO:
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                //(just doing it here for now, note that with this code, no explanation is shown)
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        22);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        22);
            }
            return false;
        } else {
            return true;
        }
    }

    private void getCurrentLocation() {

        pbPersonalidadUbicacion.setVisibility(View.VISIBLE);

        if (mGoogleApiClient != null) {

            if (checkLocationPermission()) {
                PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi.getCurrentPlace(mGoogleApiClient, null);
                result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
                    @Override
                    public void onResult(@NonNull PlaceLikelihoodBuffer placeLikelihoods) {
                        if (placeLikelihoods.getCount() <= 0) {
                            rvSugerencias.setVisibility(View.INVISIBLE);
                            pbPersonalidadUbicacion.setVisibility(View.INVISIBLE);
                            ivPersonalidadUbicacionHint.setVisibility(View.VISIBLE);

                        } else {
                            //place= placeLikelihoods.get(0).getPlace().getName().toString();
                            place = placeLikelihoods.get(0).getPlace().getAddress().toString();
                            longitud = String.valueOf(placeLikelihoods.get(0).getPlace().getLatLng().longitude);
                            latitud = String.valueOf(placeLikelihoods.get(0).getPlace().getLatLng().latitude);
                            pbPersonalidadUbicacion.setVisibility(View.INVISIBLE);
                            ivPersonalidadUbicacionHint.setVisibility(View.VISIBLE);
                            etSugerencias.setText(place);
                            //etSugerencias.setText(obtenerNombreCiudad(mLocation.getLatitude(), mLocation.getLongitude()));
                            rvSugerencias.setVisibility(View.INVISIBLE);

                        }
                    }
                });
            }
        }

    }


}
