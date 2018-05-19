package com.example.anujdawar.bus_service_1;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database_All_States extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "Bus_Services.db";
    public static final String TABLE_NAME = "IDs_For_States";
    public static final String COL_1 = "BUS_NO";
    public static final String COL_2 = "ROUTE";

    public Database_All_States(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (BUS_NO TEXT PRIMARY KEY,ROUTE TEXT)");
    }

    public boolean insertData(String bus_number, String route)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, bus_number);
        contentValues.put(COL_2, route);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor viewAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }

    public boolean updateData(String bus_number, String route)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, bus_number);
        contentValues.put(COL_2, route);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {bus_number});

        return true;
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
    }
}