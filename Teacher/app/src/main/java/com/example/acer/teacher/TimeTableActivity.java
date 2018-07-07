package com.example.acer.teacher;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.acer.teacher.Home.Home;
import com.example.acer.teacher.ServerConnection.SessionManager;
import com.example.acer.teacher.Students.StudentDetailsMult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class TimeTableActivity extends AppCompatActivity {
    SessionManager session;
    EditText sdate;
    String uid;
    ArrayList<String> spinnerArray = new ArrayList<String>();
    ArrayList<String> spinnerArray2 = new ArrayList<String>();
    ImageView btnMonday, btnTuesday, btnWednesday, btnThursday, btnFriday, btnSaturday, btnSunday;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        // Session manager
        session = new SessionManager(getApplicationContext());

        //Retrieve a value from SharedPreference
        uid = session.getValue(TimeTableActivity.this,"uid");

        btnMonday = findViewById(R.id.imgMonday);
        btnMonday.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                String d = "Monday";
                periodPopUp(d);
            }});

        btnTuesday = findViewById(R.id.imgTuesday);
        btnTuesday.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                periodPopUp("Tuesday");

            }});

        btnWednesday = findViewById(R.id.imgWednesday);
        btnWednesday.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
               periodPopUp("Wednesday");

            }});

        btnThursday = findViewById(R.id.imgThursday);
        btnThursday.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
             periodPopUp("Thursday");

            }});

        btnFriday = findViewById(R.id.imgFriday);
        btnFriday.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
               periodPopUp("Friday");

            }});

        btnSaturday = findViewById(R.id.imgSaturday);
        btnSaturday.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                periodPopUp("Saturday");

            }});

        btnSunday = findViewById(R.id.imgSunday);
        btnSunday.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                periodPopUp("Sunday");

            }});
    }

    public void periodPopUp(String day){
        final AlertDialog.Builder builder = new AlertDialog.Builder(TimeTableActivity.this);
        LayoutInflater inflater = (TimeTableActivity.this).getLayoutInflater();
        final View dialogView=inflater.inflate(R.layout.popup_periods, null);
        builder.setCancelable(false);
        builder.setView(dialogView);

        final String d = day;
        TextView dayname = dialogView.findViewById(R.id.tvDayName);
        dayname.setText(day);
        final EditText editText=(EditText) dialogView.findViewById(R.id.etp);

        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        mgr.showSoftInput(editText, InputMethodManager.SHOW_FORCED);

        final AlertDialog alertDialog = builder.create();
        Button btnDismiss = (Button)dialogView.findViewById(R.id.btnCancel);
        btnDismiss.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                
                alertDialog.dismiss();
            }});

        Button btnNext = (Button)dialogView.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                popUp(d);
                alertDialog.dismiss();

            }});



        alertDialog.show();
    }

    public void popUp(String day){
        final AlertDialog.Builder builder = new AlertDialog.Builder(TimeTableActivity.this);
        LayoutInflater inflater = (TimeTableActivity.this).getLayoutInflater();
        final View dialogView=inflater.inflate(R.layout.popup,null);
        builder.setCancelable(false);
        builder.setView(dialogView);

        TextView dayname = dialogView.findViewById(R.id.tvDayName);
        dayname.setText(day);

        final ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                spinnerArray);
        final ArrayAdapter spinnerArrayAdapter2 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                spinnerArray2);
        spinnerArray.add("Select Class");


        final Spinner sp_class=dialogView.findViewById(R.id.sp_class);
        sp_class.setAdapter(null);
        final Spinner sp_sub=dialogView.findViewById(R.id.sp_sub);
        sp_sub.setAdapter(null);
        final DatabaseReference myRef = database.getReference(uid);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sp_class.setAdapter(null);
                spinnerArray.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    String cls_name=ds.getKey();
                    spinnerArray.add(cls_name);


                }
                sp_class. setAdapter(spinnerArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



       sp_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               sp_sub.setAdapter(null);
               spinnerArray2.clear();
              String cls= sp_class.getSelectedItem().toString();
               final DatabaseReference myRef1 = database.getReference(uid).child(cls).child("subjects");
               myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       for(DataSnapshot ds:dataSnapshot.getChildren())
                       {
                           String name2 = ds.child("subject").getValue(String.class);

                           spinnerArray2.add(name2);


                       }
                       sp_sub.setAdapter(spinnerArrayAdapter2);
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });

           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

        sdate=(EditText) dialogView.findViewById(R.id.etStartDate);
        final EditText edate=(EditText) dialogView.findViewById(R.id.etEndDate);

        sdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(TimeTableActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        sdate.setText( new StringBuilder().append(selectedHour)
                                .append(":").append(selectedMinute));
                    }
                }, hour, minute,false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
         edate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
             @Override
             public void onFocusChange(View v, boolean hasFocus) {
                 Calendar mcurrentTime = Calendar.getInstance();
                 int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                 int minute = mcurrentTime.get(Calendar.MINUTE);
                 TimePickerDialog mTimePicker;
                 mTimePicker = new TimePickerDialog(TimeTableActivity.this, new TimePickerDialog.OnTimeSetListener() {
                     @Override
                     public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                         edate.setText(new StringBuilder().append(selectedHour)
                                 .append(":").append(selectedMinute));
                     }
                 }, hour, minute,false);
                 mTimePicker.setTitle("Select Time");
                 mTimePicker.show();
             }
         });

        final AlertDialog alertDialog = builder.create();
        Button btnsave = (Button)dialogView.findViewById(R.id.btnSaveDay);
        btnsave.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                alertDialog.dismiss();
            }});

        alertDialog.show();
    }
}
