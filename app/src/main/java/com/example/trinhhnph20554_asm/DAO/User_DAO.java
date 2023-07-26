package com.example.trinhhnph20554_asm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trinhhnph20554_asm.DataBase.Mydatabase;

public class User_DAO {
    private Mydatabase mydatabase;

    public User_DAO(Context context) {
// login
        mydatabase = new Mydatabase(context);
    }

    public boolean CheckLogin(String username, String pass) {
        SQLiteDatabase sqLiteDatabase = mydatabase.getReadableDatabase();//read sử dụng khi đọc data
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user_tb WHERE username = ? AND pass = ? ", new String[]{username, pass});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    // đăng kí

    public boolean Register_(String username, String pass, String email, String fullname) {
        SQLiteDatabase sqLiteDatabase = mydatabase.getWritableDatabase();//sử dụng để ghi như thêm
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("pass", pass);
        values.put("email", email);
        values.put("fullname", fullname);

        long check = sqLiteDatabase.insert("user_tb", null, values);
        // trả ra -1 là lỗi 1 là đúng
        if (check != -1) {
            return true;
        }
        return false;
    }

}
