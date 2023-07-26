package com.example.trinhhnph20554_asm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trinhhnph20554_asm.DAO.User_DAO;

import java.util.regex.Pattern;

public class Reginter_form extends AppCompatActivity {
    EditText edtuser, edtpass, edtnewpass, edtemail, edtfullname;
    Button btnsubmit;
   private  User_DAO user_dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reginter_form);
        user_dao = new User_DAO(this);
        anhxa();

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtuser.getText().toString();
                String pass = edtpass.getText().toString();
                String repass = edtnewpass.getText().toString();
                String fullname = edtfullname.getText().toString();
                String email = edtemail.getText().toString();
                if (username.equals("")){
                    Toast.makeText(Reginter_form.this, "Xin vui lòng nhập username", Toast.LENGTH_SHORT).show();

                } else if (username.equals(" ")) {
                    Toast.makeText(Reginter_form.this, "tên đăng nhập phải viến liền nhau ", Toast.LENGTH_SHORT).show();

                } else if (pass.equals("")) {
                    Toast.makeText(Reginter_form.this, "không được để troóng mật khẩu ", Toast.LENGTH_SHORT).show();
                }else if (pass.length()<3) {
                    Toast.makeText(Reginter_form.this, "khẩu phải lớn hơn 3 kí tự ", Toast.LENGTH_SHORT).show();

                } else if (fullname.equals("")) {
                    Toast.makeText(Reginter_form.this, "vùi lòng nhập tên của bạn ", Toast.LENGTH_SHORT).show();

                } else if (!isValidEmail(email)) {
                    Toast.makeText(Reginter_form.this, "sai định dạng email", Toast.LENGTH_SHORT).show();
                }else   if (!pass.equals(repass)){
                    Toast.makeText(Reginter_form.this, "mật khẩu không trùng nhau ", Toast.LENGTH_SHORT).show();
                }else {
                        boolean check = user_dao.Register_(username,pass,email,fullname);
                        if (check == true){
                            Toast.makeText(Reginter_form.this, "Đăng kí thành công ", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(Reginter_form.this, "đăng kí thất bại ", Toast.LENGTH_SHORT).show();
                        }
                }


            }
        });
    }


    public boolean isValidEmail(String email) {
        // Định dạng biểu thức chính quy cho địa chỉ email
        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-z]+";

        // Kiểm tra tính hợp lệ của địa chỉ email
        return Pattern.matches(emailPattern, email);
    }

    private void anhxa() {
        btnsubmit = findViewById(R.id.btnsubmit);
        edtuser = findViewById(R.id.edtusername);
        edtpass = findViewById(R.id.edtpass);
        edtfullname = findViewById(R.id.edtFullname);
        edtemail = findViewById(R.id.edtEmail);
        edtnewpass = findViewById(R.id.edtnhaplaimk);
    }
}