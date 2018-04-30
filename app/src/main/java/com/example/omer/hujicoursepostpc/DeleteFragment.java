package com.example.omer.hujicoursepostpc;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteFragment extends DialogFragment implements View.OnClickListener{
    private static final String TAG = message_fragment.class.getCanonicalName();

    OnLongClickListenerFromDeleteAdapter listenerForOkCancelButtons;
    Message toDelete;

    public static DeleteFragment newInstance(OnLongClickListenerFromDeleteAdapter listener,Message msgToDelete) {

        Bundle args = new Bundle();
        args.putString("msg",msgToDelete.getMsg());
        args.putString("date",msgToDelete.getTime());
        args.putString("username",msgToDelete.getUsername());
        DeleteFragment fragment = new DeleteFragment();
        fragment.toDelete = msgToDelete;
        fragment.listenerForOkCancelButtons = listener;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.message_delete, container, false);


        TextView msgDetails = view.findViewById(R.id.delete_fragment_message_details);
        msgDetails.setText("date: " + getArguments().getString("date") + "\nmessage: " +getArguments().getString("msg") +
                "\nsent by: " + getArguments().getString("username") );
        view.findViewById(R.id.delete_fragment_message_ok_button).setOnClickListener(this);
        view.findViewById(R.id.delete_fragment_message_cancel_button).setOnClickListener(this);
        return view;
    }

    public void show(FragmentManager manager) {
        show(manager, TAG);
    }

    private void handleOkClicked(View v){
        listenerForOkCancelButtons.OnAnyButtonCLicked(toDelete);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.delete_fragment_message_cancel_button:
                dismiss();
                break;
            case R.id.delete_fragment_message_ok_button:
                handleOkClicked(v);
                dismiss();
                break;
            default:
                System.out.println("what did you do?");
        }

    }


    interface OnLongClickListenerFromDeleteAdapter{
        void OnAnyButtonCLicked(Message msg);

    }
}
