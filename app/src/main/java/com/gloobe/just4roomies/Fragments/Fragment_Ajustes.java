package com.gloobe.just4roomies.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gloobe.just4roomies.Actividades.Activity_Principal_Fragment;
import com.gloobe.just4roomies.R;

/**
 * Created by rudielavilaperaza on 29/08/16.
 */
public class Fragment_Ajustes extends Fragment {

    private ImageView ivFondo;
    private TextView tvPolitica, tvTerminos;
    private Button btGuardar;
    private Switch swLocalizacion, swNotificaciones;
    private RelativeLayout rlEliminarCuenta;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_ajustes, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ivFondo = (ImageView) getActivity().findViewById(R.id.ivFondoAjustes);
        tvPolitica = (TextView) getActivity().findViewById(R.id.tvPoliticas);
        tvTerminos = (TextView) getActivity().findViewById(R.id.tvPrivacidad);
        btGuardar = (Button) getActivity().findViewById(R.id.btAjustesGuardar);
        swNotificaciones = (Switch) getActivity().findViewById(R.id.swAjustesNotificacion);
        swLocalizacion = (Switch) getActivity().findViewById(R.id.swAjustesLocalizacion);
        rlEliminarCuenta = (RelativeLayout) getActivity().findViewById(R.id.rlEliminarCuenta);

        Glide.with(getActivity()).load(R.drawable.bg_perfil).centerCrop().into(ivFondo);

        ((TextView) getActivity().findViewById(R.id.tvAjustestitulo)).setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        ((TextView) getActivity().findViewById(R.id.tvAjustesLocalizacion)).setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        ((TextView) getActivity().findViewById(R.id.tvAjustesNotificacion)).setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        ((TextView) getActivity().findViewById(R.id.tvEliminarCuenta)).setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);

        tvTerminos.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        tvPolitica.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        btGuardar.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarAjustes();
            }
        });

        swLocalizacion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean cheked) {

                if (cheked) {

                } else {

                }

            }
        });

        swNotificaciones.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean cheked) {
                if (cheked) {

                } else {

                }
            }
        });

        rlEliminarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarCuenta();
            }
        });
    }

    private void guardarAjustes() {

    }

    private void eliminarCuenta() {

    }


}
