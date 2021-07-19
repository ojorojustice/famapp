package com.example.famappapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageHolder extends RecyclerView.ViewHolder {
    TextView _name;
    TextView _message;
    TextView _time;
    CircleImageView _messagePhoto;


    public TextView get_name() {
        return _name;
    }

    public void set_name(TextView _name) {
        this._name = _name;
    }

    public TextView get_message() {
        return _message;
    }

    public void set_message(TextView _message) {
        this._message = _message;
    }

    public TextView get_time() {
        return _time;
    }

    public void set_time(TextView _time) {
        this._time = _time;
    }

    public CircleImageView get_messagePhoto() {
        return _messagePhoto;
    }

    public void set_messagePhoto(CircleImageView _messagePhoto) {
        this._messagePhoto = _messagePhoto;
    }

    public MessageHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
        super(itemView);
        _name = (TextView)itemView.findViewById(R.id.nameMessage);
        _message = (TextView) itemView.findViewById(R.id.messageMessage);
        _time = (TextView) itemView.findViewById(R.id.timeMessage);
        _messagePhoto = (CircleImageView) itemView.findViewById(R.id.profilePhotoMessage);

    }
}
