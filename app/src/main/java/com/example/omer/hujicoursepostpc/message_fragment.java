package com.example.omer.hujicoursepostpc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class message_fragment extends DialogFragment implements View.OnClickListener{
    private static final String TAG = message_fragment.class.getCanonicalName();

    EditText fragmentEditext;

    InputMessageToMainActivity listener;




    public static message_fragment newInstance(InputMessageToMainActivity mainActivity,String messageThatWasAtMainActivity) {

        Bundle args = new Bundle();
        args.putString("msg",messageThatWasAtMainActivity);
        message_fragment fragment = new message_fragment();
        fragment.listener = mainActivity;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.message_fragmnet_pop_up, container, false);

        fragmentEditext = view.findViewById(R.id.fragment_editext);
        fragmentEditext.setText(getArguments().getString("msg"));

        view.findViewById(R.id.fragment_send_button).setOnClickListener(this);
        view.findViewById(R.id.fragment_cancel_button).setOnClickListener(this);

        return view;
    }

    private void handleSentClicked(View v){
        listener.onSendClickedOnFragment(fragmentEditext.getText().toString());
        fragmentEditext.setText(null);
    }

    private void handleCancelClicked(View v){
        dismiss();
    }

    public void show(FragmentManager manager) {
        show(manager, TAG);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_send_button:
                handleSentClicked(v);
                break;
            case R.id.fragment_cancel_button:
                handleCancelClicked(v);
                break;
            default:
                Toast.makeText(v.getContext(), "holy gwakamoly.. what did you do?!", Toast.LENGTH_SHORT).show();
        }
    }


    public interface InputMessageToMainActivity{
        void onSendClickedOnFragment(String messageToSend);
    }
}


