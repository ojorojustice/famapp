package com.example.famappapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AddapterMessages extends RecyclerView.Adapter<MessageHolder> {

    List<Message> _messageList = new ArrayList<>();
    Context _context;

    public AddapterMessages(Context context) {
        _context = context;
    }

    public void addMessage(Message message){
        _messageList.add(message);
        notifyItemInserted(_messageList.size());
    }

    @NonNull
    @NotNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(_context).inflate(R.layout.card_view_messages,parent,false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MessageHolder holder, int position) {
        holder.get_name().setText(_messageList.get(position).get_name());
        holder.get_message().setText(_messageList.get(position).get_message());
        holder.get_time().setText(_messageList.get(position).get_time());
    }

    @Override
    public int getItemCount() {
        return _messageList.size();
    }
}
