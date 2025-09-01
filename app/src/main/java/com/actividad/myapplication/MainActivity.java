package com.actividad.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CheckBox producto1;
    CheckBox producto2;
    CheckBox producto3;
    CheckBox producto4;
    CheckBox producto5;

    EditText cantidadProd1,cantidadProd2, cantidadProd3, cantidadProd4, cantidadProd5 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        producto1 = findViewById(R.id.chkItem1);
        producto2 = findViewById(R.id.chkItem2);
        producto3 = findViewById(R.id.chkItem3);
        producto4 = findViewById(R.id.chkItem4);
        producto5 = findViewById(R.id.chkItem5);

        cantidadProd1 = findViewById(R.id.cantidadProd1);
        cantidadProd2 = findViewById(R.id.cantidadProd2);
        cantidadProd3 = findViewById(R.id.cantidadProd3);
        cantidadProd4 = findViewById(R.id.cantidadProd4);
        cantidadProd5 = findViewById(R.id.cantidadProd5);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public int calcularPrecio(){
        int total = 0;

        if(producto1.isChecked()){
            total += 1000 * Integer.parseInt(cantidadProd1.getText().toString());
        }
        if(producto2.isChecked()){
            total += 2500 * Integer.parseInt(cantidadProd2.getText().toString());
        }
        if(producto3.isChecked()){
            total += 1500 * Integer.parseInt(cantidadProd3.getText().toString());
        }
        if(producto4.isChecked()){
            total += 2000 * Integer.parseInt(cantidadProd4.getText().toString());
        }
        if(producto5.isChecked()){
            total += 3000 * Integer.parseInt(cantidadProd5.getText().toString());
        }

        return total;
    }

    public ArrayList<String> agregarProductos(){
        ArrayList<String> productos = new ArrayList<>();

        if(producto1.isChecked()){
            productos.add(producto1.getText().toString());
        }
        if(producto2.isChecked()){
            productos.add(producto2.getText().toString());
        }
        if(producto3.isChecked()){
            productos.add(producto3.getText().toString());
        }
        if(producto4.isChecked()){
            productos.add(producto4.getText().toString());
        }
        if(producto5.isChecked()){
            productos.add(producto5.getText().toString());
        }

        return productos;

    }

    public boolean productoSeleccionado(){
        if(producto1.isChecked() || producto2.isChecked() || producto3.isChecked() ||
                producto4.isChecked() || producto5.isChecked() ){
            return true;
        }
        else{
            return false;
        }
    }


    public void actvis1(View view){
        CheckBox chk1 = findViewById(R.id.chkItem1);

        if (chk1.isChecked()){
            cantidadProd1.setVisibility(View.VISIBLE);
        } else {
            cantidadProd1.setVisibility(View.INVISIBLE);
            cantidadProd1.setText("1");
        }
    }
    public void actvis2(View view){
        CheckBox chk2 = findViewById(R.id.chkItem2);

        if (chk2.isChecked()){
            cantidadProd2.setVisibility(View.VISIBLE);
        } else {
            cantidadProd2.setVisibility(View.INVISIBLE);
            cantidadProd2.setText("1");
        }
    }
    public void actvis3(View view){
        CheckBox chk3 = findViewById(R.id.chkItem3);
        if (chk3.isChecked()){
            cantidadProd3.setVisibility(View.VISIBLE);
        } else {
            cantidadProd3.setVisibility(View.INVISIBLE);
            cantidadProd3.setText("1");
        }
    }
    public void actvis4(View view){
        CheckBox chk4 = findViewById(R.id.chkItem4);
        if (chk4.isChecked()){
            cantidadProd4.setVisibility(View.VISIBLE);
        } else {
            cantidadProd4.setVisibility(View.INVISIBLE);
            cantidadProd4.setText("1");
        }
    }
    public void actvis5(View view){
        CheckBox chk5 = findViewById(R.id.chkItem5);
        if (chk5.isChecked()){
            cantidadProd5.setVisibility(View.VISIBLE);
        } else {
            cantidadProd5.setVisibility(View.INVISIBLE);
            cantidadProd5.setText("1");
        }
    }

    public void enviarDatos(View view){

        if (!productoSeleccionado()){
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Seleccione algun producto a comprar")
                    .setPositiveButton("OK", null)
                    .show();

        }
        else{
            Intent intent = new Intent(this,SegAct.class);
            startActivity(intent);


            intent.putExtra("productos", agregarProductos());
            intent.putExtra("total", calcularPrecio());

            startActivity(intent);
        }
    }

}