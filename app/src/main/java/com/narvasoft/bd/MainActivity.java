package com.narvasoft.bd;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {
    public MainActivity() {
        super(R.layout.activity_main);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view,SesionFragment.class, null)
                    .commit();
        }*/
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment SesionFragment = null;
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, SesionFragment)
                .setReorderingAllowed(true)
                .commit();


    }
}


