package com.example.mymoney;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.mymoney.ui.home.HomeFragment;
import com.example.mymoney.ui.incomes.IncomesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.widget.ImageButton;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ExpensesGroup> expenseGroup = new ArrayList<>();
    ArrayList<IncomesGroup> incomesGroup = new ArrayList<>();
    public ArrayList<ExpensesGroup> getExpList(){
        return expenseGroup;
    }
    public ArrayList<IncomesGroup> getIncList(){
        return incomesGroup;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_history, R.id.navigation_calendar, R.id.navigation_cards)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


    }

//    }

}

