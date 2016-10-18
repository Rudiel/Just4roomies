package com.gloobe.just4roomies.Fragments;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.gloobe.just4roomies.Actividades.Activity_Principal_Fragment;
import com.gloobe.just4roomies.Adaptadores.Adapter_PlacesAutoComplete;
import com.gloobe.just4roomies.Interfaces.Interface_RecyclerView_Sugerencia;
import com.gloobe.just4roomies.Interfaces.Just4Interface;
import com.gloobe.just4roomies.Modelos.Model_UserUpdate;
import com.gloobe.just4roomies.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.gson.Gson;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rudielavilaperaza on 29/08/16.
 */
public class Fragment_EditarPerfil extends Fragment implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, Interface_RecyclerView_Sugerencia {

    private ImageView ivFondo, ivEditar1, ivEditar2, ivEditar3;
    private EditText etComentarios, etPrecio, etEdad, etNombre;

    private ImageView ivEstudiasTrue, ivEstudiasFalse;
    private ImageView ivCocinasTrue, ivCocinasFalse;
    private ImageView ivMascotasTrue, ivMascotasFalse;
    private ImageView ivFiesteroTrue, ivFiesteroFalse;
    private ImageView ivActivoTrue, ivActivoFalse;
    private ImageView ivFumasTrue, ivFumasFalse;
    private ImageView ivUbicacion;

    private Button btGuardar;

    private int FUMAS_ESTADO;
    private int MASCOTA_ESTADO;
    private int ACTIVO_ESTADO;
    private int FIESTERO_ESTADO;
    private int ESTUDIA_ESTADO;
    private int COCINA_ESTADO;

    private ProgressDialog progressDialog;
    private ProgressBar progressBarLocalizacion;

    private int ImagenID = 0;

    private String file1, file2, file3;
    private String place, latitud, longitud;

    private ProgressBar pbImagen1, pbImagen2, pbImagen3;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_editarperfil, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        buildGoogleApiClient();

        geocoder = new Geocoder(getActivity(), Locale.getDefault());

        progressDialog = new ProgressDialog(getActivity(), R.style.MyTheme);
        progressDialog.setCancelable(false);


