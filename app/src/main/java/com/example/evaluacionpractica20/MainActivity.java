package com.example.evaluacionpractica20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText titulo, desc, autor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titulo = findViewById(R.id.txtTitle);
        desc = findViewById(R.id.txtDescription);
        autor = findViewById(R.id.txtAutor);
    }

    public void saveNote(View view) {

        try {
            ConexionSQLite conexion = new ConexionSQLite(this);
            SQLiteDatabase bd = conexion.getWritableDatabase();
            String t = titulo.getText().toString();
            String d = desc.getText().toString();
            String a = autor.getText().toString();

            ContentValues registro = new ContentValues();
            registro.put("id", (Integer) null);
            registro.put("titulo", t);
            registro.put("descripcion", d);
            registro.put("autor", a);

            int result = (int) bd.insert("tb_bloc", null, registro);
            bd.close();

            if (result > 0) {
                Toast.makeText(this, "Se guardo el registro", Toast.LENGTH_SHORT).show();
                titulo.setText("");
                desc.setText("");
                autor.setText("");
            } else {
                Toast.makeText(this, "No Se guardo el registro", Toast.LENGTH_SHORT).show();
            }



        } catch (Exception e) {
            String msg = e.toString();
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }


    }

    public void change(View view) {
        Intent intent = new Intent(this, registerNotes.class);
        startActivity(intent);
    }
}