package com.example.acer.attendance_app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class UserListAdapter extends ArrayAdapter<String> {
    private String[] uId;
    private String[] uNames;
    private Activity context;

    public UserListAdapter(Activity context, String[] uId, String[] uNames) {
        super(context, R.layout.list_row, uId);
        this.context = context;
        this.uId = uId;
        this.uNames = uNames;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_row, parent, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.tv_uid);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.tv_uname);



        textViewId.setText(uId[position]);
        textViewName.setText(uNames[position]);


        return listViewItem;
    }
}
