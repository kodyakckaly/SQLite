package com.example.kodya_000.sqlite;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by kodya_000 on 16/02/2016.
 */

public class MainActi extends AppCompatActivity {

    private EditText et1,et2,et3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        et3=(EditText)findViewById(R.id.editText3);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void altaProductos(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        String descri = et2.getText().toString();
        String pre = et3.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo", cod);
        registro.put("descripcion", descri);
        registro.put("precio", pre);
        bd.insert("articulos", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        Toast toast1 = Toast.makeText(getApplicationContext(),
                "ALTA DE PRODUCTOS EXITOSA! c:", Toast.LENGTH_SHORT);
        toast1.show();
    }

    public void searchID(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select descripcion, precio from articulos where codigo=" + cod, null);
        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "Busqueda exitosa :3", Toast.LENGTH_SHORT);
            toast1.show();
        } else{
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "No se encontro el producto. :c", Toast.LENGTH_SHORT);
        toast1.show();
        }
        bd.close();
    }

    public void searchDescription(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String descri = et2.getText().toString();
        Cursor fila = bd.rawQuery(
                "select codigo, precio from articulos where descripcion='" + descri + "'", null);
        if (fila.moveToFirst()) {
            et1.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "Busqueda exitosa :3", Toast.LENGTH_SHORT);
            toast1.show();
        } else {
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "No se encontro el producto. :c", Toast.LENGTH_SHORT);
            toast1.show();
        }
        bd.close();
    }

    public void bajaProductos(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod= et1.getText().toString();
        int cant = bd.delete("articulos", "codigo=" + cod, null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        if (cant == 1) {
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "PRODUCTO ELIMINADO!", Toast.LENGTH_SHORT);
            toast1.show();
        } else {
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "ERROR . No se encontró el producto.", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }

    public void updateProducts(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        String descri = et2.getText().toString();
        String pre = et3.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo", cod);
        registro.put("descripcion", descri);
        registro.put("precio", pre);
        int cant = bd.update("articulos", registro, "codigo=" + cod, null);
        bd.close();
        if (cant == 1) {
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "ACTUALIZANDO ... ", Toast.LENGTH_SHORT);
            toast1.show();
        } else {
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "ERROR . No se encontró el producto.", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }

}
