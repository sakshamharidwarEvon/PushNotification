package com.example.pushnotificationapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pushnotificationapp.db.AppDatabse;
import com.example.pushnotificationapp.db.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    UserListAdapter userListAdapter;
    EditText editText;
    Button btn;
    int id;
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference reference;
    Member member;
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mfirestoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        member=new Member();
        btn=findViewById(R.id.button);
        editText=findViewById(R.id.editTextTextPersonName);
        recyclerView=findViewById(R.id.recyclerview);

        firebaseFirestore=FirebaseFirestore.getInstance();
        mfirestoreList=findViewById(R.id.recyclerview);




        reference=database.getInstance().getReference().child("User");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    id=(int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                String name=editText.getText().toString();

                if (name.isEmpty()){
                    Toast.makeText(getApplicationContext(), "please fill", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    notification();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




//        View myView = findViewById(R.id.button);
//        myView.performClick();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=editText.getText().toString();


                if (name.isEmpty()){
                    Toast.makeText(getApplicationContext(), "please fill", Toast.LENGTH_SHORT).show();
                }
                else{
                    member.setName(editText.getText().toString());
                    reference.child(String.valueOf(id+1)).setValue(member);

                    Intent launchActivity1= new Intent(MainActivity.this,FirebaseDataDisplay.class);
                    startActivity(launchActivity1);

                }
            }
        });

//
//        initRecyclerView();
//        loadUserList();
    }


//    private void initRecyclerView() {
//        RecyclerView recyclerView=findViewById(R.id.recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        DividerItemDecoration dividerItemDecoration =new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
//        recyclerView.addItemDecoration(dividerItemDecoration);
//
//        recyclerView.setAdapter(userListAdapter);
//    }
//
//    private void loadUserList(){
//        AppDatabse db = AppDatabse.getDbInstance(this.getApplicationContext());
//        List<User> userList=db.userDao().getAllUser();
//        userListAdapter.setUserList(userList);
//    }

    private void notification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel=new NotificationChannel("n","n", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }
        Intent resultIntent=new Intent(this,MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,1,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"n")
                .setContentText("App Started")
                .setSmallIcon(R.drawable.icon)
                .setAutoCancel(true)
                .setContentText("App is Started")
                .setContentIntent(resultPendingIntent);

        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(this);
        managerCompat.notify(999,builder.build());
    }


}