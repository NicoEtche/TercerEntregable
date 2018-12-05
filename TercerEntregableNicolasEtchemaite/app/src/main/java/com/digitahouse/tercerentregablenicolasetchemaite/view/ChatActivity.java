package com.digitahouse.tercerentregablenicolasetchemaite.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.digitahouse.tercerentregablenicolasetchemaite.R;
import com.digitahouse.tercerentregablenicolasetchemaite.controller.ControllerChat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChatActivity extends AppCompatActivity {

    private ImageView sendButton;
    private EditText editText;
    private RecyclerView recyclerView;
    private AdapterChatIzq adapterChatIzq;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        user = FirebaseAuth.getInstance().getCurrentUser();
        sendButton = findViewById(R.id.image_button_send);
        editText = findViewById(R.id.edit_text_message);
        adapterChatIzq = new AdapterChatIzq();
        recyclerView = findViewById(R.id.recycler_view_chat);
        receiveMessages(adapterChatIzq);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterChatIzq);
        recyclerView.setLayoutManager(linearLayoutManager);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String contenido = editText.getText().toString();
                giveMessage(contenido, adapterChatIzq, user.getDisplayName());
                InputMethodManager imm = (InputMethodManager) ChatActivity.this.getSystemService(ChatActivity.INPUT_METHOD_SERVICE);
                //Find the currently focused view, so we can grab the correct window token from it.
                View view = ChatActivity.this.getCurrentFocus();
                //If no view currently has focus, create a new one, just so we can grab a window token from it
                if (view == null) {
                    view = new View(getApplicationContext());
                }
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                editText.setText("");
            }
        });

    }

    public void giveMessage(String contenido, AdapterChatIzq adapterChatIzq, String name) {
        ControllerChat controllerChat = new ControllerChat();
        controllerChat.giveMessage(contenido, adapterChatIzq, name);
    }

    public void receiveMessages(AdapterChatIzq adapterChatIzq) {
        ControllerChat controllerChat = new ControllerChat();
        controllerChat.receiveMessages(adapterChatIzq);
    }
}
