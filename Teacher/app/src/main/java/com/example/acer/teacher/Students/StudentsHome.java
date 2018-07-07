package com.example.acer.teacher.Students;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.acer.teacher.ClassEntry;
import com.example.acer.teacher.Home.ClassOutdata;
import com.example.acer.teacher.Home.Home;
import com.example.acer.teacher.Home.UserCustomAdapter;
import com.example.acer.teacher.R;
import com.example.acer.teacher.ServerConnection.SessionManager;
import com.example.acer.teacher.Subjects.SubjectsHome;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class StudentsHome extends Fragment {

    public StudentsHome() {
        // Required empty public constructor
    }

    FirebaseDatabase database;
    ListView lst;
    ArrayList<Student> userArray = new ArrayList<Student>();
    SessionManager session;
    String uid, class_sv;
//    Activity context = this;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_students_home, container, false);


        session = new SessionManager(getContext());

        uid = session.getValue(getContext(), "uid");
        Intent in =getActivity().getIntent();

        final String classname = in.getStringExtra("cls");
        final String sub = in.getStringExtra("sub");


        // adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        lst = (ListView) view.findViewById(R.id.lstStudents);

        database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef1 = database.getReference(uid).child(classname).child("students");
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //SDetail u = dataSnapshot.getValue(SDetail.class);
                // Toast.makeText(StudentDetailsMult.this,u.getPhone().toString(),Toast.LENGTH_LONG).show();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name2 = ds.child("stud_name").getValue(String.class);
                    String phone = ds.child("phone").getValue(String.class);
                    Student outpass = new Student(name2, phone);
                    userArray.add(outpass);

                }
                StudentCustomAdapter adapter = new StudentCustomAdapter(getContext(), R.layout.subject_list, userArray);
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

}
