package com.example.omer.hujicoursepostpc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.omer.hujicoursepostpc.Message;
import com.example.omer.hujicoursepostpc.R;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements message_fragment.InputMessageToMainActivity,MessageAdapter.OnLongClickMessageListener,DeleteFragment.OnLongClickListenerFromDeleteAdapter{

    EditText text;
    ListView messagesListView;
    MessageAdapter messagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.messegeEditText);

        messagesListView = findViewById(R.id.list_view);

        messagesAdapter = new MessageAdapter(this,new ArrayList<Message>(),this);
        messagesListView.setAdapter(messagesAdapter);

    }

    private void addMessageToList(Message msg){
        messagesAdapter.add(msg);

    }


    public void sendCLicked(View view) {
        String msgText = text.getText().toString();
        if(!msgText.isEmpty()){
            addMessageToList(new Message("omer",msgText,new Date()));
            text.setText(null);
        }

    }

    public void messageFragmentClicked(View view) {
        message_fragment.newInstance(this,text.getText().toString().trim()).show(getSupportFragmentManager());

    }

    @Override
    public void onSendClickedOnFragment(String messageToSend) {
        addMessageToList(new Message("omer",messageToSend,new Date()));
    }


    @Override
    public void onLongClickMessage(Message msg) {
        final DeleteFragment fragment = DeleteFragment.newInstance(this,msg);
        fragment.show(getSupportFragmentManager());


    }

    @Override
    public void OnAnyButtonCLicked(Message msg) {

        messagesAdapter.remove(msg);

    }
}

