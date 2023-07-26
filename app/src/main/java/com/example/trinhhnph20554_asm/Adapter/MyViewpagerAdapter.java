package com.example.trinhhnph20554_asm.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.trinhhnph20554_asm.Fragment.Home_Fragment;
import com.example.trinhhnph20554_asm.Fragment.Info_Fragment;
import com.example.trinhhnph20554_asm.Fragment.Setting_Fragment;

import java.util.Set;

public class MyViewpagerAdapter extends FragmentStateAdapter {
    public MyViewpagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position){
           case 0:
               return new Home_Fragment();
           case 1:
               return new Info_Fragment();
           case 2:
               return new Setting_Fragment();
           default:
               return new Home_Fragment();
       }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
