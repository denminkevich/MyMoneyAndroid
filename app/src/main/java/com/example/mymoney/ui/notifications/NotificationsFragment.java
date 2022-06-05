package com.example.mymoney.ui.notifications;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mymoney.ExpAdapter;
import com.example.mymoney.ExpensesGroup;
import com.example.mymoney.IncomesGroup;
import com.example.mymoney.ListAdapter;
import com.example.mymoney.MainActivity;
import com.example.mymoney.OperatesGroup;
import com.example.mymoney.R;
import com.example.mymoney.monthClass;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        String[]monthName={"Январь","Февраль","Март", "Апрель", "Май", "Июнь", "Июль",
                "Август", "Сентябрь", "Октябрь", "Ноябрь",
                "Декабрь"};
        LocalDate date = LocalDate.now();

        ArrayList<monthClass> monthGroup = ((MainActivity) getActivity()).getMonthGroup();
        ArrayList<ExpensesGroup> expenses = ((MainActivity) getActivity()).getExpList();
        ArrayList<IncomesGroup> incomes = ((MainActivity) getActivity()).getIncList();
        double allExp = 0;
        for (int i = 0; i < expenses.size(); i++) {
            allExp += expenses.get(i).getCount();
        }
        double allInc = 0;
        for (int i = 0; i < incomes.size(); i++) {
            allInc += incomes.get(i).getCount();
        }
        monthGroup.add(new monthClass(date, allInc - allExp, expenses, incomes));

        TextView MonthYear = (TextView) root.findViewById(R.id.monthYear);
        TextView textOfTurnover = (TextView) root.findViewById(R.id.monthSum);
        TextView textOfMonth = (TextView) root.findViewById(R.id.monthText);
        TextView curMonthInc = (TextView) root.findViewById(R.id.curMonthInc);
        TextView curMonthExp = (TextView) root.findViewById(R.id.curMonthExp);
        MonthYear.setText(date.getMonth() +" " + date.getYear());
        textOfTurnover.setText((allInc - allExp) + "");
        if ((allInc - allExp) > 0 ) {
            textOfMonth.setText("Поздравляем Вас с доходом в этом месяце!");
        } else {
            textOfMonth.setText("Постарайтесь в следующем месяце!");
        }
        curMonthInc.setText("" + allInc);
        curMonthExp.setText("" + allExp);

        ListView ExpList = (ListView) root.findViewById(R.id.ExpList);
        ExpAdapter adapter = new ExpAdapter(mContext, R.layout.list_oper_item, monthGroup.get(0).getExpenses());
        ExpList.setAdapter(adapter);


        return root;
    }
}