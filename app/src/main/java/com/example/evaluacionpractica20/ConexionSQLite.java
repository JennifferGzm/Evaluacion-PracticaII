package com.example.evaluacionpractica20;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ConexionSQLite extends SQLiteOpenHelper {
    public ConexionSQLite(@Nullable Context context) {
        super(context, "EvalblocNotas.db", null, 1);
    }

    /*public SQLiteDatabase bd() {
        SQLiteDatabase bd = this.getWritableDatabase();
        return bd;
    }*/

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table tb_bloc(id integer not null primary key autoincrement, titulo text, descripcion text, autor text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists tb_bloc");
        onCreate(sqLiteDatabase);
    }





}