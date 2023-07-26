package com.example.trinhhnph20554_asm.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trinhhnph20554_asm.R;


public class Setting_Fragment extends Fragment {
TextView txtnameuser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting_, container, false);
//        txtnameuser = view.findViewById(R.id.txtnameuser);
//
//
//       Bundle bundle = getArguments();
//        if (bundle != null) {
//          String  username = bundle.getString("username");
//            txtnameuser.setText(username);
//        }
        return view;
    }
}