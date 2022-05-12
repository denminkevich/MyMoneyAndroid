package com.example.mymoney.ui.home;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.widget.ImageButton;

import com.example.mymoney.MainActivity;
import com.example.mymoney.R;
import com.example.mymoney.ui.incomes.IncomesFragment;

import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        String[]monthName={"Январь","Февраль","Март", "Апрель", "Май", "Июнь", "Июль",
                "Август", "Сентябрь", "Октябрь", "Ноябрь",
                "Декабрь"};
        String month = monthName[c.get(Calendar.MONTH)];
        int year = c.get(Calendar.YEAR);

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        TextView monthText = root.findViewById(R.id.month);
        TextView yearText = root.findViewById(R.id.year);
        monthText.setText(month);
        yearText.setText(Integer.toString(year));

        ImageButton changeBtn = (ImageButton) root.findViewById(R.id.changeBtn);
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IncomesFragment nextFrag = new IncomesFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }

        });

        ImageView imageOfGroup = (ImageView) root.findViewById(R.id.imageOfGroup);
        TextView textOfGroup = (TextView) root.findViewById(R.id.textOfGroup);
        EditText editText = (EditText) root.findViewById(R.id.expInput);
        LinearLayout floatingWindow = (LinearLayout) root.findViewById(R.id.floatWindow);
        ImageButton healthBtn = (ImageButton) root.findViewById(R.id.healthBut);
        ImageButton goodsBtn = (ImageButton) root.findViewById(R.id.goodsBtn);
        ImageButton restBtn = (ImageButton) root.findViewById(R.id.cafesBtn);
        ImageButton eatBtn = (ImageButton) root.findViewById(R.id.eatBtn);
        ImageButton carBtn = (ImageButton) root.findViewById(R.id.carBtn);
        ImageButton houseBtn = (ImageButton) root.findViewById(R.id.houseBtn);
        ImageButton sportBtn = (ImageButton) root.findViewById(R.id.sportBtn);
        ImageButton hobbiesBtn = (ImageButton) root.findViewById(R.id.hobbiesBtn);
        ImageButton addBtn = (ImageButton) root.findViewById(R.id.addBtn);
        View emptyView = (View) root.findViewById(R.id.emptyView);


        healthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingWindow.setVisibility(View.VISIBLE);
                imageOfGroup.setImageResource(R.drawable.ic_medicon);
                textOfGroup.setText(R.string.health);
            }
        });

        goodsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingWindow.setVisibility(View.VISIBLE);
                imageOfGroup.setImageResource(R.drawable.ic_goods);
                textOfGroup.setText(R.string.goods);
            }
        });

        restBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingWindow.setVisibility(View.VISIBLE);
                imageOfGroup.setImageResource(R.drawable.ic_cafes);
                textOfGroup.setText(R.string.Cafes);
            }
        });

        eatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingWindow.setVisibility(View.VISIBLE);
                imageOfGroup.setImageResource(R.drawable.ic_eat);
                textOfGroup.setText(R.string.Eat);
            }
        });

        carBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingWindow.setVisibility(View.VISIBLE);
                imageOfGroup.setImageResource(R.drawable.ic_car);
                textOfGroup.setText(R.string.car);
            }
        });

        houseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingWindow.setVisibility(View.VISIBLE);
                imageOfGroup.setImageResource(R.drawable.ic_house);
                textOfGroup.setText(R.string.House);
            }
        });

        sportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingWindow.setVisibility(View.VISIBLE);
                imageOfGroup.setImageResource(R.drawable.ic_sport);
                textOfGroup.setText(R.string.Sport);
            }
        });

        hobbiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingWindow.setVisibility(View.VISIBLE);
                imageOfGroup.setImageResource(R.drawable.ic_hobbies);
                textOfGroup.setText(R.string.Hobbies);
            }
        });

        emptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingWindow.setVisibility(View.GONE);
            }
        });

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

}