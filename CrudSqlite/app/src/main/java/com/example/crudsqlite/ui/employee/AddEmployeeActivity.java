package com.example.crudsqlite.ui.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crudsqlite.MainActivity;
import com.example.crudsqlite.R;
import com.example.crudsqlite.dao.EmployeeDao;
import com.example.crudsqlite.model.Product;

public class AddEmployeeActivity extends AppCompatActivity {
    private Integer empId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        EmployeeDao.setDatabase(getApplicationContext());

        Bundle data = getIntent().getExtras();

        if (data != null) {
            empId = data.getInt("empId");
            String empName = data.getString("empName");
            String empEmail = data.getString("empEmail");
            String empPrice = data.getString("empPrice");
            String empQuantity = data.getString("empQuantity");

            TextView nameView = findViewById(R.id.name_inp);
            TextView emailView = findViewById(R.id.email_inp);
            TextView priceView = findViewById(R.id.price_inp);
            TextView quantityView = findViewById(R.id.quantity_inp);

            nameView.setText(empName);
            emailView.setText(empEmail);
            priceView.setText(empPrice);
            quantityView.setText(empQuantity);
        }

    }

    public void addEmployee(View view) {
        EditText nameTxt = findViewById(R.id.name_inp);
        EditText emailTxt = findViewById(R.id.email_inp);
        String name = nameTxt.getText().toString();
        String email = emailTxt.getText().toString();

        EditText priceTxt = findViewById(R.id.price_inp);
        EditText quantityTxt = findViewById(R.id.quantity_inp);
        String price = priceTxt.getText().toString();
        String quantity = quantityTxt.getText().toString();

        Product employee = new Product(name, email, price, quantity);
        employee.setId(empId);
        boolean result = empId == null ? EmployeeDao.addEmployee(employee) : EmployeeDao.updateEmployee(employee);

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