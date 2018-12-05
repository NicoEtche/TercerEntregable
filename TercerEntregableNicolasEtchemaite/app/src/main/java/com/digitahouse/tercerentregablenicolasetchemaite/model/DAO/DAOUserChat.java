package com.digitahouse.tercerentregablenicolasetchemaite.model.DAO;

import android.support.annotation.NonNull;

import com.digitahouse.tercerentregablenicolasetchemaite.model.POJO.Message;
import com.digitahouse.tercerentregablenicolasetchemaite.view.AdapterChatIzq;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DAOUserChat {

    public void giveMessage(String contenido, AdapterChatIzq adapterChatIzq, String name) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String userId = firebaseUser.getUid();


        DatabaseReference databaseReference = database.getReference();

        DatabaseReference referenciaMensajes = databaseReference.child("messages");

        DatabaseReference referenciaPorCadaMensaje = referenciaMensajes.push();

        Message message = new Message(referenciaPorCadaMensaje.getKey(), contenido, name);

        message.setIsMine(true);

        referenciaPorCadaMensaje.setValue(message);

        adapterChatIzq.addMessageToChat(message);


    }

    public void reciveMessages(final AdapterChatIzq adapterChatIzq) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String userId = firebaseUser.getUid();

        DatabaseReference databaseReference = database.getReference();

        DatabaseReference messages = databaseReference.child("messages");


        messages.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot message : dataSnapshot.getChildren()) {

                    Message m = message.getValue(Message.class);

                    if (m.getName().equals(firebaseUser.getDisplayName())) {
                        m.setIsMine(true);
                    } else {
                        m.setIsMine(false);
                    }
                    adapterChatIzq.addMessageToChat(m);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}


