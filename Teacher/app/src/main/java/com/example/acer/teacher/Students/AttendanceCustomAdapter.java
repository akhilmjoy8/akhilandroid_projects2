package com.example.acer.teacher.Students;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.teacher.ClassEntry;
import com.example.acer.teacher.Home.ClassOutdata;
import com.example.acer.teacher.R;
import com.example.acer.teacher.ServerConnection.SessionManager;
import com.example.acer.teacher.Students.StudentDetailsMult;
import com.example.acer.teacher.Students.StudentsHome;
import com.example.acer.teacher.User;
import com.example.acer.teacher.dbHelp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AttendanceCustomAdapter extends ArrayAdapter<Student> {
    SessionManager session;
    int resource;
    String response;
    Context context;
    private List<Student> items;
    private com.example.acer.teacher.Students.AttendanceCustomAdapter adapter;
    ArrayList<Studentname> userArray22 = new ArrayList<Studentname>();


    public AttendanceCustomAdapter(Context context, int resource, List<Student> items) {
        super(context, resource, items);
        this.resource=resource;
        this.context = context;
        this.items=items;
        this.adapter = this;
        // Progress dialog

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        session = new SessionManager(getContext());
        LinearLayout contactsView;
        final Student contact = getItem(position);
        if (convertView == null) {
            contactsView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(resource, contactsView, true);
        } else {
            contactsView = (LinearLayout) convertView;
        }
        dbHelp dbH4 = new dbHelp(getContext());
        dbH4.deleteStudentAll();
        Cursor data = dbH4.getStudentDetails();
        int numRows = data.getCount();

        final TextView sName = (TextView) contactsView.findViewById(R.id.tvStudentName);
        final TextView id = (TextView) contactsView.findViewById(R.id.txthidden);
        final Switch sw=(Switch) contactsView.findViewById(R.id.switch4);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(sw.isChecked()!=true)
                {
                    String s=contact.getSname();
                    String id1=contact.getId();
                    dbHelp dbH4 = new dbHelp(getContext());
                    SQLiteDatabase db4 = dbH4.getWritableDatabase();
                    ContentValues value = new ContentValues();
                    value.put(dbHelp.FNAME, s);
                    value.put(dbHelp.ID, id1);
                    db4.insert(dbHelp.TABLE_STUDENT, null, value);

                    Cursor data = dbH4.getStudentDetails();
                    int numRows = data.getCount();
                    if (numRows == 0){
                        Toast.makeText(getContext(),"There is nothing to show..!",Toast.LENGTH_LONG).show();
                    }
                    else {
                        for(int i=0;i<numRows;i++) {

                            data.moveToNext();
                            String fname = data.getString(0);
                            String id = data.getString(1);
                            String fname1 = data.getString(0);
                           // Toast.makeText(getContext(), "id:"+id, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    String id2=contact.getId();
                    dbHelp dbH4 = new dbHelp(getContext());
                    SQLiteDatabase db4 = dbH4.getWritableDatabase();
                    dbH4.deleteStudentTableContent(id2);

                    //Cursor data1 = dbH4.getStudentDetails();
                   // int numRows1 = data1.getCount();
                   //Toast.makeText(getContext(), "id:"+id2+"count:"+numRows1, Toast.LENGTH_SHORT).show();

                }
            }
        });

//        final TextView subject = (TextView) contactsView.findViewById(R.id.tvSubjectName);

        if (sName != null) {
            sName.setText(contact.getSname());
        }
//        if (subject != null) {
//            subject.setText(contact.getPhno());
//        }
        if(id !=null)
        {
            id.setText(contact.getId());
        }
        return contactsView;
    }
}