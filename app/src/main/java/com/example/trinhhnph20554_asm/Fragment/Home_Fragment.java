package com.example.trinhhnph20554_asm.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.trinhhnph20554_asm.Adapter.Adapter_Job;
import com.example.trinhhnph20554_asm.DAO.Job_DAO;
import com.example.trinhhnph20554_asm.Model.Job;
import com.example.trinhhnph20554_asm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Home_Fragment extends Fragment {

    FloatingActionButton floatadd;
    RecyclerView recyclerView;
    ArrayList<Job> list = new ArrayList<>();
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
        list = dao.getlistJob();
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
        btnout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }
}