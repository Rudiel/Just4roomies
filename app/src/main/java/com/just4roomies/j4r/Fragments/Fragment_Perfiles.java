package com.just4roomies.j4r.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.daprlabs.cardstack.SwipeDeck;
import com.just4roomies.j4r.Actividades.Activity_Principal_Fragment;
import com.just4roomies.j4r.Adaptadores.Adapter_GaleriaDialog;
import com.just4roomies.j4r.Adaptadores.Adapter_GaleriaPerfil;
import com.just4roomies.j4r.Adaptadores.Adapter_SwipeDeck;
import com.just4roomies.j4r.Interfaces.Interface_CardListener;
import com.just4roomies.j4r.Interfaces.Just4Interface;
import com.just4roomies.j4r.Modelos.Model_Like;
import com.just4roomies.j4r.Modelos.Model_Like_Response;
import com.just4roomies.j4r.Modelos.Model_Perfiles;
import com.just4roomies.j4r.Modelos.Model_Room;
import com.just4roomies.j4r.R;
import com.just4roomies.j4r.Utils.Utilerias;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rudielavilaperaza on 24/08/16.
 */
public class Fragment_Perfiles extends Fragment implements Interface_CardListener {

    private SwipeDeck cardStack;
    private ImageView ivAgregar, ivEliminar, ivGaleria;
    private LinearLayout llPerfilesacabados, llPerfilesOpciones;
    private Button btBuscardeNuevo;
    private Adapter_GaleriaDialog adapter_galeriaDialog;
    private Adapter_GaleriaPerfil adapter_galeriaPerfil;
    private ArrayList<String> rooms;
    private int FILTRO_SEXO = 3;
    private ProgressDialog progressDialog;
    private int positionRoomie = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_perfil, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cardStack = (SwipeDeck) getActivity().findViewById(R.id.swipe_deck);
        ivAgregar = (ImageView) getActivity().findViewById(R.id.ivAgregar);
        ivEliminar = (ImageView) getActivity().findViewById(R.id.ivEliminar);
        ivGaleria = (ImageView) getActivity().findViewById(R.id.ivGaleria);

        llPerfilesacabados = (LinearLayout) getActivity().findViewById(R.id.llRoomiesAcabados);
        btBuscardeNuevo = (Button) getActivity().findViewById(R.id.btPerfilBuscarDeNuevo);
        llPerfilesOpciones = (LinearLayout) getActivity().findViewById(R.id.llPerfilesOpciones);

