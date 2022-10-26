package com.example.crudsqlite.db;

import static com.example.crudsqlite.db.DbConstants.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.crudsqlite.dao.EmployeeDao;

public class DbHandler extends SQLiteOpenHelper {

    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE EMPLOYEE (ID INTEGER PRIMARY KEY, NAME TEXT, EMAIL TEXT)";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
