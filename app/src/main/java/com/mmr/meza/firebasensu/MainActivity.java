package com.mmr.meza.firebasensu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference nsuRef = database.getReference("NSU_WorkShop");
    private ListAdapter listAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerViewList);

        final EditText nameET = findViewById(R.id.name);
        final EditText IdEt = findViewById(R.id.id);

        Button button = findViewById(R.id.button);

        final ArrayList<Student> studentArrayList = new ArrayList<>();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Student tempStudent = new Student(nameET.getText().toString(), IdEt.getText().toString());
                nsuRef.child(IdEt.getText().toString()).setValue(tempStudent).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(MainActivity.this, task.toString(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });


        nsuRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Student tempStudent = dataSnapshot.getValue(Student.class);

                studentArrayList.add(tempStudent);


                Log.e("Child" , " List Size "+studentArrayList.size()+"");



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.e("Child change", dataSnapshot.getValue().toString());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.e("Child remove", dataSnapshot.getValue().toString());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.e("Child move", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Child Cancel", databaseError.toString());
            }
        });


        findViewById(R.id.showButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                listAdapter = new ListAdapter(MainActivity.this , studentArrayList);
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);



                recyclerView.setAdapter(listAdapter);
                recyclerView.setLayoutManager(layoutManager);

            }
        });





    }
}
