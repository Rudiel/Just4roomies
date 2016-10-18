package com.gloobe.just4roomies.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.gloobe.just4roomies.Actividades.Activity_Conversacion;
import com.gloobe.just4roomies.Actividades.Activity_Principal_Fragment;
import com.gloobe.just4roomies.Adaptadores.Adapter_RecyclerView;
import com.gloobe.just4roomies.Interfaces.Interface_RecyclerView;
import com.gloobe.just4roomies.Interfaces.Just4Interface;
import com.gloobe.just4roomies.Modelos.Model_Chat_Response;
import com.gloobe.just4roomies.Modelos.Model_EliminarChat;
import com.gloobe.just4roomies.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rudielavilaperaza on 28/09/16.
 */
public class Fragment_Chat_Data extends Fragment implements Interface_RecyclerView {

    private RecyclerView recyclerView;
    private Adapter_RecyclerView adapter;
    private ProgressDialog progressDialog;
    private RelativeLayout rlChatsEmpty;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_chat_conversaciones, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.rvConversaciones);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlChatsEmpty = (RelativeLayout) getActivity().findViewById(R.id.rlChatsEmpty);

        adapter = new Adapter_RecyclerView(getActivity(), ((Activity_Principal_Fragment) getActivity()).arrChats, ((Activity_Principal_Fragment) getActivity()).typeFace, Fragment_Chat_Data.this);
        recyclerView.setAdapter(adapter);

        progressDialog = new ProgressDialog(getActivity(), R.style.MyTheme);
        progressDialog.setCancelable(false);

        ((TextView) getActivity().findViewById(R.id.tvSolicitudes)).setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);


        if (((Activity_Principal_Fragment) getActivity()).arrChats.size() > 0)
            rlChatsEmpty.setVisibility(View.GONE);
        else
            rlChatsEmpty.setVisibility(View.VISIBLE);

    }

    @Override
    public void clicItem(View view, int position) {

        if (((Activity_Principal_Fragment) getActivity()).arrChats.get(position).getStatus().equals("1")) {

            Intent intent = new Intent(getActivity(), Activity_Conversacion.class);
            Bundle bundle = new Bundle();
            bundle.putInt("CHAT_ID", ((Activity_Principal_Fragment) getActivity()).arrChats.get(position).getChat_id());
            bundle.putInt("USER_ID", ((Activity_Principal_Fragment) getActivity()).arrChats.get(position).getUser_id_send());
            bundle.putString("CHAT_PHOTO", ((Activity_Principal_Fragment) getActivity()).arrChats.get(position).getData_user().getUrlphoto());
            bundle.putString("CHAT_NAME", ((Activity_Principal_Fragment) getActivity()).arrChats.get(position).getData_user().getName());

            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    @Override
    public void clicItemDelete(View view, int position) {

        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.url_base))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Just4Interface service = retrofit.create(Just4Interface.class);

        Model_EliminarChat model_eliminarChat = new Model_EliminarChat();
        model_eliminarChat.setId(((Activity_Principal_Fragment) getActivity()).arrChats.get(position).getId());

        Call<ResponseBody> eliminar = service.borrarChat(model_eliminarChat);

        eliminar.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    recargarLista();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void clicItemImage(View view, int position) {
        final Dialog dialogImage = new Dialog(getActivity());
        dialogImage.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialogImage.setContentView(R.layout.layout_dialogo_perfilpicture);

        dialogImage.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        final ImageView ivImagenPerfil = (ImageView) dialogImage.findViewById(R.id.ivProfilePicture);
        final TextView tvNombrePerfil = (TextView) dialogImage.findViewById(R.id.tvProfileName);

        Glide.with(getActivity()).load(((Activity_Principal_Fragment) getActivity()).arrChats.get(position).getData_user().getUrlphoto()).centerCrop().listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                //pbHabitacion1.setVisibility(View.GONE);
                return false;
            }
        }).into(ivImagenPerfil);

        tvNombrePerfil.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        tvNombrePerfil.setText(((Activity_Principal_Fragment) getActivity()).arrChats.get(position).getData_user().getName());

        dialogImage.show();

    }


    private void recargarLista() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.url_base))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Just4Interface service = retrofit.create(Just4Interface.class);

        Call<Model_Chat_Response> chats = service.getChats(((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getId());

        chats.enqueue(new Callback<Model_Chat_Response>() {
            @Override
            public void onResponse(Call<Model_Chat_Response> call, Response<Model_Chat_Response> response) {
                if (response.body() != null) {
                    if (response.code() == 200) {
                        ((Activity_Principal_Fragment) getActivity()).arrChats = response.body().getList();
                        ((Activity_Principal_Fragment) getActivity()).arrSolicitudes = response.body().getListRequest();
                        adapter = new Adapter_RecyclerView(getActivity(), ((Activity_Principal_Fragment) getActivity()).arrChats, ((Activity_Principal_Fragment) getActivity()).typeFace, Fragment_Chat_Data.this);
                        recyclerView.setAdapter(adapter);
                        progressDialog.dismiss();

                        if (((Activity_Principal_Fragment) getActivity()).arrChats.size() > 0)
                            rlChatsEmpty.setVisibility(View.GONE);
                        else
                            rlChatsEmpty.setVisibility(View.VISIBLE);
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<Model_Chat_Response> call, Throwable t) {

            }
        });

    }
}
