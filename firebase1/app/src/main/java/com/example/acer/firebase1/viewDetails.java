package com.example.acer.firebase1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class viewDetails extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    ListView lst;
private ArrayList<String> arrayList=new ArrayList<>();
 private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        lst=(ListView) findViewById(R.id.lst);
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, arrayList);
//        arrayList.add("hghghj");
//        lst.setAdapter(adapter);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                   User u = ds.getValue(User.class);

                   arrayList.add(u.getName().toString()+"\n"+u.getPhone().toString()+"\n");
                }
               lst.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
               // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
}
