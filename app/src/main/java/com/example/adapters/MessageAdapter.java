package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activities.R;
import com.example.model.Message;
import com.example.services.MessageService;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private static int viewHolderCount;

    public MessageAdapter() {
        viewHolderCount = 0;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        List<Message> messages = MessageService.getInstance().getMessages();
        View view;

        if (viewHolderCount % 2 == 0)
            view = inflater.inflate(R.layout.my_message_layout, parent, false);
        else
            view = inflater.inflate(R.layout.agent_message_layout, parent, false);

        MessageAdapter.MessageViewHolder viewHolder = new MessageAdapter.MessageViewHolder(view);
        viewHolderCount++;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.bind(MessageService.getInstance().getMessages().get(position));
    }

    @Override
    public int getItemCount() {
        return MessageService.getInstance().getMessages().size();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.message_body);
        }

        public void bind(Message message){
            textView.setText(message.getMessage());
        }
    }
}
