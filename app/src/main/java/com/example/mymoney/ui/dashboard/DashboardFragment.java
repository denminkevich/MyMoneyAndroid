package com.example.mymoney.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mymoney.ExpensesGroup;
import com.example.mymoney.ListAdapter;
import com.example.mymoney.MainActivity;
import com.example.mymoney.OperatesGroup;
import com.example.mymoney.R;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history, container, false);

        ArrayList<OperatesGroup> operGroup = ((MainActivity) getActivity()).getOperList();

        ListView recipeList = (ListView) root.findViewById(R.id.OperList);
        ListAdapter adapter = new ListAdapter(mContext, R.layout.list_oper_item, operGroup);
        recipeList.setAdapter(adapter);

        return root;
    }
}