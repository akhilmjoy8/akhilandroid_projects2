package com.example.acer.firebase1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText name,phone;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final DatabaseReference myRef = database.getReference("teacher");

//        DatabaseReference newRef = myRef.child("Key_of_root_node").child("remarks_parent_node").child("remarks").push();
        final DatabaseReference stud = myRef.child("class").child("students");
        final  DatabaseReference cls =myRef.child("class");
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        Button btn=(Button) findViewById(R.id.button2);
        Button btn1=(Button) findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString().trim();
                String phone1 = phone.getText().toString();

                //checking if the value is provided
                if (!TextUtils.isEmpty(name1)) {

                    //getting a unique id using push().getKey() method
                    //it will create a unique id and we will use it as the Primary Key for our Artist
                    String id = stud.push().getKey();

                    //creating an Artist Object
                    User artist = new User(id, name1, phone1);

                    //Saving the Artist
                    stud.child(name1).setValue(artist);

                    //setting edittext to blank again
                    name.setText("");
                    phone.setText("");

                    //displaying a success toast
                    // Toast.makeText(this, "Artist added", Toast.LENGTH_LONG).show();
                }
            }
        });
btn1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent in= new Intent( MainActivity.this,viewDetails.class);
        startActivity(in);
    }
});
      
    }

}
