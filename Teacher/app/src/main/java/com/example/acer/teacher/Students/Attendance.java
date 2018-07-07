package com.example.acer.teacher.Students;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.teacher.R;
import com.example.acer.teacher.ServerConnection.SessionManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Attendance extends Fragment implements AdapterView.OnItemClickListener {

    public Attendance() {
        // Required empty public constructor
    }

    FirebaseDatabase database;
    ListView lst;
    ArrayList<Student> userArray = new ArrayList<Student>();
    SessionManager session;
    String uid, class_sv;
    ArrayList<String> temp=new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_attendance, container, false);

        session = new SessionManager(getContext());

        uid = session.getValue(getContext(), "uid");
        Intent in =getActivity().getIntent();

        final String classname = in.getStringExtra("cls");
        final String sub = in.getStringExtra("sub");


        // adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        lst = (ListView) view.findViewById(R.id.lstAttendance);
        lst.setAdapter(null);
        database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef1 = database.getReference(uid).child(classname).child("students");


            myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    //SDetail u = dataSnapshot.getValue(SDetail.class);
                    // Toast.makeText(StudentDetailsMult.this,u.getPhone().toString(),Toast.LENGTH_LONG).show();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String id = ds.getKey();
                        String s = id;
                        String name2 = ds.child("stud_name").getValue(String.class);
                        String phone = ds.child("phone").getValue(String.class);
                        Student outpass = new Student(name2, phone, id);
                        userArray.add(outpass);

                    }
                    AttendanceCustomAdapter adapter = new AttendanceCustomAdapter(getContext(), R.layout.listview_attendance, userArray);
                    lst.setAdapter(null);
                    lst.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("Failed to read value.", error.toException());
                }
            });


        return view;
    }

 @Override
 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

     TextView textView = (TextView) view.findViewById(R.id.txthidden);
     String t=textView.getText().toString();
     Toast.makeText(getContext(),t,Toast.LENGTH_LONG).show();
   }
}