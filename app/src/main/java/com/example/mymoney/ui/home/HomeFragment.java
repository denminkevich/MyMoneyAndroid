package com.example.mymoney.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mymoney.GridAdapter;
import com.example.mymoney.ExpensesGroup;
import com.example.mymoney.MainActivity;
import com.example.mymoney.R;
import com.example.mymoney.ui.incomes.IncomesFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.O)
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

        ArrayList<ExpensesGroup> expGroup = ((MainActivity) getActivity()).getExpList();
        if (expGroup.size() == 0) {
            expGroup.add(new ExpensesGroup("Здоровье", 0, R.drawable.ic_medicon));
            expGroup.add(new ExpensesGroup("Покупки", 0, R.drawable.ic_goods));
            expGroup.add(new ExpensesGroup("Рестораны", 0, R.drawable.ic_cafes));
            expGroup.add(new ExpensesGroup("Еда", 0, R.drawable.ic_eat));
            expGroup.add(new ExpensesGroup("Машина", 0, R.drawable.ic_car));
            expGroup.add(new ExpensesGroup("Дом", 0, R.drawable.ic_house));
            expGroup.add(new ExpensesGroup("Спорт", 0, R.drawable.ic_sport));
            expGroup.add(new ExpensesGroup("Досуг", 0, R.drawable.ic_hobbies));
        }
        GridView myGrid = (GridView) root.findViewById(R.id.myGrid);
        ImageView imageOfGroup = (ImageView) root.findViewById(R.id.imageOfGroup);
        TextView textOfGroup = (TextView) root.findViewById(R.id.textOfGroup);
        EditText editText = (EditText) root.findViewById(R.id.expInput);
        LinearLayout floatingWindow = (LinearLayout) root.findViewById(R.id.floatWindow);
        View emptyView = (View) root.findViewById(R.id.emptyView);
        myGrid.setAdapter(new GridAdapter(mContext, expGroup));

        // When the user clicks on the GridItem
        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = myGrid.getItemAtPosition(position);
                ExpensesGroup ExpGroup = (ExpensesGroup) o;
                floatingWindow.setVisibility(View.VISIBLE);
                imageOfGroup.setImageResource(ExpGroup.getImg());
                textOfGroup.setText(ExpGroup.getName());
                myGrid.setVisibility(View.GONE);
            }
        });

        emptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingWindow.setVisibility(View.GONE);
                myGrid.setVisibility(View.VISIBLE);
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