package com.example.trinhhnph20554_asm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.trinhhnph20554_asm.Adapter.MyViewpagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContainerPro extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_pro);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager2 = findViewById(R.id.viewpager2);
        MyViewpagerAdapter myViewpagerAdapter = new MyViewpagerAdapter(this);
        viewPager2.setAdapter(myViewpagerAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.nav_info).setChecked(true);
                        break;

                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.nav_acount).setChecked(true);
                        break;

                }
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    viewPager2.setCurrentItem(0);
                } else if (id == R.id.nav_info) {
                    viewPager2.setCurrentItem(1);
                } else if (id == R.id.nav_acount) {
                    viewPager2.setCurrentItem(2);
                }else {
                    viewPager2.setCurrentItem(3);
                }
                return true;
            }
        });
    }
}