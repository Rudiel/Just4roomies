package com.just4roomies.j4r.Adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.just4roomies.j4r.Interfaces.Interface_Solicitudes;
import com.just4roomies.j4r.Modelos.Model_Chat_Solicitudes;
import com.just4roomies.j4r.R;

import java.util.List;

/**
 * Created by rudielavilaperaza on 28/09/16.
 */
public class Adapter_Recycler_Solicitudes extends RecyclerView.Adapter<Adapter_Recycler_Solicitudes.ViewHolder> {
    private List<Model_Chat_Solicitudes> arrSolicitudes;
    private Interface_Solicitudes listener;
    private Context context;
    private Typeface typeface;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNombre;
        public TextView tvDistancia;
        public ImageView ivAceptado;
        public ImageView ivRechazado;
        public ImageView ivPerfil;

        public ViewHolder(View v) {
            super(v);
            //tvEncuesta = (TextView) v.findViewById(R.id.tvLista);
            tvNombre = (TextView) v.findViewById(R.id.tvNombre);
            ivPerfil = (ImageView) v.findViewById(R.id.ivPerfilChatItem);
            ivAceptado = (ImageView) v.findViewById(R.id.ivSolicitudAceptar);
            ivRechazado = (ImageView) v.findViewById(R.id.ivSolicitudRechazar);
        }
    }

    public Adapter_Recycler_Solicitudes(Context context, List<Model_Chat_Solicitudes> arrSolicitudes, Typeface typeface, Interface_Solicitudes listener) {
        this.arrSolicitudes = arrSolicitudes;
        this.listener = listener;
        this.context = context;
        this.typeface = typeface;

    }

    @Override
    public Adapter_Recycler_Solicitudes.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_chat_solicitud, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //listener.clicItem(view);
            }
        });
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final Adapter_Recycler_Solicitudes.ViewHolder holder, final int position) {
        holder.tvNombre.setText(arrSolicitudes.get(position).getData_user_send().getName().toUpperCase());
        holder.tvNombre.setTypeface(typeface);

        Glide.with(context).load(arrSolicitudes.get(position).getData_user_send().getUrlphoto()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.ivPerfil) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.ivPerfil.setImageDrawable(circularBitmapDrawable);
            }
        });

        holder.ivAceptado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.clicAceptar(view, position);
            }
        });

        holder.ivRechazado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.clicRechazar(view, position);
            }
        });

        holder.ivPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clicImagen(v, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrSolicitudes.size();
    }
}