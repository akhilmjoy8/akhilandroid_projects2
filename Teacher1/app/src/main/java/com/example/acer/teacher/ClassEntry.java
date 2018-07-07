package com.example.acer.teacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.acer.teacher.Students.StudentDetailsMult;

public class ClassEntry extends AppCompatActivity {
    Button bt_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_entry);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bt_save = (Button) findViewById(R.id.btnAddClass);
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ClassEntry.this, StudentDetailsMult.class);
                startActivity(in);
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