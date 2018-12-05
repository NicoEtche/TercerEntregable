package com.digitahouse.tercerentregablenicolasetchemaite.view;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.digitahouse.tercerentregablenicolasetchemaite.R;
import com.digitahouse.tercerentregablenicolasetchemaite.controller.ControllerObras;
import com.digitahouse.tercerentregablenicolasetchemaite.controller.ControllerRoom;
import com.digitahouse.tercerentregablenicolasetchemaite.model.DAO.AppDataBase;
import com.digitahouse.tercerentregablenicolasetchemaite.model.DAO.DAODatabaseAcces;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Obra;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.ObraContainer;
import com.digitahouse.tercerentregablenicolasetchemaite.util.ResultListener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerViewObras extends RecyclerView.Adapter<AdapterRecyclerViewObras.ObrasViewHolder> {

    private List<Obra> paints;
    private Context context;





    public AdapterRecyclerViewObras(Context context) {
        this.context = context;
        this.paints = new ArrayList<>();
    }

    public void addListObras(List<Obra> listaObras) {
        paints.addAll(listaObras);
        notifyDataSetChanged();
    }
    public void addObraToList(Obra obra){
        paints.add(obra);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ObrasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.celda_recycler_obras, parent, false);
        ObrasViewHolder obrasViewHolder = new ObrasViewHolder(view);
        return obrasViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ObrasViewHolder holder, int position) {

        Obra obra = paints.get(position);
        holder.bindObra(obra, context);

    }

    @Override
    public int getItemCount() {
        return paints.size();
    }

    public class ObrasViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo;
        private ImageView imagenObra;
        private LinearLayout celda;

        public ObrasViewHolder(final View itemView) {
            super(itemView);
            this.imagenObra = itemView.findViewById(R.id.image_view);
            this.titulo = itemView.findViewById(R.id.text_view_title);
            this.celda = itemView.findViewById(R.id.celda_obras);

            celda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ListenerRecyclerObras listenerRecyclerObras = (ListenerRecyclerObras) itemView.getContext();
                    listenerRecyclerObras.openObraData(paints.get(getAdapterPosition()));
                }
            });
        }


        public void bindObra(Obra obra, Context context) {

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.placeholder).fitCenter();
            requestOptions.error(R.drawable.placeholder);
            requestOptions.fallback(R.drawable.placeholder);

            titulo.setText(obra.getName());
            Glide.with(context).applyDefaultRequestOptions(requestOptions).load(obra.getFullLink())
                    .transition(DrawableTransitionOptions.withCrossFade()).into(imagenObra);

        }

    }

    public interface ListenerRecyclerObras {
        void openObraData(Obra obra);
    }








}









    /* *//* public void getImageFromStorage(String string, final ImageView imageView, final Context context) {


        ControllerObras controllerObras = new ControllerObras();
        controllerObras.getImageFromStorage(string, new ResultListener<Uri>() {
            @Override
            public void finish(Uri result) {
               *//**//* RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(R.drawable.placeholder).fitCenter();
                requestOptions.error(R.drawable.placeholder);
                requestOptions.fallback(R.drawable.placeholder);

               Glide.with(context).applyDefaultRequestOptions(requestOptions).load(result)
                        .transition(DrawableTransitionOptions.withCrossFade()).into(imageView);*//*







            }
        });


    }*/








