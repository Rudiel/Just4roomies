package com.gloobe.just4roomies.Fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.gloobe.just4roomies.Actividades.Activity_Principal_Fragment;
import com.gloobe.just4roomies.Interfaces.Just4Interface;
import com.gloobe.just4roomies.Modelos.AddRoom;
import com.gloobe.just4roomies.Modelos.UpdateRoom;
import com.gloobe.just4roomies.R;
import com.gloobe.just4roomies.Utils.DatePickerFragment;
import com.gloobe.just4roomies.Utils.Utilerias;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by rudielavilaperaza on 27/08/16.
 */
public class Frgament_OfrecerHabitacion extends Fragment {

    private Button btFecha;
    private ImageView ivImagen1, ivImagen2, ivImagen3;
    private int imagenID = 0;
    private Button btGuardar;
    private ProgressDialog progressDialog;
    private String file1, file2, file3;
    private EditText etPrecio;
    private RadioGroup rg;
    private RadioButton rbAmueblado, rbNoAmueblado;
    private ProgressBar pbHabitacion1, pbHabitacion2, pbHabitacion3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_ofrecer_habitacion, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        etPrecio = (EditText) getActivity().findViewById(R.id.etOfrecer_Precio);
        etPrecio.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);


        btFecha = (Button) getActivity().findViewById(R.id.btOfrecer_Fecha);
        btFecha.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        btFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        btGuardar = (Button) getActivity().findViewById(R.id.btOfrecer_Guardar);
        btGuardar.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);

        rg = (RadioGroup) getActivity().findViewById(R.id.rgAmueblado);
        rbAmueblado = (RadioButton) getActivity().findViewById(R.id.rbAmueblado);
        rbNoAmueblado = (RadioButton) getActivity().findViewById(R.id.rbNoAmueblado);

        rbAmueblado.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        rbNoAmueblado.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);

        ivImagen1 = (ImageView) getActivity().findViewById(R.id.ivOfrecer1);
        ivImagen2 = (ImageView) getActivity().findViewById(R.id.ivOfrecer2);
        ivImagen3 = (ImageView) getActivity().findViewById(R.id.ivOfrecer3);

        pbHabitacion1 = (ProgressBar) getActivity().findViewById(R.id.pbHabitacion1);
        pbHabitacion2 = (ProgressBar) getActivity().findViewById(R.id.pbHabitacion2);
        pbHabitacion3 = (ProgressBar) getActivity().findViewById(R.id.pbHabitacion3);


        ((TextView) getActivity().findViewById(R.id.tvCuandoEstara)).setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);

        if (((Activity_Principal_Fragment) getActivity()).perfil.getRoom() != null) {

            if (!((Activity_Principal_Fragment) getActivity()).perfil.getRoom().getDate().equals(""))
                btFecha.setText(((Activity_Principal_Fragment) getActivity()).perfil.getRoom().getDate());

            if (!((Activity_Principal_Fragment) getActivity()).perfil.getRoom().getPrice().equals(""))
                etPrecio.setText(((Activity_Principal_Fragment) getActivity()).perfil.getRoom().getPrice());

            if (((Activity_Principal_Fragment) getActivity()).perfil.getRoom().getFurnished().equals("true")) {
                rbAmueblado.setChecked(true);
                rbNoAmueblado.setChecked(false);
            } else {
                rbNoAmueblado.setChecked(true);
                rbAmueblado.setChecked(false);
            }

            if (!((Activity_Principal_Fragment) getActivity()).perfil.getRoom().getFile1().equals("")) {
                ivImagen1.setPadding(0, 0, 0, 0);
                pbHabitacion1.setVisibility(View.VISIBLE);
                Glide.with(getActivity()).load(((Activity_Principal_Fragment) getActivity()).perfil.getRoom().getFile1()).centerCrop().listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        pbHabitacion1.setVisibility(View.GONE);
                        return false;
                    }
                }).into(ivImagen1);
            }

            if (!((Activity_Principal_Fragment) getActivity()).perfil.getRoom().getFile2().equals("")) {
                ivImagen2.setPadding(0, 0, 0, 0);
                pbHabitacion2.setVisibility(View.VISIBLE);
                Glide.with(getActivity()).load(((Activity_Principal_Fragment) getActivity()).perfil.getRoom().getFile2()).centerCrop().listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        pbHabitacion2.setVisibility(View.GONE);
                        return false;
                    }
                }).into(ivImagen2);
            }

            if (!((Activity_Principal_Fragment) getActivity()).perfil.getRoom().getFile3().equals("")) {
                ivImagen3.setPadding(0, 0, 0, 0);
                pbHabitacion3.setVisibility(View.VISIBLE);
                Glide.with(getActivity()).load(((Activity_Principal_Fragment) getActivity()).perfil.getRoom().getFile3()).centerCrop().listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        pbHabitacion3.setVisibility(View.GONE);
                        return false;
                    }
                }).into(ivImagen3);
            }

        }


        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarHabitacion();
            }
        });

        ivImagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenID = 1;
                loadImageFromGallery();
            }
        });

        ivImagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenID = 2;
                loadImageFromGallery();
            }
        });

        ivImagen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenID = 3;
                loadImageFromGallery();
            }
        });

        progressDialog = new ProgressDialog(getActivity(), R.style.MyTheme);
        progressDialog.setCancelable(false);


    }

    public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }


    private void loadImageFromGallery() {
        //Creamos un intent para abrir aplicaciones como galeria, google photos etc
        /*Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, LOAD_IMAGE_CODE);*/
        Intent intent = CropImage.getPickImageChooserIntent(getActivity());
        startActivityForResult(intent, CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE);

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

                switch (imagenID) {
                    case 1:
                        ivImagen1.setPadding(0, 0, 0, 0);

                        file1 = resultUri.getPath();

                        Glide.with(getActivity())
                                .load(new File(resultUri.getPath()))// Uri of the picture
                                .centerCrop()
                                .into(ivImagen1);
                        break;
                    case 2:
                        ivImagen2.setPadding(0, 0, 0, 0);

                        file2 = resultUri.getPath();

                        Glide.with(getActivity())
                                .load(new File(resultUri.getPath()))// Uri of the picture
                                .centerCrop()
                                .into(ivImagen2);
                        break;
                    case 3:
                        ivImagen3.setPadding(0, 0, 0, 0);

                        file3 = resultUri.getPath();

                        Glide.with(getActivity())
                                .load(new File(resultUri.getPath()))
                                .centerCrop()// Uri of the picture
                                .into(ivImagen3);
                        break;
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

        /*try {
            // Cuando la iamgen es seleccionada
            if (requestCode == LOAD_IMAGE_CODE && resultCode == getActivity().RESULT_OK
                    && null != data) {

                // Tomamos la imagen desde data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Tomamos el cursor
                Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);

                // Nos movemos a la primera fila
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                // Seteamos la imagen despues de codificar la url
                switch (imagenID) {
                    case 1:
                        ivImagen1.setPadding(0, 0, 0, 0);
                        Glide.with(getActivity()).load(imgDecodableString).centerCrop().into(ivImagen1);
                        break;
                    case 2:
                        ivImagen2.setPadding(0, 0, 0, 0);
                        Glide.with(getActivity()).load(imgDecodableString).centerCrop().into(ivImagen2);
                        break;
                    case 3:
                        ivImagen3.setPadding(0, 0, 0, 0);
                        Glide.with(getActivity()).load(imgDecodableString).centerCrop().into(ivImagen3);
                        break;
                }

                // ivIconoImagen.setVisibility(View.GONE);

            } else {
                Toast.makeText(getActivity(), "No has seleccionado una imagen",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Algo salió mal, intentalo de nuevo", Toast.LENGTH_LONG)
                    .show();
        }*/
    }


    private void startCropImageActivity(Uri imageUri) {
        Intent intent = CropImage.activity(imageUri)
                .setBorderLineColor(getResources().getColor(R.color.naranja_degradado))
                .setBackgroundColor(getResources().getColor(R.color.naranja_degradado_transparente))
                .setActivityTitle(getString(R.string.app_name))
                .getIntent(getActivity());
        startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    private void guardarHabitacion() {

        progressDialog.show();

        //Si el usuario tiene habitacion acyualiza, de lo contario crea
        if (((Activity_Principal_Fragment) getActivity()).perfil.getRoom() != null) {
            //Actualiza


            final UpdateRoom updateRoom = new UpdateRoom();
            updateRoom.setPrice(etPrecio.getText().toString());
            updateRoom.setDate(btFecha.getText().toString());
            updateRoom.setRoom_id(String.valueOf(((Activity_Principal_Fragment) getActivity()).perfil.getRoom().getId()));
            updateRoom.setUser_id(((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getId());

            if (rg.getCheckedRadioButtonId() == -1) {
                //nada seleccionado mostrar validacion
            } else {
                int selectedId = rg.getCheckedRadioButtonId();
                rbAmueblado = (RadioButton) getActivity().findViewById(selectedId);
                if (rbAmueblado.getText().toString().equals(getResources().getString(R.string.ofrecer_amueblado)))
                    updateRoom.setFurnished("true");
                else
                    updateRoom.setFurnished("false");
            }
            //updateRoom.setUser_id(Integer.parseInt(((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().getUser_id()));

            if (file1 != null)
                updateRoom.setImage1(convertImagetoBase64(file1));
            if (file2 != null)
                updateRoom.setImage2(convertImagetoBase64(file2));
            if (file3 != null)
                updateRoom.setImage3(convertImagetoBase64(file3));

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Utilerias.URL_GLOBAL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Just4Interface service = retrofit.create(Just4Interface.class);

            Call<ResponseBody> response = service.updateHabitacion(updateRoom);

            response.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.body() != null) {
                        ((Activity_Principal_Fragment) getActivity()).perfil.getRoom().setPrice(updateRoom.getPrice());
                        ((Activity_Principal_Fragment) getActivity()).perfil.getRoom().setDate(updateRoom.getDate());
                        if (updateRoom.getFurnished().equals("true"))
                            ((Activity_Principal_Fragment) getActivity()).perfil.getRoom().setFurnished("true");
                        else
                            ((Activity_Principal_Fragment) getActivity()).perfil.getRoom().setFurnished("false");

                        if (updateRoom.getImage1() != null)
                            ((Activity_Principal_Fragment) getActivity()).perfil.getRoom().setFile1(file1);
                        if (updateRoom.getImage2() != null)
                            ((Activity_Principal_Fragment) getActivity()).perfil.getRoom().setFile2(file2);
                        if (updateRoom.getImage3() != null)
                            ((Activity_Principal_Fragment) getActivity()).perfil.getRoom().setFile3(file3);


                        mostrarAlerta(getResources().getString(R.string.ofrecer_alerta_titulo), getResources().getString(R.string.ofrecer_alerta_mensaje_bien));
                    } else {
                        mostrarAlerta(getResources().getString(R.string.ofrecer_alerta_titulo), getResources().getString(R.string.ofrecer_alerta_mensaje_mal));
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    mostrarAlerta(getResources().getString(R.string.ofrecer_alerta_titulo), getResources().getString(R.string.ofrecer_alerta_mensaje_mal));
                    progressDialog.dismiss();

                }
            });

        } else {
            //Crea una nueva

            final AddRoom addRoom = new AddRoom();
            addRoom.setPrice(etPrecio.getText().toString());
            addRoom.setDate(btFecha.getText().toString());
            if (rg.getCheckedRadioButtonId() == -1) {
                //nada seleccionado mostrar validacion
            } else {
                int selectedId = rg.getCheckedRadioButtonId();
                rbAmueblado = (RadioButton) getActivity().findViewById(selectedId);
                if (rbAmueblado.getText().toString().equals("Amueblado"))
                    addRoom.setFurnished(true);
                else
                    addRoom.setFurnished(false);
            }
            addRoom.setUser_id(Integer.parseInt(((Activity_Principal_Fragment) getActivity()).perfil.getPersonality().getUser_id()));

            if (file1 != null)
                addRoom.setImage1(convertImagetoBase64(file1));
            if (file2 != null)
                addRoom.setImage2(convertImagetoBase64(file2));
            if (file3 != null)
                addRoom.setImage3(convertImagetoBase64(file3));

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Utilerias.URL_GLOBAL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Just4Interface service = retrofit.create(Just4Interface.class);

            Call<ResponseBody> response = service.createHabitacion(addRoom);

            response.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.body() != null) {

                        if (((Activity_Principal_Fragment) getActivity()).perfil.getRoom() != null) {
                            ((Activity_Principal_Fragment) getActivity()).perfil.getRoom().setPrice(addRoom.getPrice());
                            ((Activity_Principal_Fragment) getActivity()).perfil.getRoom().setDate(addRoom.getDate());
                            if (addRoom.getFurnished())
                                ((Activity_Principal_Fragment) getActivity()).perfil.getRoom().setFurnished("true");
                            else
                                ((Activity_Principal_Fragment) getActivity()).perfil.getRoom().setFurnished("false");

                            if (addRoom.getImage1() != null)
                                ((Activity_Principal_Fragment) getActivity()).perfil.getRoom().setFile1(file1);
                            if (addRoom.getImage2() != null)
                                ((Activity_Principal_Fragment) getActivity()).perfil.getRoom().setFile2(file2);
                            if (addRoom.getImage3() != null)
                                ((Activity_Principal_Fragment) getActivity()).perfil.getRoom().setFile3(file3);


                        } else {
                            AddRoom room = new AddRoom();
                        }
                        mostrarAlerta(getString(R.string.ofrecer_alerta_titulo), getString(R.string.ofrecer_alerta_mensaje_bien));
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    mostrarAlerta(getString(R.string.ofrecer_alerta_titulo), getString(R.string.ofrecer_alerta_mensaje_mal));
                    progressDialog.dismiss();

                }
            });
        }


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

    private String convertImagetoBase64(String imagePath) {

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inSampleSize = 2;
        options.inPurgeable = true;

        Bitmap bm = BitmapFactory.decodeFile(imagePath, options);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 75, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();

        return Base64.encodeToString(b, Base64.DEFAULT);

    }

}
