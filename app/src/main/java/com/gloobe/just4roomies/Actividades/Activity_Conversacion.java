package com.gloobe.just4roomies.Actividades;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.gloobe.just4roomies.Adaptadores.Adapter_Chat;
import com.gloobe.just4roomies.Interfaces.Just4Interface;
import com.gloobe.just4roomies.Modelos.Model_Chat_Conversacion;
import com.gloobe.just4roomies.Modelos.Model_Chat_Conversacion_Mensaje;
import com.gloobe.just4roomies.Modelos.Model_Chat_Mensaje;
import com.gloobe.just4roomies.Modelos.Model_Chat_Mensaje_Response;
import com.gloobe.just4roomies.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rudielavilaperaza on 27/09/16.
 */
public class Activity_Conversacion extends AppCompatActivity {

    public static FloatingActionButton fabSendMessage;
    public static SwipeRefreshLayout srlCargar;
    public static EditText etMenssage;
    public static boolean isActive = false;


    public static List<Model_Chat_Conversacion_Mensaje> listMensajes;
    public static Adapter_Chat adapter_chat;
    public static RecyclerView recyclerView;
    public static ImageView ivChatImagen;
    public static TextView tvChatNombre;
    public static Typeface typeface;

    public static int chat_id;
    public static int user_id;

    public static int pagina_actual;
    public static int pagina_ultima;

    public static ProgressBar pbEnviar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chat_conversacion);

        recyclerView = (RecyclerView) findViewById(R.id.rvChatConversaciones);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        fabSendMessage = (FloatingActionButton) findViewById(R.id.fabEnviar);
        etMenssage = (EditText) findViewById(R.id.etMensajeChat);
        ivChatImagen = (ImageView) findViewById(R.id.ivChatImagen);
        tvChatNombre = (TextView) findViewById(R.id.tvChatNombre);
        srlCargar = (SwipeRefreshLayout) findViewById(R.id.srlCargar);
        pbEnviar = (ProgressBar) findViewById(R.id.pbEnviar);

        typeface = Typeface.createFromAsset(getAssets(), "fonts/MavenPro_Regular.ttf");

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            chat_id = bundle.getInt("CHAT_ID");
            user_id = bundle.getInt("USER_ID");

            getConversacion(chat_id, user_id, Activity_Conversacion.this);

            tvChatNombre.setText(bundle.getString("CHAT_NAME"));
            tvChatNombre.setTypeface(typeface);

            Glide.with(Activity_Conversacion.this).load(bundle.get("CHAT_PHOTO")).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivChatImagen) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    ivChatImagen.setImageDrawable(circularBitmapDrawable);
                }
            });
        }

        etMenssage.setTypeface(typeface);

        srlCargar.setColorSchemeColors(getResources().getColor(R.color.amarillo_degradado));
        srlCargar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (pagina_ultima > pagina_actual)
                    cargarMensajes();
                else
                    srlCargar.setRefreshing(false);

            }
        });

        ivChatImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogImage = new Dialog(Activity_Conversacion.this);
                dialogImage.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialogImage.setContentView(R.layout.layout_dialogo_perfilpicture);

                dialogImage.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


                final ImageView ivImagenPerfil = (ImageView) dialogImage.findViewById(R.id.ivProfilePicture);
                final TextView tvNombrePerfil = (TextView) dialogImage.findViewById(R.id.tvProfileName);

                Glide.with(Activity_Conversacion.this).load(bundle.get("CHAT_PHOTO")).centerCrop().into(ivImagenPerfil);

                tvNombrePerfil.setTypeface(typeface);
                tvNombrePerfil.setText(bundle.getString("CHAT_NAME"));

                dialogImage.show();
            }
        });
    }

    public static void getConversacion(int chat_id, final int user_id, final Context context) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.url_base))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Just4Interface service = retrofit.create(Just4Interface.class);

        Call<Model_Chat_Conversacion> response = service.getMensajesChat(chat_id);

        response.enqueue(new Callback<Model_Chat_Conversacion>() {
            @Override
            public void onResponse(Call<Model_Chat_Conversacion> call, Response<Model_Chat_Conversacion> response) {
                if (response.body() != null) {
                    if (response.code() == 200) {
                        pagina_actual = response.body().getCurrent_page();
                        pagina_ultima = response.body().getLast_page();
                        listMensajes = response.body().getData();
                        adapter_chat = new Adapter_Chat(context, listMensajes, typeface, user_id);
                        recyclerView.setAdapter(adapter_chat);
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<Model_Chat_Conversacion> call, Throwable t) {

            }
        });

        fabSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etMenssage.getText().toString().trim().isEmpty())
                    sendMessage(etMenssage.getText().toString(), context);
            }
        });
    }

    public static void sendMessage(String mensaje, final Context context) {

        pbEnviar.setVisibility(View.VISIBLE);
        fabSendMessage.setEnabled(false);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.url_base))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Just4Interface service = retrofit.create(Just4Interface.class);

        Model_Chat_Mensaje model_chat_mensaje = new Model_Chat_Mensaje();
        model_chat_mensaje.setMessage(mensaje);
        model_chat_mensaje.setChat_id(chat_id);
        model_chat_mensaje.setUser_id_send(user_id);

        Call<Model_Chat_Mensaje_Response> send = service.enviarMensaje(model_chat_mensaje);

        send.enqueue(new Callback<Model_Chat_Mensaje_Response>() {
            @Override
            public void onResponse(Call<Model_Chat_Mensaje_Response> call, Response<Model_Chat_Mensaje_Response> response) {
                if (response.body() != null) {
                    if (response.body().getCode() == 200) {
                        getConversacion(chat_id, user_id, context);
                        etMenssage.setText("");
                        pbEnviar.setVisibility(View.INVISIBLE);
                        fabSendMessage.setEnabled(true);

                    } else {

                    }
                } else {

                }

            }

            @Override
            public void onFailure(Call<Model_Chat_Mensaje_Response> call, Throwable t) {

            }
        });
    }

    public static void cargarMensajes() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fremonweb.com/just4rommies/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Just4Interface service = retrofit.create(Just4Interface.class);

        Map<String, Integer> params = new HashMap<>();
        //params.put("page", pagina_actual + 1);
        params.put("page", pagina_actual + 1);

        Call<Model_Chat_Conversacion> response = service.getMensajesNext(chat_id, params);

        response.enqueue(new Callback<Model_Chat_Conversacion>() {
            @Override
            public void onResponse(Call<Model_Chat_Conversacion> call, Response<Model_Chat_Conversacion> response) {
                if (response.body() != null) {
                    if (response.code() == 200) {
                        pagina_actual = response.body().getCurrent_page();
                        pagina_ultima = response.body().getLast_page();
                        //listMensajes = response.body().getData();
                        listMensajes.addAll(response.body().getData());
                        adapter_chat.notifyDataSetChanged();
                    }

                }
                srlCargar.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<Model_Chat_Conversacion> call, Throwable t) {

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        isActive = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isActive = false;
    }
}
