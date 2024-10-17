package com.carballeira.proyectoud2;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity2 extends AppCompatActivity {
    private LinearLayout conversacion2; // Variable para el LinearLayout que contiene los mensajes
    private EditText textoEnviar2; // Variable para el EditText donde se escribe el mensaje
    private Button enviar2; // Variable para el botón de enviar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Inicializar los componentes
        conversacion2 = findViewById(R.id.conversacion2);  // Historial de mensajes
        textoEnviar2 = findViewById(R.id.textoEnviar2);    // Texto a enviar
        enviar2 = findViewById(R.id.enviar2);              // Botón de enviar

        // Recuperar mensajes previos si existen
        String historialPrevio = getIntent().getStringExtra("historial");
        if (historialPrevio != null) {
            // Si hay historial previo, agregarlo a la conversación
            agregarMensaje(historialPrevio);
        }

        // Configurar el listener del botón enviar
        enviar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funcionEnviar(v);  // Llamar a la función enviar
            }
        });
    }

    public void funcionEnviar(View view) {
        // Crear un nuevo mensaje a partir del texto ingresado
        String nuevoMensaje = "Actividad 2: " + textoEnviar2.getText().toString();

        // Agregar el nuevo mensaje a la conversación
        agregarMensaje(nuevoMensaje);

        // Crear el Intent para enviar el historial actualizado a la primera actividad
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        intent.putExtra("historial", nuevoMensaje);  // Enviar solo el nuevo mensaje
        startActivity(intent);

        // Limpiar el campo de texto después de enviar
        textoEnviar2.setText("");
    }

    private void agregarMensaje(String mensaje) {
        // Crear un nuevo TextView para el mensaje
        TextView mensajeView = new TextView(this);
        mensajeView.setText(mensaje);
        mensajeView.setTextSize(16); // Tamaño de texto
        mensajeView.setPadding(16, 16, 16, 16); // Padding alrededor del texto

        // Agregar el nuevo TextView al LinearLayout

    }
}