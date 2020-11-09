package com.example.pushnotificationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pushnotificationapp.db.AppDatabse;
import com.example.pushnotificationapp.db.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewUser extends AppCompatActivity {

    UserListAdapter userListAdapter;
    EditText editText;
    Button btn;
    int id;
    FirebaseDatabase database;
    DatabaseReference reference;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);
        member=new Member();
        btn=findViewById(R.id.button);
        editText=findViewById(R.id.editTextTextPersonName);

        final EditText text=findViewById(R.id.editTextTextPersonName);
        Button saveButton=findViewById(R.id.button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=editText.getText().toString();

                if (name.isEmpty()){
                    Toast.makeText(getApplicationContext(), "please fill", Toast.LENGTH_SHORT).show();
                }
                else{
                    member.setName(editText.getText().toString());
                    reference.child(String.valueOf(id+1)).setValue(member);
                }
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNewUser(text.getText().toString());
            }
        });
    }

    private void saveNewUser(String text) {
        AppDatabse db =AppDatabse.getDbInstance(this.getApplicationContext());

        User user = new User();
        user.text_input=text;
        db.userDao().insertUser();
        finish();

    }
}