// MainActivity.java
package com.example.trabajo;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajo.adapters.MultimediaAdapter;
import com.example.trabajo.dialog.DetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Actividad principal que muestra la lista de recursos multimedia.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar el RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Crear la lista de ítems multimedia
        List<MultimediaItem> items = new ArrayList<>();
        // Para vídeos y audios se usa el nombre del recurso (que se encuentra en res/raw)
        items.add(new MultimediaItem("CR7", "cr7", MultimediaItem.Type.VIDEO));
        items.add(new MultimediaItem("Gato", "gato", MultimediaItem.Type.VIDEO));
        items.add(new MultimediaItem("Ojitos", "ojitos", MultimediaItem.Type.AUDIO));
        items.add(new MultimediaItem("Rehenkharmacion", "rehenkharmacion", MultimediaItem.Type.AUDIO));
        // Recursos web con URL completas
        items.add(new MultimediaItem("Google Finance", "https://www.google.com/finance/", MultimediaItem.Type.WEB));
        items.add(new MultimediaItem("Marca", "https://www.marca.com/", MultimediaItem.Type.WEB));

        // Configurar el adaptador del RecyclerView
        MultimediaAdapter adapter = new MultimediaAdapter(items, item -> {
            // Al pulsar sobre un ítem se lanza la actividad de detalle
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("title", item.getTitle());
            intent.putExtra("url", item.getUrl());
            intent.putExtra("type", item.getType().name());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }
}
