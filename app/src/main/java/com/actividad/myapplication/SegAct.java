package com.actividad.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SegAct extends AppCompatActivity {

    // Variables para almacenar los datos recibidos de la primera activity
    int precioTotal;
    int abono;
    ArrayList<String> productos;
    ArrayList<String> cantidadProductos;
    TextView txtTotal, txtProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seg);

        // Recepción de datos enviados desde MainActivity
        precioTotal = getIntent().getIntExtra("total", 0);
        productos = getIntent().getStringArrayListExtra("productos");
        cantidadProductos = getIntent().getStringArrayListExtra("cantidadesProductos");

        // Inicialización de componentes de la interfaz
        txtProductos = findViewById(R.id.txtProductosSelec);
        txtTotal = findViewById(R.id.txtPagoTotal);

        // Texto mostrado en la interfaz
        txtProductos.setText(organizarArray());
        txtTotal.setText("Total a pagar: $" + precioTotal);

        EditText editAbona = findViewById(R.id.txtAbona);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    // ***********************************************************
    //       Metodo Para mostrar el contenido de los arrays de
    //         los productos seleccionados con sus cantidades
    // ***********************************************************
    public String organizarArray(){
        String organizados = "";
        // iteracion a traves de los arrays
        for(int i = 0; i < productos.size(); i++){
            organizados += "- " + productos.get(i) + " (Cantidad: " + cantidadProductos.get(i) + ")\n\n";
        }
        return organizados;
    }


    // ************************************************************
    //      Metodo para procesar el pago y calcular el vuelto
    // ************************************************************

    public void Pagar(View view){
        int abono = 0;

        EditText editAbona = findViewById(R.id.txtAbona);
        String abonoStr = editAbona.getText().toString();

        //Valida que el campo para abonar no este vacio
        if (!abonoStr.isEmpty()) {
            abono = Integer.parseInt(abonoStr);
        }else{
            Toast.makeText(this, "Ingrese un monto para abonar", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        // calcula el restante
        int restante = abono - precioTotal;

        // Caso de pago exacto
        if(restante == 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Notificacion")
                    .setMessage("Pago completado!\nRetire Sus Productos")
                    .setPositiveButton("OK", null)
                    .show();
        }
        // Caso de pago mayor (vuelto)
        else if (restante >= 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Notificacion")
                    .setMessage("Pago completado!\nRetire Sus Productos\nsu vuelto es de: $" + Math.abs(restante))
                    .setPositiveButton("OK", null)
                    .show();

        }
        // Caso de pago Insuficiente
        else {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Cantidad Insuficiente!\nFalta abonar: $" + Math.abs(restante))
                    .setPositiveButton("OK", null)
                    .show();
        }
    }

    // ************************************************
    // Metodo Para regresar a la primera activity
    // ************************************************
    public void IraMain(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}