        ((TextView) getActivity().findViewById(R.id.tvTeAcabasteRoomies)).setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        btBuscardeNuevo.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);

        ((Activity_Principal_Fragment) getActivity()).ivFiltros.setVisibility(View.VISIBLE);
        ((Activity_Principal_Fragment) getActivity()).ivFiltros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarFiltros();
            }
        });

        progressDialog = new ProgressDialog(getActivity(), R.style.MyTheme);
        progressDialog.setCancelable(false);

        //((Activity_Principal_Fragment) getActivity()).arrRomies.add(roomie);

        getProfiles();

        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                ((Activity_Principal_Fragment) getActivity()).arrRoomiesRechazados.add(((Activity_Principal_Fragment) getActivity()).arrRomies.get(position));
                positionRoomie = position + 1;
                haveGallery();

            }

            @Override
            public void cardSwipedRight(int position) {
                ((Activity_Principal_Fragment) getActivity()).arrRoomiesPreferidos.add(((Activity_Principal_Fragment) getActivity()).arrRomies.get(position));
                liketoPerfil(positionRoomie);
                positionRoomie = position + 1;
                haveGallery();

            }

            @Override
            public void cardsDepleted() {
                llPerfilesacabados.setVisibility(View.VISIBLE);
                llPerfilesOpciones.setVisibility(View.INVISIBLE);
            }

            @Override
            public void cardActionDown() {

            }

            @Override
            public void cardActionUp() {

            }

        });

        ivAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardStack.swipeTopCardRight(150);
            }
        });

        ivEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardStack.swipeTopCardLeft(150);

            }
        });


        btBuscardeNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (((Activity_Principal_Fragment) getActivity()).arrRomies.size() > 0)
                    getProfiles();
                else {
                    ((Activity_Principal_Fragment) getActivity()).iniciarFragment(new Fragment_EditarPerfil(), false);
                    ((Activity_Principal_Fragment) getActivity()).ivFiltros.setVisibility(View.INVISIBLE);
                }

            }
        });


    }

    private void mostrarFiltros() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.layout_dialog_filtro, null);
        builder.setView(layout);

        final AlertDialog show = builder.show();

        final Button btAplicar = (Button) layout.findViewById(R.id.btAplicar);
        final Button btNoAplicar = (Button) layout.findViewById(R.id.btNoAplicar);
        final TextView tvRoomies = (TextView) layout.findViewById(R.id.tvRommies);
        final TextView tvGenero = (TextView) layout.findViewById(R.id.tvGenero);

        btAplicar.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        btNoAplicar.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        tvRoomies.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
        tvGenero.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);


        btAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (FILTRO_SEXO) {
                    case 1:
                        show.dismiss();
                        getProfilesFilter("female");
                        break;
                    case 2:
                        show.dismiss();
                        getProfilesFilter("male");
                        break;
                    case 3:
                        show.dismiss();
                        getProfiles();
                        break;
                }
            }
        });

        btNoAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.dismiss();
                getProfiles();
                FILTRO_SEXO = 3;
            }
        });


        final ImageView ivMujer = (ImageView) layout.findViewById(R.id.ivFiltroMujer);
        final ImageView ivHombre = (ImageView) layout.findViewById(R.id.ivFiltroHombre);
        final ImageView ivAmbos = (ImageView) layout.findViewById(R.id.ivAmbos);

        ivMujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FILTRO_SEXO = 1;
                Glide.with(getActivity()).load(R.drawable.ic_mujer_active).into(ivMujer);
                Glide.with(getActivity()).load(R.drawable.ic_hombre_inactive).into(ivHombre);
                Glide.with(getActivity()).load(R.drawable.ic_ambos_inactive).into(ivAmbos);

            }
        });

        ivHombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FILTRO_SEXO = 2;
                Glide.with(getActivity()).load(R.drawable.ic_hombre_active).into(ivHombre);
                Glide.with(getActivity()).load(R.drawable.ic_mujer_inactive).into(ivMujer);
                Glide.with(getActivity()).load(R.drawable.ic_ambos_inactive).into(ivAmbos);
            }
        });

        ivAmbos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FILTRO_SEXO = 3;
                Glide.with(getActivity()).load(R.drawable.ic_ambos_active).into(ivAmbos);
                Glide.with(getActivity()).load(R.drawable.ic_hombre_inactive).into(ivHombre);
                Glide.with(getActivity()).load(R.drawable.ic_mujer_inactive).into(ivMujer);
            }
        });

        if (FILTRO_SEXO == 3)
            ivAmbos.performClick();
        else if (FILTRO_SEXO == 2)
            ivHombre.performClick();
        else
            ivMujer.performClick();

    }

    @Override
    public void clickCardListener(int position) {

        final Dialog dialogGaleria = new Dialog(getActivity());
        dialogGaleria.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialogGaleria.setContentView(R.layout.layout_dialog_galeria);

        Window window = dialogGaleria.getWindow();

        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        window.setBackgroundDrawableResource(R.color.naranja_degradado_transparente_dialog);

        dialogGaleria.getWindow().setLayout(ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.MATCH_PARENT);
        dialogGaleria.setCancelable(true);
        dialogGaleria.setCanceledOnTouchOutside(false);

        final ImageView ivCerrar = (ImageView) dialogGaleria.findViewById(R.id.btDialogCerrar);
        ivCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogGaleria.dismiss();
            }
        });

        final ImageView ivPuntos = (ImageView) dialogGaleria.findViewById(R.id.ivPuntos);

        final ViewPager viewPager = (ViewPager) dialogGaleria.findViewById(R.id.vpDialogCarrusel);

        final ArrayList<String> arrImagenes = new ArrayList<>();

        if (!((Activity_Principal_Fragment) getActivity()).arrRomies.get(position).getPhoto().equals(""))
            arrImagenes.add(((Activity_Principal_Fragment) getActivity()).arrRomies.get(position).getPhoto());
        if (!((Activity_Principal_Fragment) getActivity()).arrRomies.get(position).getPhoto2().equals(""))
            arrImagenes.add(((Activity_Principal_Fragment) getActivity()).arrRomies.get(position).getPhoto2());
        if (!((Activity_Principal_Fragment) getActivity()).arrRomies.get(position).getPhoto3().equals(""))
            arrImagenes.add(((Activity_Principal_Fragment) getActivity()).arrRomies.get(position).getPhoto3());

        TextView precio = (TextView) dialogGaleria.findViewById(R.id.tvGaleriaPrecio);
        TextView amueblado = (TextView) dialogGaleria.findViewById(R.id.tvGaleriaAmueblado);
        TextView disponible = (TextView) dialogGaleria.findViewById(R.id.tvGaleriaDisponible);
        TextView descripcion = (TextView) dialogGaleria.findViewById(R.id.tvDescripcion);

        precio.setVisibility(View.INVISIBLE);
        amueblado.setVisibility(View.INVISIBLE);
        disponible.setVisibility(View.INVISIBLE);

        descripcion.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);

        descripcion.setText(((Activity_Principal_Fragment) getActivity()).arrRomies.get(position).getProfile().getComments());

        if (arrImagenes.size() > 0) {
            adapter_galeriaPerfil = new Adapter_GaleriaPerfil(getActivity(), arrImagenes);

            viewPager.setAdapter(adapter_galeriaPerfil);

            if (arrImagenes.size() == 1)
                Glide.with(getActivity()).load(R.drawable.ic_puntos1).into(ivPuntos);
            else if (arrImagenes.size() == 2)
                Glide.with(getActivity()).load(R.drawable.ic_puntosdos1).into(ivPuntos);
            else
                Glide.with(getActivity()).load(R.drawable.puntos_uno).into(ivPuntos);

            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (arrImagenes.size() == 1) {
                        Glide.with(getActivity()).load(R.drawable.ic_puntos1).into(ivPuntos);
                    } else if (arrImagenes.size() == 2) {

                        switch (position) {
                            case 0:
                                Glide.with(getActivity()).load(R.drawable.ic_puntosdos1).into(ivPuntos);
                                break;
                            case 1:
                                Glide.with(getActivity()).load(R.drawable.ic_puntosdos2).into(ivPuntos);
                                break;
                        }
                    } else {
                        switch (position) {
                            case 0:
                                Glide.with(getActivity()).load(R.drawable.puntos_uno).into(ivPuntos);
                                break;
                            case 1:
                                Glide.with(getActivity()).load(R.drawable.puntos_dos).into(ivPuntos);
                                break;
                            case 2:
                                Glide.with(getActivity()).load(R.drawable.puntos_tres).into(ivPuntos);
                                break;
                        }
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });


            dialogGaleria.show();

        }
    }

    private void haveGallery() {
        if (positionRoomie < ((Activity_Principal_Fragment) getActivity()).arrRomies.size())
            if (((Activity_Principal_Fragment) getActivity()).arrRomies.get(positionRoomie).getRoom() != null) {

                final Model_Room room = ((Activity_Principal_Fragment) getActivity()).arrRomies.get(positionRoomie).getRoom();
                if (room.getId() != 0) {
                    if (!room.getFile1().equals("") || !room.getFile2().equals("") || !room.getFile3().equals("")) {
                        ivGaleria.setImageResource(R.drawable.ic_galeria_true);
                        ivGaleria.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                final Dialog dialogGaleria = new Dialog(getActivity());
                                dialogGaleria.requestWindowFeature(Window.FEATURE_NO_TITLE);

                                dialogGaleria.setContentView(R.layout.layout_dialog_galeria);

                                Window window = dialogGaleria.getWindow();

                                window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                                window.setBackgroundDrawableResource(R.color.naranja_degradado_transparente_dialog);

                                dialogGaleria.getWindow().setLayout(ViewPager.LayoutParams.MATCH_PARENT,
                                        ViewPager.LayoutParams.MATCH_PARENT);
                                dialogGaleria.setCancelable(true);
                                dialogGaleria.setCanceledOnTouchOutside(false);

                                final ImageView ivCerrar = (ImageView) dialogGaleria.findViewById(R.id.btDialogCerrar);
                                ivCerrar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialogGaleria.dismiss();
                                    }
                                });

                                TextView precio = (TextView) dialogGaleria.findViewById(R.id.tvGaleriaPrecio);
                                TextView amueblado = (TextView) dialogGaleria.findViewById(R.id.tvGaleriaAmueblado);
                                TextView disponible = (TextView) dialogGaleria.findViewById(R.id.tvGaleriaDisponible);
                                final ImageView ivPuntos = (ImageView) dialogGaleria.findViewById(R.id.ivPuntos);

                                amueblado.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
                                precio.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);
                                disponible.setTypeface(((Activity_Principal_Fragment) getActivity()).typeFace);


                                precio.setText("$" + room.getPrice());
                                if (room.getFurnished().equals("true"))
                                    amueblado.setText(getResources().getText(R.string.ofrecer_amueblado));
                                else
                                    amueblado.setText(getResources().getText(R.string.ofrecer_noamueblado));

                                disponible.setText(getResources().getString(R.string.buscar_disponible) + " " + room.getDate());

                                final ViewPager viewPager = (ViewPager) dialogGaleria.findViewById(R.id.vpDialogCarrusel);


                                rooms = new ArrayList<>();

                                if (!room.getFile1().equals(""))
                                    rooms.add(room.getFile1());

                                if (!room.getFile2().equals(""))
                                    rooms.add(room.getFile2());

                                if (!room.getFile3().equals(""))
                                    rooms.add(room.getFile3());


                                adapter_galeriaDialog = new Adapter_GaleriaDialog(getActivity(), rooms);

                                viewPager.setAdapter(adapter_galeriaDialog);

                                if (rooms.size() == 1)
                                    Glide.with(getActivity()).load(R.drawable.ic_puntos1).into(ivPuntos);
                                else if (rooms.size() == 2)
                                    Glide.with(getActivity()).load(R.drawable.ic_puntosdos1).into(ivPuntos);
                                else
                                    Glide.with(getActivity()).load(R.drawable.puntos_uno).into(ivPuntos);

                                viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                    @Override
                                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                    }

                                    @Override
                                    public void onPageSelected(int position) {
                                        if (rooms.size() == 1) {
                                            Glide.with(getActivity()).load(R.drawable.ic_puntos1).into(ivPuntos);
                                        } else if (rooms.size() == 2) {

                                            switch (position) {
                                                case 0:
                                                    Glide.with(getActivity()).load(R.drawable.ic_puntosdos1).into(ivPuntos);
                                                    break;
                                                case 1:
                                                    Glide.with(getActivity()).load(R.drawable.ic_puntosdos2).into(ivPuntos);
                                                    break;
                                            }
                                        } else {
                                            switch (position) {
                                                case 0:
                                                    Glide.with(getActivity()).load(R.drawable.puntos_uno).into(ivPuntos);
                                                    break;
                                                case 1:
                                                    Glide.with(getActivity()).load(R.drawable.puntos_dos).into(ivPuntos);
                                                    break;
                                                case 2:
                                                    Glide.with(getActivity()).load(R.drawable.puntos_tres).into(ivPuntos);
                                                    break;
                                            }
                                        }
                                    }

                                    @Override
                                    public void onPageScrollStateChanged(int state) {

                                    }
                                });

                                dialogGaleria.show();

                            }
                        });
                    } else {
                        ivGaleria.setImageResource(R.drawable.ic_galeria_false);
                        ivGaleria.setOnClickListener(null);
                    }

                } else {
                    ivGaleria.setImageResource(R.drawable.ic_galeria_false);
                    ivGaleria.setOnClickListener(null);
                }

            } else {
                ivGaleria.setImageResource(R.drawable.ic_galeria_false);
                ivGaleria.setOnClickListener(null);
            }
    }


    private void getProfiles() {

        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utilerias.URL_GLOBAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Just4Interface service = retrofit.create(Just4Interface.class);

        Log.d("PERFIL_ID", "" + ((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getId());

        Call<Model_Perfiles> perfiles = service.listadoPerfiles(((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getId());

        perfiles.enqueue(new Callback<Model_Perfiles>() {
            @Override
            public void onResponse(Call<Model_Perfiles> call, Response<Model_Perfiles> response) {
                if (response.body() != null) {
                    ((Activity_Principal_Fragment) getActivity()).arrRomies = response.body().getProfiles();
                    Adapter_SwipeDeck swipeDeck = new Adapter_SwipeDeck(((Activity_Principal_Fragment) getActivity()).arrRomies, getActivity(), ((Activity_Principal_Fragment) getActivity()).typeFace, Fragment_Perfiles.this);
                    cardStack.setAdapter(swipeDeck);

                    cardStack.setLeftImage(R.id.left_image);
                    cardStack.setRightImage(R.id.right_image);

                    positionRoomie = 0;
                    haveGallery();

                    if (((Activity_Principal_Fragment) getActivity()).arrRomies.size() > 0) {
                        llPerfilesacabados.setVisibility(View.INVISIBLE);
                        llPerfilesOpciones.setVisibility(View.VISIBLE);
                    } else {
                        //llPerfilesacabados.setVisibility(View.VISIBLE);
                        llPerfilesacabados.setVisibility(View.VISIBLE);
                        ((TextView) getActivity().findViewById(R.id.tvTeAcabasteRoomies)).setText(getString(R.string.buscarroomie_nohayperfiles));
                        llPerfilesOpciones.setVisibility(View.INVISIBLE);
                        btBuscardeNuevo.setText(getString(R.string.buscrarommie_buscarotrazona));
                    }
                    progressDialog.dismiss();

                } else {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Model_Perfiles> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void getProfilesFilter(String genero) {
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utilerias.URL_GLOBAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Just4Interface service = retrofit.create(Just4Interface.class);

        Call<Model_Perfiles> perfiles = service.listadoPerfilesFiltro(genero, ((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getId());

        perfiles.enqueue(new Callback<Model_Perfiles>() {
            @Override
            public void onResponse(Call<Model_Perfiles> call, Response<Model_Perfiles> response) {
                if (response.body() != null) {
                    ((Activity_Principal_Fragment) getActivity()).arrRomies = response.body().getProfiles();
                    Adapter_SwipeDeck swipeDeck = new Adapter_SwipeDeck(((Activity_Principal_Fragment) getActivity()).arrRomies, getActivity(), ((Activity_Principal_Fragment) getActivity()).typeFace, Fragment_Perfiles.this);
                    cardStack.setAdapter(swipeDeck);

                    cardStack.setLeftImage(R.id.left_image);
                    cardStack.setRightImage(R.id.right_image);

                    positionRoomie = 0;
                    haveGallery();

                    if (((Activity_Principal_Fragment) getActivity()).arrRomies.size() > 0) {
                        llPerfilesacabados.setVisibility(View.INVISIBLE);
                        llPerfilesOpciones.setVisibility(View.VISIBLE);
                    } else {
                        llPerfilesacabados.setVisibility(View.VISIBLE);
                        llPerfilesOpciones.setVisibility(View.INVISIBLE);
                    }
                    progressDialog.dismiss();

                } else {

                }
            }

            @Override
            public void onFailure(Call<Model_Perfiles> call, Throwable t) {

            }
        });
    }


    private void liketoPerfil(int position) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utilerias.URL_GLOBAL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Just4Interface service = retrofit.create(Just4Interface.class);

        Model_Like model_like = new Model_Like();
        model_like.setUser_id_send(((Activity_Principal_Fragment) getActivity()).perfil.getProfile().getId());
        model_like.setUser_id_receiver(((Activity_Principal_Fragment) getActivity()).arrRomies.get(position).getId());

        Call<Model_Like_Response> responseCall = service.perfilLike(model_like);

        responseCall.enqueue(new Callback<Model_Like_Response>() {
            @Override
            public void onResponse(Call<Model_Like_Response> call, Response<Model_Like_Response> response) {
                if (response.body() != null) {
                    Log.d("SOLICITUD", "ENVIADA");
                    //Se envio la solicitud
                    // Toast.makeText(getActivity(), "Solicitud enviada", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("SOLICITUD", "NO ENVIADA");

                    //No se pudo enviar la solicitud
                    //Toast.makeText(getActivity(), "Solicitud no enviada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model_Like_Response> call, Throwable t) {
                //Mensaje de fallo
                //Toast.makeText(getActivity(), "Solicitud no enviada", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
