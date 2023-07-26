package com.example.trinhhnph20554_asm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class ForgotPass extends AppCompatActivity {
        Button btnsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        btnsubmit = findViewById(R.id.btnsubmit);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            diaLogUpdatePass();
            }
        });
    }


    private void diaLogUpdatePass(){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.updatepass_dialog,null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();


        alertDialog.show();
    }
}