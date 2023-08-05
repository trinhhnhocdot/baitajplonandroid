package com.example.trinhhnph20554_asm.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trinhhnph20554_asm.DataBase.Mydatabase;
import com.example.trinhhnph20554_asm.Model.Job;
import com.example.trinhhnph20554_asm.Model.Status;

import java.util.ArrayList;
import java.util.List;

public class Job_DAO {
    Mydatabase mydatabase;
    SQLiteDatabase db;

    public Job_DAO(Context context) {
        mydatabase = new Mydatabase(context);
        db = mydatabase.getWritableDatabase();
    }

    public int DeleteRow(Job job) {
        String[] dieukien = new String[]{String.valueOf(job.getId())};
        return db.delete("job_tb", "id=?", dieukien);
    }
    public int Update_job(Job job) {
        ContentValues values = new ContentValues();
        values.put("name", job.getName());
        values.put("content", job.getContent());
        values.put("startday", job.getStartday());
        values.put("endday", job.getEndday());
        values.put("id_status", job.getId_status());
        String[] dieukien = new String[]{String.valueOf(job.getId())};
        return db.update("job_tb", values, "id=?", dieukien);
    }

    public int Add_Row_job(Job job) {
        ContentValues values = new ContentValues();
        values.put("name", job.getName());
        values.put("content", job.getContent());
        values.put("startday", job.getStartday());
        values.put("endday", job.getEndday());
        values.put("id_status", job.getId_status());
        return (int) db.insert("job_tb", null, values);
    }


    // Hiển thị danh sách công việc
    public List<Job> GetAll_Job() {
        List<Job> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("" +
                        "SELECT job_tb.id,name,content,startday," +
                        "endday,id_status,status FROM job_tb INNER JOIN" +
                        " status_tb ON status_tb.id = job_tb.id_status;"
                , null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id_job = cursor.getInt(0);
                String name_job = cursor.getString(1);
                String content_job = cursor.getString(2);
                String startday_job = cursor.getString(3);
                String endday_job = cursor.getString(4);
                int id_status = cursor.getInt(5);
                String name_status = cursor.getString(6);
                Job job = new Job(id_job, name_job, content_job, startday_job, endday_job, id_status, name_status);
                list.add(job);
                cursor.moveToNext();
            }
        }
        return list;
    }
    // Hiển thị danh sách Status
    public List<Status> GetAll_Status() {
        List<Status> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM status_tb", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int id_status = cursor.getInt(0);
                String status_name = cursor.getString(1);
                Status status = new Status(id_status, status_name);
                list.add(status);
                cursor.moveToNext();
            }
        }
        return list;
    }
}
