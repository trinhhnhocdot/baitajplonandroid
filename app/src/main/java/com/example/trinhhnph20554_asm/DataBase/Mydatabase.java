package com.example.trinhhnph20554_asm.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Mydatabase extends SQLiteOpenHelper {
    public Mydatabase(Context context) {
        super(context, "quanlicongviec", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String user_tb = "CREATE TABLE user_tb(username TEXT PRIMARY KEY,pass text,email TEXT,fullname TEXT )";
        db.execSQL(user_tb);
        String job_tb = "CREATE TABLE job_tb(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,content TEXT,status INTEGER,startday TEXT,endday TEXT)";
        db.execSQL(job_tb);

        String data_user = "INSERT INTO user_tb  VALUES ('trinhho','trinh123','trinhhn@gmail.com','ho nguyen trinh')," +
                "('trinh','123','trinhhn@gmail.com','hồ nguyễn trình đẹp trai')";
        String data_job = "INSERT INTO job_tb  VALUES (1,'Task 1', 'Content 1', 0, '2023-07-01', '2023-07-05')";
        String data_job2 = "INSERT INTO job_tb  VALUES (2,'Task 2', 'Content 2', 1, '2023-07-01', '2023-07-05')";

        db.execSQL(data_job);
        db.execSQL(data_job2);
        db.execSQL(data_user);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE EXITST user_tb");
            db.execSQL("DROP TABLE EXITST job_tb");
            onCreate(db);
        }
    }
}
