package com.example.acer.teacher.Students;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.acer.teacher.ClassEntry;
import com.example.acer.teacher.Home.ClassOutdata;
import com.example.acer.teacher.R;
import com.example.acer.teacher.Students.StudentDetailsMult;
import com.example.acer.teacher.Students.StudentsHome;

import java.util.List;
public class StudentCustomAdapter extends ArrayAdapter<Student> {

    int resource;
    String response;
    Context context;
    private List<Student> items;
    private com.example.acer.teacher.Students.StudentCustomAdapter adapter;



    public StudentCustomAdapter(Context context, int resource, List<Student> items) {
        super(context, resource, items);
        this.resource=resource;
        this.context = context;
        this.items=items;
        this.adapter = this;
        // Progress dialog

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
        final TextView sName = (TextView) contactsView.findViewById(R.id.tvClassName);
        final TextView subject = (TextView) contactsView.findViewById(R.id.tvSubjectName);

        if (sName != null) {
            sName.setText(contact.getSname());
        }
        if (subject != null) {
            subject.setText(contact.getPhno());
        }

        sName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, StudentsHome.class);

                in.putExtra("name",contact.getSname());
                in.putExtra("phno",contact.getPhno());

                context.startActivity(in);
            }
        });
        subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, StudentsHome.class);
                in.putExtra("name",contact.getSname());
                in.putExtra("phno",contact.getPhno());
                context.startActivity(in);
            }
        });
        return contactsView;
    }
}