package com.just4roomies.j4r.Fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.just4roomies.j4r.Actividades.Activity_Principal_Fragment;
import com.just4roomies.j4r.Interfaces.Just4Interface;
import com.just4roomies.j4r.Modelos.Model_Chat_Response;
import com.just4roomies.j4r.R;
import com.just4roomies.j4r.Utils.Utilerias;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rudielavilaperaza on 17/08/16.
 */
public class Fragment_Chat extends Fragment {

    private Button btSolicitudes, btChats;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_chat, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btChats = (Button) getActivity().findViewById(R.id.btChats);
        btSolicitudes = (Button) getActivity().findViewById(R.id.btSolicitudes);

        progressDialog = new ProgressDialog(getActivity(), R.style.MyTheme);
        progressDialog.setCancelable(false);

        progressDialog.show();

        btChats.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        btSolicitudes.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);

        init();

        btChats.setEnabled(false);
        btChats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFrgament(new Fragment_Chat_Data());

                btSolicitudes.setBackground(getResources().getDrawable(R.drawable.boton_fecha));
                btSolicitudes.setTextColor(getResources().getColor(R.color.naranja_degradado));

                btChats.setBackground(getResources().getDrawable(R.drawable.boto_naranja_chat));
                btChats.setTextColor(Color.WHITE);

            }
        });

        btSolicitudes.setEnabled(false);
        btSolicitudes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFrgament(new Fragment_Chat_Solicitudes());

                btSolicitudes.setBackground(getResources().getDrawable(R.drawable.boto_naranja_chat));
                btSolicitudes.setTextColor(Color.WHITE);

                btChats.setBackground(getResources().getDrawable(R.drawable.boton_fecha));
                btChats.setTextColor(getResources().getColor(R.color.naranja_degradado));
            }
        });


    }

    private void setFrgament(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContenedorChat, fragment).commit();
    }

    private void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utilerias.URL_GLOBAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Just4Interface service = retrofit.create(Just4Interface.class);

        Call<Model_Chat_Response> chats = service.getChats(((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getId());

        chats.enqueue(new Callback<Model_Chat_Response>() {
            @Override
            public void onResponse(Call<Model_Chat_Response> call, Response<Model_Chat_Response> response) {
                if (response.body().getCode() == 200) {

                    ((Activity_Principal_Fragment) getActivity()).arrChats = response.body().getList();
                    ((Activity_Principal_Fragment) getActivity()).arrSolicitudes = response.body().getListRequest();

                    btChats.setEnabled(true);
                    btSolicitudes.setEnabled(true);
                    btChats.performClick();

                    progressDialog.dismiss();

                } else {

                }
            }

            @Override
            public void onFailure(Call<Model_Chat_Response> call, Throwable t) {

            }
        });

    }


}
