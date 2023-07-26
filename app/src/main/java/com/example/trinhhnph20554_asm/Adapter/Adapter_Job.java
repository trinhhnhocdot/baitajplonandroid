package com.example.trinhhnph20554_asm.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trinhhnph20554_asm.DataBase.Mydatabase;
import com.example.trinhhnph20554_asm.Model.Job;
import com.example.trinhhnph20554_asm.R;

import java.util.ArrayList;

public class Adapter_Job extends RecyclerView.Adapter<Adapter_Job.MyviewHolder> {
    Mydatabase db;
        Context context;
        ArrayList<Job> list;

    public Adapter_Job( Context context, ArrayList<Job> list) {

        this.context = context;
        this.list = list;
        db = new Mydatabase(context);
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_job, parent, false);

        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.txtnamejob.setText(list.get(position).getName());
        holder.txtcontent.setText(list.get(position).getContent());
        holder.txtstartday.setText(list.get(position).getStartday());
        holder.txtendday.setText(list.get(position).getEndday());
        holder.txtstatus.setText(list.get(position).getStatus()+"");
        holder.ivUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_delete();
            }
        });
    }
    private void dialog_delete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setIcon(android.R.drawable.stat_notify_error);
        builder.setMessage("Bạn có muốn xóa?");
        builder.setCancelable(false);//khong cho thoat

        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "xoa thanh cong", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
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
