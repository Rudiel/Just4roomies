package com.just4roomies.j4r.Adaptadores;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.just4roomies.j4r.Interfaces.Interface_CardListener;
import com.just4roomies.j4r.Modelos.Model_Profiles;
import com.just4roomies.j4r.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudielavilaperaza on 26/08/16.
 */
public class


Adapter_SwipeDeck extends BaseAdapter {

    private List<Model_Profiles> listRoomies;
    private Context context;
    private Typeface typeface;
    private Interface_CardListener cardListener;

    public Adapter_SwipeDeck(List<Model_Profiles> listRoomies, Context context, Typeface typeface, Interface_CardListener cardListener) {
        this.cardListener = cardListener;
        this.listRoomies = listRoomies;
        this.context = context;
        this.typeface = typeface;
    }

    @Override
    public int getCount() {
        return listRoomies.size();
    }

    @Override
    public Object getItem(int position) {
        return listRoomies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // normally use a viewholder
            v = inflater.inflate(R.layout.layout_item_perfiles_card, parent, false);
        }

        List<Integer> imagenes = new ArrayList<>();

        TextView tvNombre = (TextView) v.findViewById(R.id.tvNombre);
        tvNombre.setText(listRoomies.get(position).getName() + "," + listRoomies.get(position).getAge());
        //tvNombre.setText(listRoomies.get(position).getName());
        tvNombre.setTypeface(typeface);

        TextView tvConexion = (TextView) v.findViewById(R.id.tvConexion);
        // tvConexion.setText(listRoomies.get(position).get());
        //tvConexion.setTypeface(typeface);

        TextView tvPrecio = (TextView) v.findViewById(R.id.tvPrecio);
        tvPrecio.setText("$" + listRoomies.get(position).getPrice());
        tvPrecio.setTypeface(typeface);

        TextView tvDistancia = (TextView) v.findViewById(R.id.tvDistancia);
        //tvDistancia.setText(listRoomies.get(position).getDistancia());
        //tvDistancia.setTypeface(typeface);

        TextView tvSobreMi = (TextView) v.findViewById(R.id.tvSobremi);
        tvSobreMi.setText(listRoomies.get(position).getProfile().getComments());
        tvSobreMi.setTypeface(typeface);

        ((TextView) v.findViewById(R.id.tvSobreMiTitulo)).setTypeface(typeface);

        ImageView ivFoto = (ImageView) v.findViewById(R.id.ivImagen);

        ImageView ivMascota = (ImageView) v.findViewById(R.id.ivMascota);
        ImageView ivFumas = (ImageView) v.findViewById(R.id.ivFumas);
        ImageView ivEstudias = (ImageView) v.findViewById(R.id.ivEstudias);
        ImageView ivNacionalidad = (ImageView) v.findViewById(R.id.ivNacionalidad);
        ImageView ivActivo = (ImageView) v.findViewById(R.id.ivActivo);
        ImageView ivCocinas = (ImageView) v.findViewById(R.id.ivCocinas);
        ImageView ivFiestero = (ImageView) v.findViewById(R.id.ivFiestero);
        ImageView ivPuntos = (ImageView) v.findViewById(R.id.ivPuntosPerfilCard);

        final ProgressBar pbPerfil = (ProgressBar) v.findViewById(R.id.pbPerfil);

        if (!listRoomies.get(position).getPhoto().equals(""))
            pbPerfil.setVisibility(View.VISIBLE);

        Glide.with(context).load(listRoomies.get(position).getPhoto()).centerCrop().listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                pbPerfil.setVisibility(View.GONE);
                return false;
            }
        }).into(ivFoto);

        if (listRoomies.get(position).getProfile().getSmoke().equals("1"))
            Glide.with(context).load(R.drawable.fumas_true_inicial).into(ivFumas);
        else
            Glide.with(context).load(R.drawable.fumas_false_inicial).into(ivFumas);

        if (listRoomies.get(position).getProfile().getStudent().equals("1"))
            Glide.with(context).load(R.drawable.estudia_true_inicial).into(ivEstudias);
        else
            Glide.with(context).load(R.drawable.estudia_false_inicial).into(ivEstudias);

        if (listRoomies.get(position).getProfile().getPet().equals("1"))
            Glide.with(context).load(R.drawable.mascota_true_inicial).into(ivMascota);
        else
            Glide.with(context).load(R.drawable.mascota_false_inicial).into(ivMascota);

        if (listRoomies.get(position).getProfile().getActive().equals("1"))
            Glide.with(context).load(R.drawable.activo_true_inicial).into(ivActivo);
        else
            Glide.with(context).load(R.drawable.activo_false_inicial).into(ivActivo);

        if (listRoomies.get(position).getProfile().getCook().equals("1"))
            Glide.with(context).load(R.drawable.cocina_true_inicial).into(ivCocinas);
        else
            Glide.with(context).load(R.drawable.cocina_false_incial).into(ivCocinas);

        if (listRoomies.get(position).getProfile().getParty().equals("1"))
            Glide.with(context).load(R.drawable.fiestero_true_incial).into(ivFiestero);
        else
            Glide.with(context).load(R.drawable.fiestero_false_inicial).into(ivFiestero);

        if (listRoomies.get(position).getPhoto() != null && !listRoomies.get(position).getPhoto().equals(""))
            imagenes.add(1);
        if (listRoomies.get(position).getPhoto2() != null && !listRoomies.get(position).getPhoto2().equals(""))
            imagenes.add(2);
        if (listRoomies.get(position).getPhoto3() != null && !listRoomies.get(position).getPhoto3().equals(""))
            imagenes.add(3);

        if (imagenes.size() == 1) {
            Glide.with(context).load(R.drawable.ic_puntos1).into(ivPuntos);
        } else if (imagenes.size() == 2) {
            Glide.with(context).load(R.drawable.ic_puntosdos1).into(ivPuntos);
        } else if (imagenes.size() == 3) {
            Glide.with(context).load(R.drawable.puntos_uno).into(ivPuntos);
        }

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardListener.clickCardListener(position);
            }
        });

        return v;
    }
}
