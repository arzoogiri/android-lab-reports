package com.example.multifragmentapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment firstFragment = new FragmentOne();
        Fragment secondFragment = new FragmentTwo();

        fragmentTransaction.add(R.id.fragmentContainerView1, firstFragment);
        fragmentTransaction.add(R.id.fragmentContainerView2, secondFragment);

        fragmentTransaction.commit();
    }
}