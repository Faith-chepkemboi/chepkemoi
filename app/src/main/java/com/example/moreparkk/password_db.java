package com.example.moreparkk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class password_db extends SQLiteOpenHelper {
    public static final String COL_1 = "id";
    public static final String COL_2 = "EMAIL";
    public static final String COL_3 = "PASSWORD";
    public static final String COL_4 = "USERNAME";
    public static final String COL_5 = "P_NUMBER";
    public static final String COL_6 = "Id_NUMBER";
    public static final String DATABASE_NAME = "password_db";
    public static final String TABLE_NAME = "users";

    public password_db(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(id INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT NOT NULL UNIQUE,PASSWORD TEXT,USERNAME TEXT,P_NUMBER TEXT NOT NULL UNIQUE,Id_NUMBER NUMBER NOT NULL UNIQUE)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int n, int n2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        this.onCreate(sQLiteDatabase);
    }
    public boolean insertData(String email, String password, String username, String pnumber, String id_number) {
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, email);
        contentValues.put(COL_3, password);
        contentValues.put(COL_4, username);
        contentValues.put(COL_5, pnumber);
        contentValues.put(COL_6, id_number);
        return sQLiteDatabase.insert(TABLE_NAME, null, contentValues)
                != -1L;
    }

    public Boolean Check_email(String email) {
        if (this.getWritableDatabase().rawQuery("select * from users where EMAIL = ?", new String[]{email}).getCount() > 0) {
            return true;
        }
        return false;
    }

    public Boolean Check_email_password(String string2, String string3) {
        if (this.getWritableDatabase().rawQuery("select * from users where EMAIL = ? and PASSWORD = ?", new String[]{string2, string3}).getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean checkIfExists(String string2) {
        Cursor cursor = this.getReadableDatabase().rawQuery("select EMAIL from users", null);
        if (cursor.moveToFirst()) {
            do {
                if (!cursor.getString(0).equals((Object)string2)) continue;
                return true;
            } while (cursor.moveToNext());
        }
        return false;
    }

    public boolean checkid(String email) {
        Cursor cursor = this.getReadableDatabase().rawQuery("select EMAIL from users", null);
        if (cursor.moveToFirst()) {
            do {
                if (!cursor.getString(0).equals((Object)email)) continue;
                return true;
            } while (cursor.moveToNext());
        }
        return false;
    }



    public Cursor login_user(String string2, String string3) {
        return this.getWritableDatabase().rawQuery("select * from users where EMAIL=' " + string2 + " 'AND PASSWORD=' " + string3 + " ' ", null);
    }




}


