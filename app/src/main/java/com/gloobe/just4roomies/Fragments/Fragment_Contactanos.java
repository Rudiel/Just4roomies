package com.gloobe.just4roomies.Fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gloobe.just4roomies.Actividades.Activity_Principal_Fragment;
import com.gloobe.just4roomies.Interfaces.Just4Interface;
import com.gloobe.just4roomies.Modelos.Model_Contact;
import com.gloobe.just4roomies.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rudielavilaperaza on 01/09/16.
 */
public class Fragment_Contactanos extends Fragment {

    private EditText etMensaje, etAsunto, etNombre;
    private Button btEnviar;
    private ImageView ivFondoContacto;
    private ProgressDialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_contacto, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ivFondoContacto = (ImageView) getActivity().findViewById(R.id.ivContactoFondo);
        etMensaje = (EditText) getActivity().findViewById(R.id.etContactoMensaje);
        etAsunto = (EditText) getActivity().findViewById(R.id.etContactoAsunto);
        etNombre = (EditText) getActivity().findViewById(R.id.etContactoNombre);
        btEnviar = (Button) getActivity().findViewById(R.id.btContactoEnviar);

        etNombre.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        etAsunto.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        etMensaje.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        btEnviar.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);


        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                enviarMensage();
            }
        });

        Glide.with(getActivity()).load(R.drawable.bg_perfil).centerCrop().into(ivFondoContacto);

        progressDialog = new ProgressDialog(getActivity(), R.style.MyTheme);
        progressDialog.setCancelable(false);

    }

    private void enviarMensage() {

        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.url_base))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Just4Interface service = retrofit.create(Just4Interface.class);

        Model_Contact contact = new Model_Contact();
        contact.setSubject(etAsunto.getText().toString());
        contact.setFullname(etNombre.getText().toString());
        contact.setBodyMessage(etMensaje.getText().toString());

        Call<ResponseBody> response = service.contacto(contact);

        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    if (response.code() == 200) {
                        mostrarAlerta(getResources().getString(R.string.contacto_dialog_titulo), getResources().getString(R.string.contacto_dialog_mensaje_bien));
                        etAsunto.setText("");
                        etMensaje.setText("");
                        etNombre.setText("");
                    }
                    progressDialog.dismiss();

                } else {
                    progressDialog.dismiss();
                    mostrarAlerta(getResources().getString(R.string.contacto_dialog_titulo), getResources().getString(R.string.contacto_dialog_mensaje_mal));
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                mostrarAlerta(getString(R.string.contacto_dialog_titulo), getString(R.string.contacto_dialog_mensaje_mal));
            }
        });

    }

    private void validarCampos() {

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
}
