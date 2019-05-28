package com.example.a19134355_7.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class carsSQLiteHelper extends SQLiteOpenHelper{
    //sentencia sql para crear la tabla cars
    String sqlCreate = "Create table cars (id integer primary key autoincrement not null,nombre text,color text)";

    public carsSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version)
    {
     super(context,name,factory,version);
     this.sqlCreate = sqlCreate;
    }

 @Override
    public void onCreate(SQLiteDatabase db)
 {
     db.execSQL(sqlCreate);
 }
 @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion)
 {
     // se elimina la version anteriro de la tabla
     db.execSQL("DROP TABLE IF EXISTS cars ");

     //se crea la nueva version de la tabla
     db.execSQL(sqlCreate);
 }

}