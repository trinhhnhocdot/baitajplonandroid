package com.example.trinhhnph20554_asm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trinhhnph20554_asm.DAO.User_DAO;
import com.example.trinhhnph20554_asm.Fragment.Setting_Fragment;

public class MainActivity extends AppCompatActivity {
        TextView btnsignupnow,btnforgot;
        EditText edtusername,edtpass;
        Button btnlogin;

     private    User_DAO user_dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_dao = new User_DAO(this);
        anhxa();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = edtusername.getText().toString();
                String pass =  edtpass.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                Setting_Fragment fragment = new Setting_Fragment();
                fragment.setArguments(bundle);

                if (username.equals("")){
                    Toast.makeText(MainActivity.this, "Mời nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
                    return;
                } else if (pass.equals("")) {
                    Toast.makeText(MainActivity.this, "Xin vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean check = user_dao.CheckLogin(username,pass);

            if (check==true){
                Toast.makeText(MainActivity.this, "Đăng nhập thành công ", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this,ContainerPro.class);
                startActivity(intent);
            }else {
                Toast.makeText(MainActivity.this, "Đăng nhập thất bại vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
            }

            }
        });

        btnsignupnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Reginter_form.class);
                startActivity(intent);
            }
        });

        btnforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ForgotPass.class);
                startActivity(intent);
            }
        });


    }
    private void anhxa(){
        btnforgot = findViewById(R.id.btnquenmk);
        btnlogin = findViewById(R.id.btnlogin);
        btnsignupnow = findViewById(R.id.btnSignupnow);
        edtusername = findViewById(R.id.edtusername);
        edtpass = findViewById(R.id.edtpassword);
    }
}