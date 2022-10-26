package com.example.crudsqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.crudsqlite.R;
import com.example.crudsqlite.model.Employee;

import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

public class EmployeeAdapter extends BaseAdapter {

    private Context context;
    private List<Employee> employees;

    private LayoutInflater inflater;


    public EmployeeAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        employees = employeeList;
    }


    @Override

    public int getCount() {
        return employees != null ? employees.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.emp_list_item, viewGroup, false);
        }

        TextView nameText = view.findViewById(R.id.emp_list_name);
        TextView emailText = view.findViewById(R.id.emp_list_email);

        Employee employee = employees.get(i);
        nameText.setText(employee.getName());
        emailText.setText(employee.getEmail());

        return view;
    }
}
