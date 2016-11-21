package com.gloobe.just4roomies.Actividades;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.gloobe.just4roomies.Adaptadores.Adapter_Chat;
import com.gloobe.just4roomies.Interfaces.Interface_ChatImagen;
import com.gloobe.just4roomies.Interfaces.Just4Interface;
import com.gloobe.just4roomies.Modelos.Model_Chat_Conversacion;
import com.gloobe.just4roomies.Modelos.Model_Chat_Conversacion_Mensaje;
import com.gloobe.just4roomies.Modelos.Model_Chat_Imagen;
import com.gloobe.just4roomies.Modelos.Model_Chat_Mensaje;
import com.gloobe.just4roomies.Modelos.Model_Chat_Mensaje_Response;
import com.gloobe.just4roomies.R;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
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
    private ImageView ivChatfoto;
    private String file;

    public static Context ctx;


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
        ivChatfoto = (ImageView) findViewById(R.id.ivImagenChat);

        typeface = Typeface.createFromAsset(getAssets(), "fonts/MavenPro_Regular.ttf");

        ctx = Activity_Conversacion.this;

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
                dialogImage.getWindow().getAttributes().windowAnimations = R.style.animationdialog;

                final ImageView ivImagenPerfil = (ImageView) dialogImage.findViewById(R.id.ivProfilePicture);
                final TextView tvNombrePerfil = (TextView) dialogImage.findViewById(R.id.tvProfileName);
                final ProgressBar pbImagenPerfil = (ProgressBar) dialogImage.findViewById(R.id.pbImagenesChat);

                //Glide.with(Activity_Conversacion.this).load(bundle.get("CHAT_PHOTO")).centerCrop().into(ivImagenPerfil);
                pbImagenPerfil.setVisibility(View.VISIBLE);

                Glide.with(Activity_Conversacion.this).load(bundle.get("CHAT_PHOTO").toString()).centerCrop().listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        pbImagenPerfil.setVisibility(View.GONE);
                        return false;
                    }
                }).into(ivImagenPerfil);

                tvNombrePerfil.setTypeface(typeface);
                tvNombrePerfil.setText(bundle.getString("CHAT_NAME"));

                dialogImage.show();
            }
        });

        ivChatfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImageFromGallery();
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
                        adapter_chat = new Adapter_Chat(context, listMensajes, typeface, user_id, new Interface_ChatImagen() {
                            @Override
                            public void clicImagen(View view, int position) {
                                final Dialog dialogImage = new Dialog(ctx);
                                dialogImage.requestWindowFeature(Window.FEATURE_NO_TITLE);

                                dialogImage.setContentView(R.layout.layout_dialogo_conversacionimage);

                                Window window = dialogImage.getWindow();

                                window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                                dialogImage.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                dialogImage.getWindow().getAttributes().windowAnimations = R.style.animationdialog;


                                final ImageView ivImagenPerfil = (ImageView) dialogImage.findViewById(R.id.ivConversacionImagen);
                                final ProgressBar pbImagenChat = (ProgressBar) dialogImage.findViewById(R.id.pbImagenesConversacion);


                                Glide.with(context).load(listMensajes.get(position).getMessage()).listener(new RequestListener<String, GlideDrawable>() {
                                    @Override
                                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                        pbImagenChat.setVisibility(View.GONE);
                                        return false;
                                    }
                                }).into(ivImagenPerfil);
                                dialogImage.show();
                            }
                        });
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


    private void loadImageFromGallery() {
        //Creamos un intent para abrir aplicaciones como galeria, google photos etc
        /*Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, LOAD_IMAGE_CODE);*/
        Intent intent = CropImage.getPickImageChooserIntent(this);
        startActivityForResult(intent, CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                if (file != null && !file.equals(""))
                    sendImage();


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

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
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();

        return Base64.encodeToString(b, Base64.DEFAULT);

    }

    private void sendImage() {

        pbEnviar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.url_base))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Just4Interface service = retrofit.create(Just4Interface.class);

        Model_Chat_Imagen model_chat_imagen = new Model_Chat_Imagen();
        model_chat_imagen.setChat_id(chat_id);
        model_chat_imagen.setImage(convertImagetoBase64(file));
        model_chat_imagen.setUser_id_send(user_id);

        Call<Model_Chat_Mensaje_Response> imageCall = service.sendImage(model_chat_imagen);

        imageCall.enqueue(new Callback<Model_Chat_Mensaje_Response>() {
            @Override
            public void onResponse(Call<Model_Chat_Mensaje_Response> call, Response<Model_Chat_Mensaje_Response> response) {
                if (response.code() == 200) {
                    //recargo
                    getConversacion(chat_id, user_id, Activity_Conversacion.this);
                    etMenssage.setText("");
                    pbEnviar.setVisibility(View.INVISIBLE);
                    fabSendMessage.setEnabled(true);
                    pbEnviar.setVisibility(View.INVISIBLE);
                } else {
                    //muestro error
                    pbEnviar.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void onFailure(Call<Model_Chat_Mensaje_Response> call, Throwable t) {

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
