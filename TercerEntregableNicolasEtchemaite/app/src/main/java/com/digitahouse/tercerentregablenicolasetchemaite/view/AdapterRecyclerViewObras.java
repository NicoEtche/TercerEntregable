package com.digitahouse.tercerentregablenicolasetchemaite.view;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
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
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Obra;
import com.digitahouse.tercerentregablenicolasetchemaite.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerViewObras extends RecyclerView.Adapter<AdapterRecyclerViewObras.ObrasViewHolder> {

    private List<Obra> paints;

    public AdapterRecyclerViewObras() {
        this.paints = new ArrayList<>();
    }

    public void addListObras(List<Obra> listaObras) {
        paints = listaObras;
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
        holder.bindObra(obra);
    }

    @Override
    public int getItemCount() {
        return paints.size();
    }

    public class ObrasViewHolder extends RecyclerView.ViewHolder {
        private ControllerObras controllerObras = new ControllerObras();
        private ImageView imagenObra;
        private TextView titulo;
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

        public void bindObra(Obra obra) {

            String realPath = obra.getImage().substring(13,obra.getImage().length());
            titulo.setText(obra.getName());
            getImageFromStorage(realPath, imagenObra, itemView.getContext());

        }

    }

    public interface ListenerRecyclerObras {
        void openObraData(Obra obra);
    }

    public void getImageFromStorage(String string, final ImageView imageView, final Context context) {
        ControllerObras controllerObras = new ControllerObras();
        controllerObras.getImageFromStorage(string, new ResultListener<Uri>() {
            @Override
            public void finish(Uri result) {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(R.drawable.placeholder).fitCenter();
                requestOptions.error(R.drawable.placeholder);
                requestOptions.fallback(R.drawable.placeholder);

                Glide.with(context).applyDefaultRequestOptions(requestOptions).load(result)
                        .transition(DrawableTransitionOptions.withCrossFade()).into(imageView);

            }
        });

    }

  /*  public Integer countChars(String string){

        int counter = 0;
        for( int i=0; i<string.length(); i++ ) {
            if( s.charAt(i) == '$' ) {
                counter++;
            }
        }

    }*/

    }




