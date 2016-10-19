package com.gloobe.just4roomies.Adaptadores;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gloobe.just4roomies.Interfaces.Interface_ChatImagen;
import com.gloobe.just4roomies.Modelos.Model_Chat_Conversacion_Mensaje;
import com.gloobe.just4roomies.R;

import java.util.List;


/**
 * Created by rudielavilaperaza on 29/09/16.
 */
public class Adapter_Chat extends RecyclerView.Adapter<Adapter_Chat.ViewHolder> {

    private Context context;
    private List<Model_Chat_Conversacion_Mensaje> listMensajes;
    private Typeface typeface;
    private int user_id;
    Interface_ChatImagen listener;

    private int SELF = 222;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvMensaje;
        private ImageView ivImagen;

        public ViewHolder(View itemView) {
            super(itemView);

            tvMensaje = (TextView) itemView.findViewById(R.id.tvMessage);
            ivImagen = (ImageView) itemView.findViewById(R.id.ivImage);
        }
    }

    public Adapter_Chat(Context context, List<Model_Chat_Conversacion_Mensaje> listMensajes, Typeface typeface, int user_id, Interface_ChatImagen listener) {
        this.context = context;
        this.typeface = typeface;
        this.listMensajes = listMensajes;
        this.user_id = user_id;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {

        Model_Chat_Conversacion_Mensaje mensaje = listMensajes.get(position);

        if (mensaje.getUser_id_send().equals(String.valueOf(user_id))) {
            return SELF;
        }
        return position;

    }

    @Override
    public Adapter_Chat.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflamos la vista de acuerdo al usuario
        View view;
        if (viewType == SELF) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_chat_mansaje_sender, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.layout_chat_mensaje_receiver, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter_Chat.ViewHolder holder, final int position) {
        //setemos los datos
        if (listMensajes.get(position).getType().equals("mensaje")) {
            //mensaje de texto
            holder.tvMensaje.setVisibility(View.VISIBLE);
            holder.ivImagen.setVisibility(View.GONE);
            holder.tvMensaje.setText(listMensajes.get(position).getMessage());
            holder.tvMensaje.setTypeface(typeface);
        } else {
            //es una imagen
            holder.tvMensaje.setVisibility(View.GONE);
            holder.ivImagen.setVisibility(View.VISIBLE);
            Glide.with(context).load(listMensajes.get(position).getMessage()).centerCrop().into(holder.ivImagen);

            holder.ivImagen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.clicImagen(v, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return listMensajes.size();
    }
}
