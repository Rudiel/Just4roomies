package com.gloobe.just4roomies.Adaptadores;

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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.gloobe.just4roomies.Interfaces.Interface_RecyclerView;
import com.gloobe.just4roomies.Modelos.Model_Chat;
import com.gloobe.just4roomies.R;

import java.util.ArrayList;

/**
 * Created by rudielavilaperaza on 08/09/16.
 */
public class Adapter_RecyclerView extends RecyclerSwipeAdapter<Adapter_RecyclerView.SimpleViewHolder> {

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {

        SwipeLayout swipeLayout;
        TextView tvNombre,tvHora;
        LinearLayout buttonDelete;
        ImageView ivImagenPerfil;
        RelativeLayout rlContenedor;
        ImageView ivEnespera;


        public SimpleViewHolder(View itemView) {
            super(itemView);

            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
            buttonDelete = (LinearLayout) itemView.findViewById(R.id.llEliminar);
            ivImagenPerfil = (ImageView) itemView.findViewById(R.id.ivPerfilChatItem);
            rlContenedor = (RelativeLayout) itemView.findViewById(R.id.rlContenedorChat);
            ivEnespera = (ImageView) itemView.findViewById(R.id.ivEnespera);
            tvHora=(TextView) itemView.findViewById(R.id.tvHora);
        }
    }

    private Interface_RecyclerView listener;

    private Context mContext;
    private ArrayList<Model_Chat> mDataset;
    private Typeface typeface;

    public Adapter_RecyclerView(Context context, ArrayList<Model_Chat> arrayList, Typeface typeface, Interface_RecyclerView listener) {
        this.mContext = context;
        this.mDataset = arrayList;
        this.typeface = typeface;
        this.listener = listener;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_chat_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {

        viewHolder.tvNombre.setText(mDataset.get(position).getData_user().getName().toUpperCase());
        viewHolder.tvNombre.setTypeface(typeface);

        Glide.with(mContext).load(mDataset.get(position).getData_user().getUrlphoto()).asBitmap().centerCrop().into(new BitmapImageViewTarget(viewHolder.ivImagenPerfil) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                viewHolder.ivImagenPerfil.setImageDrawable(circularBitmapDrawable);
            }
        });

        if (mDataset.get(position).getStatus().equals("1"))
            viewHolder.ivEnespera.setVisibility(View.GONE);
        else
            viewHolder.ivEnespera.setVisibility(View.VISIBLE);

        viewHolder.tvHora.setText(mDataset.get(position).getDate().substring(11,16));
        viewHolder.tvHora.setTypeface(typeface);

        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        viewHolder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {
            }

            @Override
            public void onOpen(SwipeLayout layout) {

            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });

        viewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.clicItemDelete(view, position);
            }
        });

        viewHolder.ivImagenPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clicItemImage(v, position);
            }
        });

        viewHolder.rlContenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.clicItem(view, position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return 0;
    }


}
