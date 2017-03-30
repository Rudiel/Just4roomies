package com.just4roomies.j4r.Fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.facebook.Profile;
import com.just4roomies.j4r.R;


/**
 * Created by rudielavilaperaza on 18/08/16.
 */
public class Fragment_BuscarRoomie extends Fragment {

    private Toolbar toolbar;
    private EditText etBusqueda;
    private Button btBuscar;
    private ImageView ivBuscoRoomieyHabitacion, ivBuscoHabitacion, ivOfrezcoHabitacion, ivImagenPerfil, ivFondo;
    private int BUSQUEDA_ESTADO = 0;
    private TextView tvSituacionActual, tvBienvenida;
    private Profile profile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_buscar_roomie, container, false);
        setupToolbar((Toolbar) v.findViewById(R.id.toolbar));
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ivBuscoRoomieyHabitacion = (ImageView) getActivity().findViewById(R.id.ivBuscoRoomieYHabitacion);
        ivBuscoHabitacion = (ImageView) getActivity().findViewById(R.id.ivBuscoHabitacion);
        ivOfrezcoHabitacion = (ImageView) getActivity().findViewById(R.id.ivOfrezcoHabitacion);

        btBuscar = (Button) getActivity().findViewById(R.id.btBuscarRoomieBuscar);
        etBusqueda = (EditText) getActivity().findViewById(R.id.etBuscarBusqueda);

        tvBienvenida = (TextView) getActivity().findViewById(R.id.tvBienvenida);
        tvSituacionActual = (TextView) getActivity().findViewById(R.id.tvBuscarSituacionActual);

        ivImagenPerfil = (ImageView) getActivity().findViewById(R.id.ivImagenPerfilBuscar);
        ivFondo = (ImageView) getActivity().findViewById(R.id.ivFondo_BuscarRoomie);

        Glide.with(getActivity()).load(R.drawable.bg_perfil).centerCrop().into(ivFondo);


        profile = Profile.getCurrentProfile();

        if (profile != null) {

            tvBienvenida.setText("¡HOLA " + profile.getName().toUpperCase() + "!");

            Glide.with(this).load(profile.getProfilePictureUri(400, 400)).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivImagenPerfil) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getActivity().getBaseContext().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    ivImagenPerfil.setImageDrawable(circularBitmapDrawable);
                }
            });


        }


        ivBuscoRoomieyHabitacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(getActivity()).load(R.drawable.habitacionyroomie_activo).into(ivBuscoRoomieyHabitacion);
                Glide.with(getActivity()).load(R.drawable.habitacion_inactivo).into(ivBuscoHabitacion);
                Glide.with(getActivity()).load(R.drawable.ofrezco_inactivo).into(ivOfrezcoHabitacion);

                tvSituacionActual.setText(getResources().getString(R.string.buscarroomie_buscoroomieyhabitacion));

                BUSQUEDA_ESTADO = 1;
            }
        });

        ivBuscoHabitacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(getActivity()).load(R.drawable.habitacionyroomie_inactivo).into(ivBuscoRoomieyHabitacion);
                Glide.with(getActivity()).load(R.drawable.habitacion_activo).into(ivBuscoHabitacion);
                Glide.with(getActivity()).load(R.drawable.ofrezco_inactivo).into(ivOfrezcoHabitacion);

                tvSituacionActual.setText(getResources().getString(R.string.buscarroomie_buscohabitacion));

                BUSQUEDA_ESTADO = 2;

            }
        });

        ivOfrezcoHabitacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(getActivity()).load(R.drawable.habitacionyroomie_inactivo).into(ivBuscoRoomieyHabitacion);
                Glide.with(getActivity()).load(R.drawable.habitacion_inactivo).into(ivBuscoHabitacion);
                Glide.with(getActivity()).load(R.drawable.ofrezco_activo).into(ivOfrezcoHabitacion);

                tvSituacionActual.setText(getResources().getString(R.string.buscarroomie_ofrezcohabitacion));

                BUSQUEDA_ESTADO = 3;

            }
        });

        ivBuscoRoomieyHabitacion.performClick();

        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (BUSQUEDA_ESTADO) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        });

    }


    private void setupToolbar(Toolbar toolbar) {
       // ((Activity_Principal) getActivity()).setSupportActionBar(toolbar);
    }
}
