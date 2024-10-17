package com.carballeira.proyectoud2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private LinearLayout conversacion1;
    private EditText textoEnviar1;
    private Button enviar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        conversacion1 = findViewById(R.id.conversacion1);
        textoEnviar1 = findViewById(R.id.textoEnviar1);
        enviar1 = findViewById(R.id.enviar1);


        String historialPrevio = getIntent().getStringExtra("historial");
        if (historialPrevio != null) {
            agregarMensaje(historialPrevio);
        }
        enviar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funcionEnviar(v);
            }
        });
    }
    public void funcionEnviar(View view) {
        String nuevoMensaje = "Actividad 1: " + this.textoEnviar1.getText().toString();
        agregarMensaje(nuevoMensaje);  // Añadir el nuevo mensaje al layout

        // Crear el Intent para pasar el historial de mensajes a la segunda actividad
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("historial", obtenerHistorial());  // Enviar el historial actualizado
        startActivity(intent);
    }
    private void agregarMensaje(String mensaje) {
        TextView nuevoTexto = new TextView(this);  // Crear un nuevo TextView
        nuevoTexto.setText(mensaje);  // Establecer el texto con el mensaje
        conversacion1.addView(nuevoTexto);  // Agregar el TextView al LinearLayout
    }
    private String obtenerHistorial() {
        StringBuilder historial = new StringBuilder();
        int count = conversacion1.getChildCount();  // Obtener el número de vistas en el LinearLayout
        for (int i = 0; i < count; i++) {
            TextView texto = (TextView) conversacion1.getChildAt(i);  // Obtener cada TextView
            historial.append(texto.getText().toString()).append("\n");  // Agregar el texto al historial
        }
        return historial.toString();  // Devolver todo el historial como una cadena de texto
    }
}