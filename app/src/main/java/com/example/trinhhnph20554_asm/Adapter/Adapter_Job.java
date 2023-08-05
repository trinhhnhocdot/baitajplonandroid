package com.example.trinhhnph20554_asm.Adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trinhhnph20554_asm.DAO.Job_DAO;
import com.example.trinhhnph20554_asm.DataBase.Mydatabase;
import com.example.trinhhnph20554_asm.Model.Job;
import com.example.trinhhnph20554_asm.Model.Status;
import com.example.trinhhnph20554_asm.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Adapter_Job extends RecyclerView.Adapter<Adapter_Job.MyviewHolder> {
        Context context;
        List<Job> list;

    public Adapter_Job(Context context, List<Job> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_job, parent, false);

        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
//        LopDTO lopDTO = list.get(position);
        Job job = list.get(position);
        holder.txtnamejob.setText(job.getName());
        holder.txtcontent.setText(job.getContent());
        holder.txtstartday.setText(job.getStartday());
        holder.txtendday.setText(job.getEndday());
        holder.txtstatus.setText(job.getName_status());


        holder.ivUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showdialogSua(job);
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_delete(job);
            }
        });
    }

    private void showdialogSua(Job job) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.edit_dialog, null);
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
        Job_DAO job_dao = new Job_DAO(context);
        List<Status> liststatus = job_dao.GetAll_Status();
        ArrayAdapter<Status> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, liststatus);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(arrayAdapter);

        edt_title.setText(job.getName());
        edt_content.setText(job.getContent());
        edt_startday.setText(job.getStartday());
        edt_endday.setText(job.getEndday());
        // hiển thị lên spinner

        for (int i=0;i<liststatus.size();i++){
            if (liststatus.get(i).getId_status()==job.getId_status()){
                spinner.setSelection(i);
            }
        }

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
                    Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }else if (content.equals("")) {
                    Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }else if (startday.equals("")) {
                    Toast.makeText(context, "Vui lòng chọn ngày bắt đầu ", Toast.LENGTH_SHORT).show();
                    return;
                }else if (endday.equals("")) {
                    Toast.makeText(context, "Vui lòng chọn ngày kết thúc", Toast.LENGTH_SHORT).show();
                    return;
                }
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
                Job_DAO dao = new Job_DAO(context);
                job.setName(title);
                job.setContent(content);
                job.setStartday(startday);
                job.setEndday(endday);
                job.setId_status(status.getId_status());

                long kq = dao.Update_job(job);
                if (kq>0){
                    Toast.makeText(context, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(dao.GetAll_Job());
                    notifyDataSetChanged();
                    alertDialog.dismiss();
                }else{
                    Toast.makeText(context, "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
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
        DatePickerDialog datePickerDialog =new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                edt_startday.setText(selectedDate);
                edt_startday.setTextColor(Color.BLACK);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    private void dialog_delete(Job job){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setIcon(android.R.drawable.stat_notify_error);
        builder.setMessage("Bạn có muốn xóa "+ job.getName() +" ?");
        builder.setCancelable(false);//khong cho thoat

        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Job_DAO dao = new Job_DAO(context);
                int kq = dao.DeleteRow(job);
                if (kq>0){
                    list.clear();
                    list.addAll(dao.GetAll_Job());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{
    private TextView txtnamejob,txtcontent,txtstartday,txtendday,txtstatus;

        ImageView ivUpdate,ivDelete;
    public MyviewHolder(@NonNull View itemView) {
        super(itemView);
        ivDelete = itemView.findViewById(R.id.ivdelete);
        ivUpdate = itemView.findViewById(R.id.ivupdate);
        txtnamejob =itemView.findViewById(R.id.txtnamejob);
        txtcontent =itemView.findViewById(R.id.txtcontent);
        txtstartday =itemView.findViewById(R.id.txtstartday);
        txtendday =itemView.findViewById(R.id.txtendday);
        txtstatus =itemView.findViewById(R.id.txtstatus);
    }
}
}
