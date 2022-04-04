package com.example.moreparkk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class password_db extends SQLiteOpenHelper {
    public static final String COL_1 = "id";
    public static final String COL_2 = "email";
    public static final String COL_3 = "password";
    public static final String COL_4 = "username";
    public static final String COL_6 = "id_number";
    public static final String COL_7 = "Phone_number";
    public static final String DATABASE_NAME = "password_fay";
    public static final String TABLE_NAME = "chep";

    public password_db(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT NOT NULL UNIQUE,password TEXT,username TEXT,id_number NUMBER NOT NULL UNIQUE,phone_number NUMBER NOT NULL UNIQUE)");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String email, String password, String username, String id_number, String phone_number ) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, email);
        contentValues.put(COL_3, password);
        contentValues.put(COL_4, username);
        contentValues.put(COL_6, id_number);
        contentValues.put(COL_7, phone_number);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;

    }
    public void updatePassword(String email, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_3, password);
        db.update(TABLE_NAME, values, COL_2+"=?",new  String[]{email});
        db.close();
    }


    // public Cursor login_user(String email, String password) {
    //  return this.getWritableDatabase().rawQuery("select * from users where EMAIL=' " + email + " 'AND PASSWORD=' " + password + " ' ", null);
    //}

    public Cursor login_user(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from"+TABLE_NAME +"where EMAIL='"+email+"'AND PASSWORD='"+password+"'",null);
        return res;

    }




    public Boolean Check_email(String email) {
        if (this.getWritableDatabase().rawQuery("select * from chep where EMAIL = ?", new String[]{email}).getCount() > 0) {
            return true;
         }
        return false;
    }
      //login handler

    public Boolean Check_email_password(String email, String password) {
        if (this.getWritableDatabase().rawQuery("select * from chep where EMAIL = ? and PASSWORD = ?", new String[]{email, password}).getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean checkIfExists(String email) {
        Cursor cursor = this.getReadableDatabase().rawQuery("select EMAIL from chep", null);
        if (cursor.moveToFirst()) {
            do {
                if (!cursor.getString(0).equals((Object)email)) continue;
                return true;
            } while (cursor.moveToNext());
        }
        return false;
    }

    public boolean checkid(String id_number) {
        Cursor cursor = this.getReadableDatabase().rawQuery("select id_number from chep", null);
        if (cursor.moveToFirst()) {
            do {
                if (!cursor.getString(0).equals((Object)id_number)) continue;
                return true;
            } while (cursor.moveToNext());
        }
        return false;
    }

}


