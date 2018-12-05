package com.digitahouse.tercerentregablenicolasetchemaite.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.digitahouse.tercerentregablenicolasetchemaite.R;
import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Message;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class AdapterChatIzq extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private FirebaseUser user;
    private List<Message> messages;
    private Integer celda;
    private LayoutInflater layoutInflater;


    public AdapterChatIzq() {
        this.messages = new ArrayList<>();
    }

    public void addMessageToChat(Message message) {
        List<String> ids = new ArrayList<>();
        for (Message me : messages) {
            ids.add(me.getId());
        }
        if (!ids.contains(message.getId())) {
            messages.add(message);
        }
        notifyDataSetChanged();
    }


    public void receiveDataBaseMessages(List<Message> messages) {
        this.messages.addAll(messages);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        layoutInflater = LayoutInflater.from(parent.getContext());

        if (viewType == 0) {
            View view = layoutInflater.inflate(R.layout.celda_chat_mine, parent, false);
            ChatViewHolderDer chatViewHolderDer = new ChatViewHolderDer(view);
            return chatViewHolderDer;

        }else{
            View view = layoutInflater.inflate(R.layout.celda_chat, parent, false);
            ChatViewHolder chatViewHolder = new ChatViewHolder(view);
            return chatViewHolder;

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);

        if (message.getIsMine()) {
            ((ChatViewHolderDer) holder).onBind(message);
        }else {
            ((ChatViewHolder) holder).onBind(message);
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (messages.get(position).getIsMine()) {

            return 0;
        }else{

            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {

        private TextView nombre;
        private TextView mensajeSorR;

        public ChatViewHolder(View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.text_view_nombre_user_chat);
            this.mensajeSorR = itemView.findViewById(R.id.text_view_message_chat);
        }

        public void onBind(Message message) {

            mensajeSorR.setText(message.getContenido());

            if (!message.getName().equals(null)) {
                nombre.setText(message.getName());
            } else {
                nombre.setText("Usuario Anónimo");
            }
        }
    }

    public class ChatViewHolderDer extends RecyclerView.ViewHolder {

        private TextView miNombre;
        private TextView miMensajeSorR;

        public ChatViewHolderDer(View itemView) {
            super(itemView);
            this.miNombre = itemView.findViewById(R.id.text_view_nombre_user_chat_mine);
            this.miMensajeSorR = itemView.findViewById(R.id.text_view_message_chat_mine);
        }

        public void onBind(Message message) {

            miMensajeSorR.setText(message.getContenido());

            if (!message.getName().equals(null)) {
                miNombre.setText(message.getName());
            } else {
                miNombre.setText("Usuario Anónimo");
            }
        }
    }
}


