package com.example.crudsqlite.ui.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.crudsqlite.MainActivity;
import com.example.crudsqlite.R;
import com.example.crudsqlite.adapter.EmployeeAdapter;
import com.example.crudsqlite.dao.EmployeeDao;
import com.example.crudsqlite.db.DbHandler;
import com.example.crudsqlite.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListEmployeeActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employee);

        EmployeeDao.setDatabase(getApplicationContext());

        List<Product> employees = EmployeeDao.getEmployees();
        listView = findViewById(R.id.emp_list_view);

        EmployeeAdapter adapter = new EmployeeAdapter(this, employees, new DbHandler(this));
        listView.setAdapter(adapter);

    }

    public void goToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}