        ivFondo = (ImageView) getActivity().findViewById(R.id.ivFondoEditarPerfil);
        btGuardar = (Button) getActivity().findViewById(R.id.btEditarPerfilGuardar);
        etComentarios = (EditText) getActivity().findViewById(R.id.etEditarPerfil_Descripcion);
        etPrecio = (EditText) getActivity().findViewById(R.id.etEditarPerfil_Precio);
        etEdad = (EditText) getActivity().findViewById(R.id.etEditarPerfil_Edad);
        etNombre = (EditText) getActivity().findViewById(R.id.etEditarPerfil_Nombre);
        etSugerencias = (EditText) getActivity().findViewById(R.id.etEditarPerfil_Ubicacion);
        ((TextView) getActivity().findViewById(R.id.tvUbicacion)).setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);


        ivEditar1 = (ImageView) getActivity().findViewById(R.id.ivEditar1);
        ivEditar2 = (ImageView) getActivity().findViewById(R.id.ivEditar2);
        ivEditar3 = (ImageView) getActivity().findViewById(R.id.ivEditar3);
        ivUbicacion = (ImageView) getActivity().findViewById(R.id.ivIconUbicacionHint);
        progressBarLocalizacion = (ProgressBar) getActivity().findViewById(R.id.pbLocalizacion);

        Glide.with(this).load(R.drawable.bg_perfil).centerCrop().into(ivFondo);

        pbImagen1 = (ProgressBar) getActivity().findViewById(R.id.pbImagen1);
        pbImagen2 = (ProgressBar) getActivity().findViewById(R.id.pbImagen2);
        pbImagen3 = (ProgressBar) getActivity().findViewById(R.id.pbImagen3);

        btGuardar.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        etComentarios.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        etPrecio.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        etPrecio.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        etNombre.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        etEdad.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        etSugerencias.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);


        if (!((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getPhoto().equals("") && ((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getPhoto() != null) {
            ivEditar1.setPadding(0, 0, 0, 0);
            pbImagen1.setVisibility(View.VISIBLE);
            Glide.with(this).load(((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getPhoto()).centerCrop().listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    pbImagen1.setVisibility(View.GONE);
                    return false;
                }
            }).into(ivEditar1);
        }

        if (!((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getPhoto2().equals("") && ((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getPhoto2() != null) {
            ivEditar2.setPadding(0, 0, 0, 0);
            pbImagen2.setVisibility(View.VISIBLE);
            Glide.with(this).load(((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getPhoto2()).centerCrop().listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    pbImagen2.setVisibility(View.GONE);
                    return false;
                }
            }).into(ivEditar2);
        }

        if (!((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getPhoto3().equals("") && ((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getPhoto3() != null) {
            ivEditar3.setPadding(0, 0, 0, 0);
            pbImagen3.setVisibility(View.VISIBLE);
            Glide.with(this).load(((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getPhoto3()).centerCrop().listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    pbImagen3.setVisibility(View.GONE);
                    return false;
                }
            }).into(ivEditar3);
        }

        if (((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().getComments() != null && !((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().getComments().equals(""))
            etComentarios.setText(((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().getComments());

        if (((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getAge() != null && !((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getAge().equals(""))
            etEdad.setText(((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getAge());

        if (((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getName() != null && !((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getName().equals(""))
            etNombre.setText(((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getName());

        if (((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getPlace() != null && !((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getPlace().equals(""))
            etSugerencias.setText(((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getPlace());

        if (((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getPrice() != null && !((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getPrice().equals(""))
            etPrecio.setText(((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getPrice());


        ((TextView) getActivity().findViewById(R.id.tvEditarActivo)).setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        ((TextView) getActivity().findViewById(R.id.tvEditarMascota)).setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        ((TextView) getActivity().findViewById(R.id.tvEditarCocinas)).setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        ((TextView) getActivity().findViewById(R.id.tvEditarEstudias)).setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        ((TextView) getActivity().findViewById(R.id.tvEditarFiestero)).setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        ((TextView) getActivity().findViewById(R.id.tvEditarFumas)).setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);


        //mascota
        ivMascotasTrue = (ImageView) getActivity().findViewById(R.id.ivMascotasTrue);
        ivMascotasFalse = (ImageView) getActivity().findViewById(R.id.ivMascotasFalse);

        //fumas
        ivFumasTrue = (ImageView) getActivity().findViewById(R.id.ivFumasTrue);
        ivFumasFalse = (ImageView) getActivity().findViewById(R.id.ivFumasFalse);

        //activo
        ivActivoTrue = (ImageView) getActivity().findViewById(R.id.ivActivoTrue);
        ivActivoFalse = (ImageView) getActivity().findViewById(R.id.ivActivoFalse);

        //fiestero
        ivFiesteroTrue = (ImageView) getActivity().findViewById(R.id.ivFiesteroTrue);
        ivFiesteroFalse = (ImageView) getActivity().findViewById(R.id.ivFiesteroFalse);

        //dedicacion
        ivEstudiasTrue = (ImageView) getActivity().findViewById(R.id.ivEstudiasTrue);
        ivEstudiasFalse = (ImageView) getActivity().findViewById(R.id.ivEstudiasFalse);

        //cocinas
        ivCocinasTrue = (ImageView) getActivity().findViewById(R.id.ivCocinasTrue);
        ivCocinasFalse = (ImageView) getActivity().findViewById(R.id.ivCocinasFalse);


        ivMascotasTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(getActivity()).load(R.drawable.mascota_true_activo).into(ivMascotasTrue);
                Glide.with(getActivity()).load(R.drawable.mascota_false_inactivo).into(ivMascotasFalse);

                MASCOTA_ESTADO = 1;
            }
        });

        ivMascotasFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(getActivity()).load(R.drawable.mascota_false_activo).into(ivMascotasFalse);
                Glide.with(getActivity()).load(R.drawable.mascota_true_inactivo).into(ivMascotasTrue);

                MASCOTA_ESTADO = 2;
            }
        });

        ivFumasTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(getActivity()).load(R.drawable.fumas_true_activo).into(ivFumasTrue);
                Glide.with(getActivity()).load(R.drawable.fumas_false_inactivo).into(ivFumasFalse);

                FUMAS_ESTADO = 1;
            }
        });

        ivFumasFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(getActivity()).load(R.drawable.fumas_false_activo).into(ivFumasFalse);
                Glide.with(getActivity()).load(R.drawable.fumas_true_inactivo).into(ivFumasTrue);

                FUMAS_ESTADO = 2;
            }
        });

        ivActivoTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(getActivity()).load(R.drawable.activo_true_activo).into(ivActivoTrue);
                Glide.with(getActivity()).load(R.drawable.activo_false_inactivo).into(ivActivoFalse);

                ACTIVO_ESTADO = 1;
            }
        });
        ivActivoFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(getActivity()).load(R.drawable.activo_false_activo).into(ivActivoFalse);
                Glide.with(getActivity()).load(R.drawable.activo_true_inactivo).into(ivActivoTrue);

                ACTIVO_ESTADO = 2;

            }
        });

        ivFiesteroTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(getActivity()).load(R.drawable.fiestero_true_activo).into(ivFiesteroTrue);
                Glide.with(getActivity()).load(R.drawable.fiestero_false_inactivo).into(ivFiesteroFalse);

                FIESTERO_ESTADO = 1;
            }
        });
        ivFiesteroTrue.performClick();

        ivFiesteroFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(getActivity()).load(R.drawable.fiestero_false_activo).into(ivFiesteroFalse);
                Glide.with(getActivity()).load(R.drawable.fiestero_true_inactivo).into(ivFiesteroTrue);

                FIESTERO_ESTADO = 2;
            }
        });

        ivEstudiasTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(getActivity()).load(R.drawable.estudia_true_activo).into(ivEstudiasTrue);
                Glide.with(getActivity()).load(R.drawable.estudia_false_inactivo).into(ivEstudiasFalse);

                ESTUDIA_ESTADO = 1;
            }
        });
        ivEstudiasFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(getActivity()).load(R.drawable.estudia_false_activo).into(ivEstudiasFalse);
                Glide.with(getActivity()).load(R.drawable.estudia_true_inactivo).into(ivEstudiasTrue);

                ESTUDIA_ESTADO = 2;

            }
        });

        ivCocinasTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(getActivity()).load(R.drawable.cocina_true_activo).into(ivCocinasTrue);
                Glide.with(getActivity()).load(R.drawable.cocina_false_inactivo).into(ivCocinasFalse);

                COCINA_ESTADO = 1;
            }
        });
        ivCocinasFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(getActivity()).load(R.drawable.cocina_false_activo).into(ivCocinasFalse);
                Glide.with(getActivity()).load(R.drawable.cocina_true_inactivo).into(ivCocinasTrue);

                COCINA_ESTADO = 2;
            }
        });

        //Estados del perfil

        if (((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().getActive().equals("1")) {
            ivActivoTrue.performClick();
        } else
            ivActivoFalse.performClick();

        if (((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().getCook().equals("1")) {
            ivCocinasTrue.performClick();
        } else
            ivCocinasFalse.performClick();

        if (((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().getParty().equals("1")) {
            ivFiesteroTrue.performClick();
        } else
            ivFiesteroFalse.performClick();

        if (((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().getPet().equals("1")) {
            ivMascotasTrue.performClick();
        } else
            ivMascotasTrue.performClick();

        if (((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().getSmoke().equals("1")) {
            ivFumasTrue.performClick();
        } else
            ivFumasFalse.performClick();

        if (((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().getStudent().equals("1")) {
            ivEstudiasTrue.performClick();
        } else
            ivEstudiasFalse.performClick();


        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarUsuario();
            }
        });

        ivEditar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagenID = 1;
                editarImagen();
            }
        });

        ivEditar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagenID = 2;
                editarImagen();
            }
        });
        ivEditar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagenID = 3;
                editarImagen();
            }
        });

        adapter_placesAutoComplete = new Adapter_PlacesAutoComplete(getActivity(), R.layout.layout_autocomplete, mGoogleApiClient, BOUNDS_MEX, null, Fragment_EditarPerfil.this, ((Activity_Principal_Fragment) getActivity()).typeFace);
        rvSugerencias = (RecyclerView) getActivity().findViewById(R.id.rvSugerencias);
        rvSugerencias.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        rvSugerencias.setLayoutManager(linearLayoutManager);
        rvSugerencias.setAdapter(adapter_placesAutoComplete);


        ivUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                settingActiveRequest();

                progressBarLocalizacion.setVisibility(View.VISIBLE);
                ivUbicacion.setVisibility(View.INVISIBLE);

            }
        });


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


    }


    private void editarImagen() {
        Intent intent = CropImage.getPickImageChooserIntent(getActivity());
        startActivityForResult(intent, CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE);
    }

    private void editarUsuario() {
        progressDialog.show();

        final Model_UserUpdate usuario = new Model_UserUpdate();

        usuario.setComments(etComentarios.getText().toString());
        usuario.setPrice(etPrecio.getText().toString());
        usuario.setUser_id(Integer.valueOf(((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().getUser_id()));

        if (FUMAS_ESTADO == 1)
            usuario.setSmoke(true);
        else
            usuario.setSmoke(false);

        if (ACTIVO_ESTADO == 1)
            usuario.setActive(true);
        else
            usuario.setActive(false);

        if (ESTUDIA_ESTADO == 1)
            usuario.setStudent(true);
        else
            usuario.setStudent(false);

        if (COCINA_ESTADO == 1)
            usuario.setCook(true);
        else
            usuario.setCook(false);

        if (MASCOTA_ESTADO == 1)
            usuario.setPet(true);
        else
            usuario.setPet(false);

        if (FIESTERO_ESTADO == 1)
            usuario.setParty(true);
        else
            usuario.setParty(false);

        if (file1 != null)
            usuario.setImage1(convertImagetoBase64(file1));
        if (file2 != null)
            usuario.setImage2(convertImagetoBase64(file2));
        if (file3 != null)
            usuario.setImage3(convertImagetoBase64(file3));

        if (place != null && !place.equals("")) {
            usuario.setLongitud(longitud);
            usuario.setLatitud(latitud);
            usuario.setPlace(place);
        }

        String gson = new Gson().toJson(usuario);

        Log.d("EDITARPERFIL", gson);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.url_base))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Just4Interface service = retrofit.create(Just4Interface.class);

        Call<ResponseBody> user = service.actualizarUsuario(usuario);

        user.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    guardarPerfilLocal(usuario);
                    mostrarAlerta(getString(R.string.editarperfil_dialog_titlo), getString(R.string.editarperfil_dialog_mensaje_bien));
                    ((Activity_Principal_Fragment) getActivity()).updateImagenPerfil();
                } else {
                    mostrarAlerta(getString(R.string.editarperfil_dialog_titlo), getString(R.string.editarperfil_dialog_mensaje_mal));
                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                mostrarAlerta(getString(R.string.editarperfil_dialog_titlo), getString(R.string.editarperfil_dialog_mensaje_mal));
            }
        });


    }

    public void mostrarAlerta(String titulo, String mensaje) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
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

    private void startCropImageActivity(Uri imageUri) {
        Intent intent = CropImage.activity(imageUri)
                .setBorderLineColor(getResources().getColor(R.color.naranja_degradado))
                .setBackgroundColor(getResources().getColor(R.color.naranja_degradado_transparente))
                .setActivityTitle(getString(R.string.app_name))
                .getIntent(getActivity());
        startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    private void guardarPerfilLocal(Model_UserUpdate usuario) {

        if (usuario.getActive())
            ((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().setActive("1");
        else
            ((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().setActive("0");

        if (usuario.getParty())
            ((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().setParty("1");
        else
            ((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().setParty("0");

        if (usuario.getPet())
            ((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().setPet("1");
        else
            ((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().setPet("0");

        if (usuario.getCook())
            ((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().setCook("1");
        else
            ((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().setCook("0");

        if (usuario.getSmoke())
            ((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().setSmoke("1");
        else
            ((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().setSmoke("0");

        if (usuario.getStudent())
            ((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().setStudent("1");
        else
            ((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().setStudent("0");

        if (file1 != null)
            ((Activity_Principal_Fragment) getActivity()).perfil.getProfile().setPhoto(file1);

        if (file2 != null)
            ((Activity_Principal_Fragment) getActivity()).perfil.getProfile().setPhoto2(file2);

        if (file3 != null)
            ((Activity_Principal_Fragment) getActivity()).perfil.getProfile().setPhoto3(file3);

        if (!etComentarios.getText().toString().equals(""))
            ((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().setComments(etComentarios.getText().toString());

        if (!etPrecio.getText().toString().equals(""))
            ((Activity_Principal_Fragment) getActivity()).perfil.getProfile().setPrice(etPrecio.getText().toString());

        if (place != null && !place.equals(""))
            ((Activity_Principal_Fragment) getActivity()).perfil.getProfile().setPlace(place);

        if (latitud != null && !latitud.equals(""))
            ((Activity_Principal_Fragment) getActivity()).perfil.getProfile().setLatitud(latitud);

        if (longitud != null && !longitud.equals(""))
            ((Activity_Principal_Fragment) getActivity()).perfil.getProfile().setLongitud(longitud);

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

    @Override
    public void onLocationChanged(Location location) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5000);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PermissionChecker.PERMISSION_GRANTED)
            return;
        mLocation = location;

        if (mLocation != null) {
            place = obtenerNombreCiudad(mLocation.getLatitude(), mLocation.getLongitude());
            latitud = String.valueOf(mLocation.getLatitude());
            longitud = String.valueOf(mLocation.getLongitude());
            progressBarLocalizacion.setVisibility(View.INVISIBLE);
            ivUbicacion.setVisibility(View.VISIBLE);
            etSugerencias.setText(obtenerNombreCiudad(mLocation.getLatitude(), mLocation.getLongitude()));
            rvSugerencias.setVisibility(View.INVISIBLE);
        }

        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, Fragment_EditarPerfil.this);

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
                    place = placeAutocomplete.description.toString();
                    latitud = String.valueOf(places.get(0).getLatLng().latitude);
                    longitud = String.valueOf(places.get(0).getLatLng().longitude);

                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        etSugerencias.setText(placeAutocomplete.description);
        rvSugerencias.setVisibility(View.INVISIBLE);
    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
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

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                //Mostarmos una alerta al usuario sin bloquear el hilo
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 22);
            }
        }
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == getActivity().RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(getActivity(), data);

            // For API >= 23 we need to check specifically that we have permissions to read external storage.
            if (CropImage.isReadExternalStoragePermissionsRequired(getActivity(), imageUri)) {
                // request permissions and handle the result in onRequestPermissionsResult()
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
            } else {
                // no permissions required or already grunted, can start crop image activity
                startCropImageActivity(imageUri);
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == getActivity().RESULT_OK) {
                Uri resultUri = result.getUri();

                switch (ImagenID) {
                    case 1:
                        ivEditar1.setPadding(0, 0, 0, 0);

                        file1 = resultUri.getPath();

                        Glide.with(getActivity())
                                .load(file1)// Uri of the picture
                                .centerCrop()
                                .into(ivEditar1);
                        break;
                    case 2:
                        ivEditar2.setPadding(0, 0, 0, 0);

                        file2 = resultUri.getPath();

                        Glide.with(getActivity())
                                .load(file2)// Uri of the picture
                                .centerCrop()
                                .into(ivEditar2);
                        break;
                    case 3:
                        ivEditar3.setPadding(0, 0, 0, 0);

                        file3 = resultUri.getPath();

                        Glide.with(getActivity())
                                .load(file3)// Uri of the picture
                                .centerCrop()
                                .into(ivEditar3);
                        break;
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

        if (requestCode == 0x1) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    if (checkLocationPermission()) {
                        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, Fragment_EditarPerfil.this);
                    }
                    break;
                case Activity.RESULT_CANCELED:
                    settingActiveRequest();
                    break;
            }
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 22: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                    }
                }
            }

        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                //TODO:
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                //(just doing it here for now, note that with this code, no explanation is shown)
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        22);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        22);
            }
            return false;
        } else {
            return true;
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
                            place = obtenerNombreCiudad(mLocation.getLatitude(), mLocation.getLongitude());
                            latitud = String.valueOf(mLocation.getLatitude());
                            longitud = String.valueOf(mLocation.getLongitude());
                            progressBarLocalizacion.setVisibility(View.INVISIBLE);
                            ivUbicacion.setVisibility(View.VISIBLE);
                            etSugerencias.setText(obtenerNombreCiudad(mLocation.getLatitude(), mLocation.getLongitude()));
                            rvSugerencias.setVisibility(View.INVISIBLE);
                        } else{
                            mGoogleApiClient.connect();}

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(getActivity(), 0x1);
                        } catch (IntentSender.SendIntentException e) {
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;

                }

            }
        });

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
}
