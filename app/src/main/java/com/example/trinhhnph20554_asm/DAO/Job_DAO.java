package com.example.trinhhnph20554_asm.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trinhhnph20554_asm.DataBase.Mydatabase;
import com.example.trinhhnph20554_asm.Model.Job;

import java.util.ArrayList;

public class Job_DAO {
    Mydatabase mydatabase;

    public Job_DAO(Context context) {
        mydatabase = new Mydatabase(context);
    }

    // gét list job
    public ArrayList<Job> getlistJob() {
        // select lop.id,ten_lop,ten_khoa,si_so form lop inner join khoa on khoa.id = lop.id_khoa;
        ArrayList<Job> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = mydatabase.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM job_tb", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Job job = new Job();
            job.setId(cursor.getInt(0));
            job.setName(cursor.getString(1));
            job.setContent(cursor.getString(2));
            job.setStatus(cursor.getInt(3));
            job.setStartday(cursor.getString(4));
            job.setEndday(cursor.getString(5));
            list.add(job);
            cursor.moveToNext();
        }
        return list;
    }
}
