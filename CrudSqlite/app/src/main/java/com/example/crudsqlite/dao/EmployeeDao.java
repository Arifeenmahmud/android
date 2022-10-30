package com.example.crudsqlite.dao;

import static com.example.crudsqlite.db.DbConstants.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.crudsqlite.db.DbConstants;
import com.example.crudsqlite.db.DbHandler;
import com.example.crudsqlite.model.Product;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private static DbHandler dbHandler;

    public static void setDatabase(Context context) {
        if (dbHandler == null) {
            dbHandler = new DbHandler(context);
        }
    }

    public static boolean addEmployee(Product employee) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, employee.getName());
        values.put(COL_EMAIL, employee.getEmail());
        values.put(COL_PRICE, employee.getPrice());
        values.put(COL_QUANTITY, employee.getQuantity());
        long result = db.insert(TBL_NAME, null, values);
        db.close();
        return result > 0;
    }

    public static boolean updateEmployee(Product employee) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, employee.getName());
        values.put(COL_EMAIL, employee.getEmail());
        values.put(COL_PRICE, employee.getPrice());
        values.put(COL_QUANTITY, employee.getQuantity());
        long result = db.update(TBL_NAME, values, "id = ?" , new String[]{employee.getId() + ""});
        db.close();
        return result > 0;
    }


    public static List<Product> getEmployees() {
        String query = "SELECT * FROM " + TBL_NAME;
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        List<Product> employees = new ArrayList<>(cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String price = cursor.getString(3);
                String quantity = cursor.getString(4);

                Product employee = new Product(name, email, price, quantity);
                employee.setId(cursor.getInt(0));
                employees.add(employee);
            } while (cursor.moveToNext());
        }
        db.close();
        return employees;
    }


    public static boolean deleteEmployee(int id) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        int rowCount = db.delete(TBL_NAME, "id = ?", new String[]{id + ""});
        db.close();
        return rowCount > 0;
    }
}
