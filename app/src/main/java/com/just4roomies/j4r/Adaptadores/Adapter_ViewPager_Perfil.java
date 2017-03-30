package com.just4roomies.j4r.Adaptadores;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.just4roomies.j4r.Modelos.Roomie;
import com.just4roomies.j4r.R;

import java.util.ArrayList;

/**
 * Created by rudielavilaperaza on 24/08/16.
 */
public class Adapter_ViewPager_Perfil extends PagerAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Roomie> roomies;

    public Adapter_ViewPager_Perfil(Context context, ArrayList<Roomie> roomies) {
        this.context = context;
        this.roomies = roomies;
    }

    @Override
    public int getCount() {
        return roomies.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.layout_item_perfiles, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.ivImagen);

        TextView tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
        TextView tvDistancia = (TextView) itemView.findViewById(R.id.tvDistancia);
        TextView tvPrecio = (TextView) itemView.findViewById(R.id.tvPrecio);
        TextView tvSobremi = (TextView) itemView.findViewById(R.id.tvSobremi);
        TextView tvConexion = (TextView) itemView.findViewById(R.id.tvConexion);

        ImageView ivMascota = (ImageView) itemView.findViewById(R.id.ivMascota);
        ImageView ivFumas = (ImageView) itemView.findViewById(R.id.ivFumas);
        ImageView ivEstudias = (ImageView) itemView.findViewById(R.id.ivEstudias);
        ImageView ivNacionalidad = (ImageView) itemView.findViewById(R.id.ivNacionalidad);
        ImageView ivActivo = (ImageView) itemView.findViewById(R.id.ivActivo);
        ImageView ivCocinas = (ImageView) itemView.findViewById(R.id.ivCocinas);
        ImageView ivFiestero = (ImageView) itemView.findViewById(R.id.ivFiestero);


        Glide.with(context).load(roomies.get(position).getImagen()).centerCrop().into(imageView);
        //tvNombre.setText(roomies.get(position).getNombre() + "," + roomies.get(position).getEdad());
        tvNombre.setText(roomies.get(position).getNombre());
        tvDistancia.setText(roomies.get(position).getDistancia());
        tvPrecio.setText(roomies.get(position).getPrecio());
        tvSobremi.setText(roomies.get(position).getSobremi());
        tvConexion.setText("conexion");

        if (roomies.get(position).getFumas())
            Glide.with(context).load(R.drawable.fumas_true_inicial).into(ivFumas);
        else
            Glide.with(context).load(R.drawable.fumas_false_inicial).into(ivFumas);

        if (roomies.get(position).getEstudias())
            Glide.with(context).load(R.drawable.estudia_true_inicial).into(ivEstudias);
        else
            Glide.with(context).load(R.drawable.estudia_false_inicial).into(ivEstudias);

        if (roomies.get(position).getMascota())
            Glide.with(context).load(R.drawable.mascota_true_inicial).into(ivMascota);
        else
            Glide.with(context).load(R.drawable.mascota_false_inicial).into(ivMascota);

        if (roomies.get(position).getActivo())
            Glide.with(context).load(R.drawable.activo_true_inicial).into(ivActivo);
        else
            Glide.with(context).load(R.drawable.activo_false_inicial).into(ivActivo);

        if (roomies.get(position).getCocina())
            Glide.with(context).load(R.drawable.cocina_true_inicial).into(ivCocinas);
        else
            Glide.with(context).load(R.drawable.cocina_false_incial).into(ivCocinas);

        if (roomies.get(position).getFiestero())
            Glide.with(context).load(R.drawable.fiestero_true_incial).into(ivFiestero);
        else
            Glide.with(context).load(R.drawable.fiestero_false_inicial).into(ivFiestero);


        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
