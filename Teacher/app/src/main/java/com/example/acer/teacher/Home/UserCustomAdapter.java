package com.example.acer.teacher.Home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.acer.teacher.ClassEntry;
import com.example.acer.teacher.R;
import com.example.acer.teacher.Students.StudentDetailsMult;
import com.example.acer.teacher.Students.StudentTabActivity;
import com.example.acer.teacher.Students.StudentsHome;

import java.util.List;
public class UserCustomAdapter extends ArrayAdapter<ClassOutdata> {

    int resource;
    String response;
    Context context;
    private List<ClassOutdata> items;
    private UserCustomAdapter adapter;



    public UserCustomAdapter(Context context, int resource, List<ClassOutdata> items) {
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
        final ClassOutdata contact = getItem(position);
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
            sName.setText(contact.getName());
        }
        if (subject != null) {
            subject.setText(contact.getSubject());
        }

        sName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, StudentTabActivity.class);

                in.putExtra("cls",contact.getName());
                in.putExtra("sub",contact.getSubject());

                context.startActivity(in);
            }
        });
        subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, StudentTabActivity.class);
                in.putExtra("cls",contact.getName());
                in.putExtra("sub",contact.getSubject());
                context.startActivity(in);
            }
        });
        return contactsView;
    }
}