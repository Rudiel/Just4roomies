package com.gloobe.just4roomies.Adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.gloobe.just4roomies.Modelos.Room;
import com.gloobe.just4roomies.R;

import java.util.ArrayList;

/**
 * Created by rudielavilaperaza on 20/09/16.
 */
public class Adapter_GaleriaPerfil extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<String> arrImagenes;

    public Adapter_GaleriaPerfil(Context context, ArrayList<String> arrImagenes) {
        this.context = context;
        this.arrImagenes = arrImagenes;
    }

    @Override
    public int getCount() {
        return arrImagenes.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.layout_galeriarooms_item, container, false);

        final ProgressBar progressBar = (ProgressBar) itemView.findViewById(R.id.pbImagenGaleria);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.ivGaleria);

        progressBar.setVisibility(View.VISIBLE);

        Glide.with(context).load(arrImagenes.get(position)).centerCrop().listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(imageView);

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
