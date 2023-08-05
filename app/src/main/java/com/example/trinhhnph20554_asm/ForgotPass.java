package com.example.trinhhnph20554_asm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trinhhnph20554_asm.DAO.User_DAO;

public class ForgotPass extends AppCompatActivity {
        Button btnsubmit;
        EditText  edt_uername,edt_email;
        User_DAO user_dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        edt_uername = findViewById(R.id.edtusername);
        edt_email = findViewById(R.id.edtEmail);
        btnsubmit = findViewById(R.id.btnsubmit);
        user_dao = new User_DAO(this);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = edt_uername.getText().toString();
                String email =  edt_email.getText().toString();
                boolean check = user_dao.CheckForgotpass(username,email);
            if (check == true){
                Toast.makeText(ForgotPass.this, "vui lòng nhập mật khẩu mới", Toast.LENGTH_SHORT).show();
                diaLogUpdatePass(username);
            }else {
                Toast.makeText(ForgotPass.this, "Username và email không trùng khớp", Toast.LENGTH_SHORT).show();
            }


            }
        });
    }


    private void diaLogUpdatePass( String username){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.updatepass_dialog,null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
            EditText edt_newpass = view.findViewById(R.id.edt_newpass);
            Button btnsubmit = view.findViewById(R.id.btn_submittttt);
            Button btnout = view.findViewById(R.id.btn_outtttttt);

            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newpass = edt_newpass.getText().toString();
                    if (newpass.equals("")){
                        Toast.makeText(ForgotPass.this, "Vui lòng nhập mật khẩu mới ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (user_dao.changePassword(username,newpass)){
                        Toast.makeText(ForgotPass.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgotPass.this,MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(ForgotPass.this, "Không thành công  "+username+"]]"+newpass, Toast.LENGTH_SHORT).show();
                    }

                }
            });

        alertDialog.show();
    }
}