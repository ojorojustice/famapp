package com.example.famappapplication;

public class Message {

    String _message;
    String _name;
    String _profilePhoto;
    String _typeMessage;
    String _time;

    public Message(String message, String name, String profilePhoto, String typeMessage, String time) {
        _message = message;
        _name = name;
        _profilePhoto = profilePhoto;
        _typeMessage = typeMessage;
        _time = time;
    }

    public String get_message() {
        return _message;
    }

    public void set_message(String _message) {
        this._message = _message;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_profilePhoto() {
        return _profilePhoto;
    }

    public void set_profilePhoto(String _profilePhoto) {
        this._profilePhoto = _profilePhoto;
    }

    public String get_typeMessage() {
        return _typeMessage;
    }

    public void set_typeMessage(String _typeMessage) {
        this._typeMessage = _typeMessage;
    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }
}
