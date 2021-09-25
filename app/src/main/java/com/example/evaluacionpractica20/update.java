package com.example.evaluacionpractica20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class update extends AppCompatActivity {

    public String x;
    public EditText v;
    public EditText titulo, desc, autor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        titulo = findViewById(R.id.txtTitle);
        desc = findViewById(R.id.txtDescription);
        autor = findViewById(R.id.txtAutor);

        Bundle bundle = new Bundle();

        String dato = getIntent().getStringExtra("valorTitle");

        v = findViewById(R.id.txtTitle);
        v.setText(dato);

        x = v.getText().toString();


        try {

            ConexionSQLite conexion = new ConexionSQLite(this);
            SQLiteDatabase bd = conexion.getWritableDatabase();

            Cursor fila = bd.rawQuery("select descripcion, autor from tb_bloc where titulo = '" + x + "'"  , null);
            if(fila.moveToFirst()) {
                desc.setText(fila.getString(0));
                autor.setText(fila.getString(1));
            }


        } catch (Exception e) {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }


    }


    public void update(View view) {
        ConexionSQLite conexion = new ConexionSQLite(this);
        SQLiteDatabase bd = conexion.getWritableDatabase();
        String t = titulo.getText().toString();
        String d = desc.getText().toString();
        String a = autor.getText().toString();


        ContentValues registro = new ContentValues();
        registro.put("titulo", t);
        registro.put("descripcion", d);
        registro.put("autor", a);

        int cant = bd.update("tb_bloc", registro, "titulo = '" + x + "'", null);
        bd.close();
        if (cant == 1) {
            Toast.makeText(this, "Note Upgrade", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not Upgrade", Toast.LENGTH_SHORT).show();
        }
    }


    public void deleteNote(View view) {
        ConexionSQLite conexion = new ConexionSQLite(this);
        SQLiteDatabase bd = conexion.getWritableDatabase();
        String t = titulo.getText().toString();
        int cant = bd.delete("tb_bloc", "titulo = '" + x + "'", null);
        bd.close();
        titulo.setText("");
        desc.setText("");
        autor.setText("");

        Toast.makeText(this, "Note Delete", Toast.LENGTH_SHORT).show();
    }


}
