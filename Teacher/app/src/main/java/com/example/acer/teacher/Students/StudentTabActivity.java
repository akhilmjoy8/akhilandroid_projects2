package com.example.acer.teacher.Students;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.teacher.Home.Home;
import com.example.acer.teacher.Home.Login;
import com.example.acer.teacher.R;
import com.example.acer.teacher.ServerConnection.SessionManager;
import com.example.acer.teacher.Subjects.SubjectsHome;
import com.example.acer.teacher.User;
import com.example.acer.teacher.dbHelp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class StudentTabActivity extends AppCompatActivity {

    FirebaseDatabase database;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ArrayList<String> arrayList=new ArrayList<String>();
    private SessionManager session;
    ImageView save;
    String uid,key,id;
    int i;
    int t,t1;
    boolean resultOfComparison;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // session manager
        session = new SessionManager(getApplicationContext());
        uid = session.getValue(getApplicationContext(), "uid");

        //getting values from Classentry
        Intent in= getIntent();
        final String classname=in.getStringExtra("cls");
        final String sub=in.getStringExtra("sub");


        database = FirebaseDatabase.getInstance();
        final DatabaseReference mref = database.getReference(uid).child(classname).child("students");
        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    key = ds.getKey();
                    arrayList.add(key);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        ImageView  save=(ImageView) findViewById(R.id.btSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = arrayList.size();
                for (int j = 0; j < n; j++) {
                    String s = arrayList.get(j);
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat mdformat = new SimpleDateFormat("dd-MM-yyyy");
                    String strDate = mdformat.format(calendar.getTime()).toString();

                    final DatabaseReference myRef1 = database.getReference(uid).child(classname).child("students").child(s).child("Attendance").child(strDate);

                    dbHelp dbH4 = new dbHelp(StudentTabActivity.this);
                    SQLiteDatabase db4 = dbH4.getWritableDatabase();
                    final Cursor data = dbH4.getStudentDetails();
                    final int numRows = data.getCount();
                    try {
                        if (numRows != 0) {
                            for (i = 0; i < numRows; i++) {
                                data.moveToNext();
                                String id = data.getString(1);
                                // Toast.makeText(StudentTabActivity.this, id, Toast.LENGTH_SHORT).show();
                                resultOfComparison = s.equals(id);
                                if (resultOfComparison) {
                                    break;
                                }
                            }


                            if (resultOfComparison == true) {

                                UserAtt udata = new UserAtt(sub, "absent");
                                String id2 = myRef1.push().getKey();
                                myRef1.child(id2).setValue(udata);
                                Toast.makeText(StudentTabActivity.this, "absent", Toast.LENGTH_SHORT).show();


                            } else {
                                UserAtt udata = new UserAtt(sub, "present");
                                String id2 = myRef1.push().getKey();
                                myRef1.child(id2).setValue(udata);
                                Toast.makeText(StudentTabActivity.this, "present", Toast.LENGTH_SHORT).show();

                            }

                        } else {
                            UserAtt udata = new UserAtt(sub, "present");
                            String id2 = myRef1.push().getKey();
                            myRef1.child(id2).setValue(udata);
                            Toast.makeText(StudentTabActivity.this, "present", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {

                        Toast.makeText(StudentTabActivity.this, "" + e, Toast.LENGTH_LONG).show();
                    }

                }
            }

        });


        FloatingActionButton subject = findViewById(R.id.fabAddSubject);


        subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(StudentTabActivity.this, SubjectsHome.class);
                startActivity(in);
            }
        });

        FloatingActionButton student = findViewById(R.id.fabAddStudent);


        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(StudentTabActivity.this, StudentDetailsMult.class);
                startActivity(in);
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Attendance(), "Attendance");
        adapter.addFrag(new StudentsHome(), "Students");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


 }
