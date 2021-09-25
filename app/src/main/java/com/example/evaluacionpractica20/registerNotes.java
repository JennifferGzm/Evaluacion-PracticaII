package com.example.evaluacionpractica20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class registerNotes extends AppCompatActivity {
    public ListView list;
    public TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_notes);
        list = findViewById(R.id.notesList);
        text = findViewById(R.id.texView);

        ArrayList<String> valor = new ArrayList<>();


        com.example.evaluacionpractica20.ConexionSQLite conexion = new com.example.evaluacionpractica20.ConexionSQLite(this);
        SQLiteDatabase bd = conexion.getWritableDatabase();

        Cursor fila = bd.rawQuery("select titulo from tb_bloc", null);
        if (fila.moveToFirst()) {
            do {
                valor.add(fila.getString(0));
            } while (fila.moveToNext());
        }
        bd.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, valor);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(view.getContext(), update.class);
                //String t = valor.get(position);
                String t = (String) list.getItemAtPosition(position);

                intent.putExtra("valorTitle", t);
                startActivity(intent);
            }
        });

    }
}
