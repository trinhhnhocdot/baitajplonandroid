package com.example.trinhhnph20554_asm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trinhhnph20554_asm.DataBase.Mydatabase;
import com.example.trinhhnph20554_asm.Model.User;

public class User_DAO {
    private Mydatabase mydatabase;
        SQLiteDatabase db;
    public User_DAO(Context context) {
// login
        mydatabase = new Mydatabase(context);
        db = mydatabase.getWritableDatabase();
    }

    public boolean CheckLogin(User user) {
        SQLiteDatabase sqLiteDatabase = mydatabase.getReadableDatabase();//read sử dụng khi đọc data
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user_tb WHERE username = ? AND pass = ? ", new String[]{user.getUsername(), user.getPassword()});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    // kiểm tra trùng nhau
    public boolean CheckForgotpass(String username, String email) {
        SQLiteDatabase sqLiteDatabase = mydatabase.getReadableDatabase();//read sử dụng khi đọc data
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user_tb WHERE username = ? AND email = ? ", new String[]{username, email});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    // đăng kí

    public boolean Register_(User user) {
        SQLiteDatabase sqLiteDatabase = mydatabase.getWritableDatabase();//sử dụng để ghi như thêm
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("pass", user.getPassword());
        values.put("email", user.getEmail());
        values.put("fullname", user.getFullname());

        long check = sqLiteDatabase.insert("user_tb", null, values);
        // trả ra -1 là lỗi 1 là đúng
        if (check != -1) {
            return true;
        }
        return false;
    }


    // đổi mật khẩu
    // Đổi mật khẩu của người dùng
    public boolean changePassword(String username,String newPassword) {
        SQLiteDatabase db = mydatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pass", newPassword);

        int rowsAffected = db.update("user_tb", values, "username=?", new String[]{username});
        return rowsAffected > 0;
    }

}
