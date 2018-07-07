package com.example.acer.teacher.Subjects;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.acer.teacher.ClassEntry;
import com.example.acer.teacher.Home.ClassOutdata;
import com.example.acer.teacher.Home.Home;
import com.example.acer.teacher.Home.UserCustomAdapter;
import com.example.acer.teacher.R;
import com.example.acer.teacher.ServerConnection.AppConfig;
import com.example.acer.teacher.ServerConnection.AppController;
import com.example.acer.teacher.ServerConnection.SessionManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SubjectsHome extends AppCompatActivity {
    FirebaseDatabase database;
    ListView lst;
    ArrayList<ClassOutdata> userArray = new ArrayList<ClassOutdata>();
    SessionManager session;
    String uid,class_sv;
    Activity context = this;

    Button subSave;

    private ArrayList<String> arrayList=new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Class");

//        Intent in = getIntent();
//
//        final String classname = in.getStringExtra("cls");
//        final String uid = in.getStringExtra("uid");

        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        lst=(ListView) findViewById(R.id.lstSubject);

        //call to session values
        session = new SessionManager(getApplicationContext());
        class_sv = session.getValue(SubjectsHome.this, "class_name");
        uid = session.getValue(SubjectsHome.this, "uid");

        final EditText sub_name=(EditText) findViewById(R.id.etSub);
        final DatabaseReference myRef = database.getReference(uid).child(class_sv).child("subjects");
        Button btsave=(Button) findViewById(R.id.btnSubSave);
        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OneSub sub=new OneSub(sub_name.getText().toString());
                String id = myRef.push().getKey();
                myRef.child(id).setValue(sub);
            }
        });

        database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef1 = database.getReference(uid);
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //SDetail u = dataSnapshot.getValue(SDetail.class);
                // Toast.makeText(StudentDetailsMult.this,u.getPhone().toString(),Toast.LENGTH_LONG).show();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    final String cls = ds.child("name").getValue(String.class);

                    final DatabaseReference myRef = database.getReference(uid).child(cls).child("subjects");
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            //SDetail u = dataSnapshot.getValue(SDetail.class);
                            // Toast.makeText(StudentDetailsMult.this,u.getPhone().toString(),Toast.LENGTH_LONG).show();
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                String sub = ds.child("subject").getValue(String.class);
//                                ClassOutdata outpass = new ClassOutdata(sub);

                                //outpass.setName(cls);
//                                outpass.setSubject(sub)
                                arrayList.add(sub);

                            }
                            lst.setAdapter(adapter);

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w("Failed to read value.", error.toException());
                        }
                    });
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });

        subSave = findViewById(R.id.btnSubSave);
        subSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
