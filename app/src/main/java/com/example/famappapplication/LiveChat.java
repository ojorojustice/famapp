package com.example.famappapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class LiveChat extends AppCompatActivity {

    CircleImageView _profilePhoto;
    TextView _name;
    RecyclerView _rvMessage;
    EditText _txtMessage;
    Button _btnSendMessage;
    AddapterMessages _addapter;

    FirebaseDatabase _database;
    DatabaseReference _databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_chat);

        _profilePhoto = (CircleImageView) findViewById(R.id.profilePhoto);
        _name = (TextView) findViewById(R.id.chatName);
        _rvMessage = (RecyclerView) findViewById(R.id.rvMessage);
        _txtMessage = (EditText) findViewById(R.id.txtMessage);
        _btnSendMessage = (Button) findViewById(R.id.btnSendMessage);

        _database = FirebaseDatabase.getInstance();
        _databaseReference = _database.getReference("chat");

        _addapter = new AddapterMessages(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        _rvMessage.setLayoutManager(l);
        _rvMessage.setAdapter(_addapter);

        _btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _addapter.addMessage(new Message(_txtMessage.getText().toString(),
                        _name.getText().toString(),
                        "",
                        "1",
                        "00:00"));
                //_databaseReference.push().setValue();
                _txtMessage.setText("");
            }
        });

        _addapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollBar();
            }
        });


    }

    private void setScrollBar(){
        _rvMessage.scrollToPosition(_addapter.getItemCount()-1);
    }
}