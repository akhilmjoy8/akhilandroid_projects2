package com.example.acer.teacher;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelp extends SQLiteOpenHelper {

    /*data base*/
    public static final  String DB_NAME="Attendance";
    public static final  int VERSION_NO=1;
    public static final String TABLE_STUDENT="StudentDetails";


    /* Student profile table */


    public static final String FNAME="student_fname";
    public static final String ID="student_id";



    public dbHelp(Context context) {
        super(context, DB_NAME, null, VERSION_NO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        /* creating tables */


        String PROFILE_TABLE = "CREATE TABLE "+ TABLE_STUDENT +" ("+ FNAME +" TEXT NOT NULL,"+ ID +" TEXT NOT NULL)";
        db.execSQL(PROFILE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//    // update stream and branch names
//    public void updateStream(String stream_name){
//        SQLiteDatabase db = this.getReadableDatabase();
//        String update = "UPDATE "+TABLE_STUDENT+ " SET "+STREAM+" = '"+stream_name+"'";
//        db.execSQL(update);
//    }
//
public void deleteStudentTableContent(String id){
    SQLiteDatabase db = this.getReadableDatabase();
    String delete = "DELETE FROM "+TABLE_STUDENT+" WHERE "+ID+" = '"+id+"'";
    db.execSQL(delete);
}
   public void deleteStudentAll(){
      SQLiteDatabase db = this.getReadableDatabase();
       String delete = "DELETE FROM "+TABLE_STUDENT;
        db.execSQL(delete);
 }
        public Cursor getStudentDetails() {

            SQLiteDatabase db=this.getWritableDatabase();
            Cursor data = db.rawQuery("select * from "+TABLE_STUDENT, null);
            return data;
        }
}
