package com.example.crudsqlite.ui.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudsqlite.MainActivity;
import com.example.crudsqlite.R;
import com.example.crudsqlite.dao.EmployeeDao;
import com.example.crudsqlite.model.Employee;

public class AddEmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        EmployeeDao.setDatabase(getApplicationContext());
    }

    public void addEmployee(View view) {
        EditText nameTxt = findViewById(R.id.name_inp);
        EditText emailTxt = findViewById(R.id.email_inp);
        String name = nameTxt.getText().toString();
        String email = emailTxt.getText().toString();

        Employee employee = new Employee(name, email);
        boolean result = EmployeeDao.addEmployee(employee);

        String message = result ? "Successful" : "Failed";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToHome(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}