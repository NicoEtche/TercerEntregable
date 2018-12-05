package com.digitahouse.tercerentregablenicolasetchemaite.controller;

import com.digitahouse.tercerentregablenicolasetchemaite.model.DAO.DAOUserChat;
import com.digitahouse.tercerentregablenicolasetchemaite.view.AdapterChatIzq;

public class ControllerChat {

    public void giveMessage(String contenido, AdapterChatIzq adapterChatIzq, String name){
        DAOUserChat daoUserChat = new DAOUserChat();

        daoUserChat.giveMessage(contenido, adapterChatIzq, name);
    }

    public void receiveMessages(AdapterChatIzq adapterChatIzq){
        DAOUserChat daoUserChat = new DAOUserChat();

        daoUserChat.reciveMessages(adapterChatIzq);



    }
}
