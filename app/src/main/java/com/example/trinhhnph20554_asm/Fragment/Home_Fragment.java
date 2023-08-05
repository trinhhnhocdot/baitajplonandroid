package com.example.trinhhnph20554_asm.Fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trinhhnph20554_asm.Adapter.Adapter_Job;
import com.example.trinhhnph20554_asm.DAO.Job_DAO;
import com.example.trinhhnph20554_asm.Model.Job;
import com.example.trinhhnph20554_asm.Model.Status;
import com.example.trinhhnph20554_asm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Home_Fragment extends Fragment {

    FloatingActionButton floatadd;
    RecyclerView recyclerView;
    List<Job> list = new ArrayList<>();
    Adapter_Job adapter_job;
    Job_DAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_, container, false);
        floatadd = view.findViewById(R.id.floatAdd);
        recyclerView = view.findViewById(R.id.recyclerView__);


        dao = new Job_DAO(getContext());
        list = dao.GetAll_Job();
        adapter_job = new Adapter_Job(getContext(), list);
        recyclerView.setAdapter(adapter_job);

        floatadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_dialog();
            }
        });

        return view;
    }


    void add_dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.add_dialog, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        Button btnout = view.findViewById(R.id.btnout);
        Button btnsubmit = view.findViewById(R.id.btnsubmit);
        EditText edt_title = view.findViewById(R.id.edt_title);
        EditText edt_content = view.findViewById(R.id.edt_content);
        TextView edt_startday = view.findViewById(R.id.edt_startday);
        TextView edt_endday = view.findViewById(R.id.edt_endday);
        Spinner spinner = view.findViewById(R.id.spiner_status);
        // hiển thị lên spinner
            Job_DAO job_dao = new Job_DAO(getContext());
        List<Status> liststatus = job_dao.GetAll_Status();
        ArrayAdapter<Status> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, liststatus);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(arrayAdapter);
        edt_startday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(edt_startday);
            }
        });
        edt_endday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(edt_endday);
            }
        });
        btnout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edt_title.getText().toString();
                String content = edt_content.getText().toString();
                String startday = edt_startday.getText().toString();
                String endday = edt_endday.getText().toString();
                // lấy giá trị spinner
                Status status = (Status) spinner.getSelectedItem();

                if (title.equals("")) {
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }else if (content.equals("")) {
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }else if (startday.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng chọn ngày bắt đầu ", Toast.LENGTH_SHORT).show();
                    return;
                }else if (endday.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng chọn ngày kết thúc", Toast.LENGTH_SHORT).show();
                    return;
                }
                Job job = new Job();
                job.setName(title);
                job.setContent(content);
                job.setStartday(startday);
                job.setEndday(endday);
                job.setId_status(1);
                long kq = dao.Add_Row_job(job);
                if (kq>0){
                    Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(dao.GetAll_Job());
                    adapter_job.notifyDataSetChanged();
                    alertDialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alertDialog.show();
    }

    private void showDatePicker(TextView edt_startday) {
        final Calendar currentDate = Calendar.getInstance();
        int year = currentDate.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog =new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                edt_startday.setText(selectedDate);
                edt_startday.setTextColor(Color.BLACK);
            }
        },year,month,day);
        datePickerDialog.show();
    }
}