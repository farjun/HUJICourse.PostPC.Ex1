package com.example.omer.hujicoursepostpc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.omer.hujicoursepostpc.Message;
import com.example.omer.hujicoursepostpc.R;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {

    Context context;
    List<Message> messages;
    OnLongClickMessageListener longClickListener;

    public MessageAdapter(@NonNull Context context, @NonNull List<Message> objects,OnLongClickMessageListener lisener){
        super(context, 0, objects);
        this.longClickListener = lisener;
        this.context = context;
        this.messages = objects;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitem = convertView;
        final Message message = messages.get(position);

        if (listitem == null) {
            listitem = LayoutInflater.from(context).inflate(R.layout.message_box, parent, false);
            listitem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longClickListener.onLongClickMessage(message);
                    return true;
                }
            });
        }


        TextView temp = listitem.findViewById(R.id.message_content);
        temp.setText(message.getMsg());

        temp = listitem.findViewById(R.id.user_name);
        temp.setText(message.getUsername());

        temp = listitem.findViewById(R.id.msg_time_stamp);
        temp.setText(message.getTime());

        return listitem;

    }

    @Override
    public void add(@Nullable Message object) {
        messages.add(object);
        notifyDataSetChanged();
    }

    public void remove(Message toRemove){
        messages.remove(messages.indexOf(toRemove));
        notifyDataSetChanged();
    }

    interface OnLongClickMessageListener{
        void onLongClickMessage(Message msg);
    }
}
