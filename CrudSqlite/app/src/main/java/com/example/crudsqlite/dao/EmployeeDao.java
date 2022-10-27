package com.example.crudsqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.crudsqlite.db.DbHandler;
import com.example.crudsqlite.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private static DbHandler dbHandler;

    public static void setDatabase(Context context) {
        if (dbHandler == null) {
            dbHandler = new DbHandler(context);
        }
    }

    public static boolean addEmployee(Employee employee) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME", employee.getName());
        values.put("EMAIL", employee.getEmail());
        long result = db.insert("EMPLOYEE", null, values);
        db.close();
        return result > 0;
    }

    public static boolean updateEmployee(Employee employee) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME", employee.getName());
        values.put("EMAIL", employee.getEmail());
        long result = db.update("EMPLOYEE", values, "id = ?" , new String[]{employee.getId() + ""});
        db.close();
        return result > 0;
    }


    public static List<Employee> getEmployees() {
        String query = "SELECT * FROM EMPLOYEE";
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        List<Employee> employees = new ArrayList<>(cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee(cursor.getString(1), cursor.getString(2));
                employee.setId(cursor.getInt(0));
                employees.add(employee);
            } while (cursor.moveToNext());
        }
        db.close();
        return employees;
    }


    public static boolean deleteEmployee(int id) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        int rowCount = db.delete("EMPLOYEE", "id = ?", new String[]{id + ""});
        db.close();
        return rowCount > 0;
    }
}
