package com.actividad.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SegAct extends AppCompatActivity {

    int precioTotal;
    int abono;
    ArrayList<String> productos;
    TextView txtTotal;
    TextView txtProductos;
    TextView txtFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seg);

        precioTotal = getIntent().getIntExtra("total", 0);
        productos = getIntent().getStringArrayListExtra("productos");

        txtProductos = findViewById(R.id.txtProductosSelec);
        txtTotal = findViewById(R.id.txtPagoTotal);

        txtProductos.setText(organizarArray());
        txtTotal.setText("Total a pagar: $" + precioTotal);

        txtFinal = findViewById(R.id.txtFinal);
        EditText editAbona = findViewById(R.id.txtAbona);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public String organizarArray(){
        String organizados = "";

        for(String producto: productos){
            organizados += "- " + producto + "\n\n";
        }

        return organizados;
    }

    public void Pagar(View view){
        int abono = 0;
        EditText editAbona = findViewById(R.id.txtAbona);
        String abonoStr = editAbona.getText().toString();

        if (!abonoStr.isEmpty()) {
            abono = Integer.parseInt(abonoStr);
        }
        int restante = abono - precioTotal;

        if (restante >= 0) {
            txtFinal.setText("Pago completado, Retire Sus Productos y su vuelto es de: $" + Math.abs(restante));
        } else {
            txtFinal.setText("Falta abonar: $" + Math.abs(restante));
        }
    }
    public void IraMain(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}