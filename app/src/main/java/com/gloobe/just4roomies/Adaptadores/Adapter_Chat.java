package com.gloobe.just4roomies.Adaptadores;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    private int SELF = 222;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvMensaje;

        public ViewHolder(View itemView) {
            super(itemView);

            tvMensaje = (TextView) itemView.findViewById(R.id.tvMessage);
        }
    }

    public Adapter_Chat(Context context, List<Model_Chat_Conversacion_Mensaje> listMensajes, Typeface typeface, int user_id) {
        this.context = context;
        this.typeface = typeface;
        this.listMensajes = listMensajes;
        this.user_id = user_id;
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
    public void onBindViewHolder(Adapter_Chat.ViewHolder holder, int position) {
        //setamos los datos
        holder.tvMensaje.setText(listMensajes.get(position).getMessage());
        holder.tvMensaje.setTypeface(typeface);
    }

    @Override
    public int getItemCount() {
        return listMensajes.size();
    }
}
