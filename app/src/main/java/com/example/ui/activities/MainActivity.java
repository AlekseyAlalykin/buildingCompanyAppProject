package com.example.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.activities.R;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activities.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_services, R.id.nav_profile, /*R.id.nav_settings,*/ R.id.nav_support, R.id.nav_orders)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View header = navigationView.getHeaderView(0);

        TextView emailTextView = header.findViewById(R.id.email_text_view);
        TextView phoneTextView = header.findViewById(R.id.phone_text_view);


        Intent intent = getIntent();
        emailTextView.setText(intent.getStringExtra("email"));

        String number = intent.getStringExtra("phone");

        int shift = 0;

        if (number.substring(0,1).equals("+"))
            shift = 1;

        String formattedNumber = number.substring(0, 1 + shift);

        formattedNumber = formattedNumber + " (" + number.substring(1 + shift, 4 + shift) + ") ";
        formattedNumber = formattedNumber + number.substring(4 + shift, 7 + shift) + "-";
        formattedNumber = formattedNumber + number.substring(7 + shift, 9 + shift) + "-";
        formattedNumber = formattedNumber + number.substring(9 + shift, 11 + shift);

        phoneTextView.setText(formattedNumber);

    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}