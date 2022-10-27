package com.example.crudsqlite.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crudsqlite.R;
import com.example.crudsqlite.dao.EmployeeDao;
import com.example.crudsqlite.db.DbHandler;
import com.example.crudsqlite.model.Employee;
import com.example.crudsqlite.ui.employee.AddEmployeeActivity;

import java.util.List;

public class EmployeeAdapter extends BaseAdapter {

    private Context context;
    private List<Employee> employees;

    private LayoutInflater inflater;
    private DbHandler db;



    public EmployeeAdapter(Context context, List<Employee> employeeList, DbHandler db) {
        this.context = context;
        employees = employeeList;
        this.db = db;
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

        if (view == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.emp_list_item, viewGroup, false);
        }

        TextView nameText = view.findViewById(R.id.emp_list_name);
        TextView emailText = view.findViewById(R.id.emp_list_email);
        ImageView editBtn = view.findViewById(R.id.emp_edit_btn);
        ImageView delBtn = view.findViewById(R.id.emp_del_btn);


        Employee employee = employees.get(i);
        nameText.setText(employee.getName());
        emailText.setText(employee.getEmail());

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddEmployeeActivity.class);
                intent.putExtra("empId", employee.getId());
                intent.putExtra("empName", employee.getName());
                intent.putExtra("empEmail", employee.getEmail());
                context.startActivity(intent);
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleted = EmployeeDao.deleteEmployee(employee.getId());
                if(deleted) {
                    employees.remove(i);
                    notifyDataSetChanged();
                }
                String message = deleted ? "Successfully deleted" : "Failed to delete";
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
