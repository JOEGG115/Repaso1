package com.example.repaso1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.sql.SQLDataException;

public class MainActivity extends AppCompatActivity {
    Conexion c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c=new Conexion(this, "DBEscolar",null,1);
    }
    public void Insertar(View v){
        SQLiteDatabase op=c.getWritableDatabase();
        op.execSQL("INSERT INTO alumno(nc,nombre) VALUES(2,'Pedro')");
        op.close();
        Toast.makeText(this, "Alumno registrado", Toast.LENGTH_SHORT).show();
    }
    public void Consultar(View V){
        SQLiteDatabase op=c.getReadableDatabase();
        Cursor cursor=op.rawQuery("SELECT * FROM alumno",null);
        String resultados="";
        if (cursor.moveToFirst())
        {
            do {
                resultados+=cursor.getString(0)+"---"+cursor.getString(1)+"-";
            }
            while(cursor.moveToNext());
            Toast.makeText(this,resultados, Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        op.close();
    }
    public void eliminar(View v){
        SQLiteDatabase op=c.getWritableDatabase();
        op.execSQL("DELETE FROM alumno WHERE nombre='covid'");
        op.close();
        Toast.makeText(this, "Alumno eliminado", Toast.LENGTH_SHORT).show();
    }

}