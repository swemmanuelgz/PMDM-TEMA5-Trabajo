package com.example.trabajo.dialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.MediaController;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trabajo.MultimediaItem;
import com.example.trabajo.R;

/**
 * Actividad que muestra la información detallada de un recurso multimedia.
 * Si se trata de un vídeo, se muestra un VideoView con controles de reproducción.
 * En caso de recurso web, se muestra un WebView para cargar la página.
 */
public class DetailActivity extends AppCompatActivity {

    private TextView titleTextView, urlTextView, typeTextView;
    private VideoView videoView;
    private WebView webView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail); // Vincula el layout de detalle

        // Inicialización de vistas
        titleTextView = findViewById(R.id.detail_title);
        urlTextView = findViewById(R.id.detail_url);
        typeTextView = findViewById(R.id.detail_type);
        videoView = findViewById(R.id.video_view);
        webView = findViewById(R.id.web_view);
        backButton = findViewById(R.id.back_button);

        // Recuperar los datos pasados desde MainActivity
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");
        String typeStr = intent.getStringExtra("type");
        MultimediaItem.Type type = MultimediaItem.Type.valueOf(typeStr);

        // Mostrar la información básica
        titleTextView.setText(title);
        urlTextView.setText(url);
        typeTextView.setText(type.toString());

        // Ocultar las vistas multimedia por defecto
        videoView.setVisibility(VideoView.GONE);
        webView.setVisibility(WebView.GONE);

        // Según el tipo de recurso, se muestran controles específicos
        if (type == MultimediaItem.Type.VIDEO) {
            // Configuración para reproducir vídeo
            videoView.setVisibility(VideoView.VISIBLE);
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);

            // Obtener el ID del recurso de vídeo a partir del nombre (url)
            int videoResId = getResources().getIdentifier(url, "raw", getPackageName());
            // Construir la URI para reproducir el vídeo desde res/raw
            String videoPath = "android.resource://" + getPackageName() + "/" + videoResId;
            videoView.setVideoURI(Uri.parse(videoPath));
            videoView.requestFocus();
            videoView.start(); // Inicia la reproducción
        } else if (type == MultimediaItem.Type.WEB) {
            // Configuración para cargar el recurso web
            webView.setVisibility(WebView.VISIBLE);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(url);
        } else if (type == MultimediaItem.Type.AUDIO) {
            // En este ejemplo para audio simplemente se muestra la información.
            // Podrías agregar controles de reproducción para audio utilizando MediaPlayer.
        }

        // Configurar el botón para volver a la lista (finaliza esta actividad)
        backButton.setOnClickListener(v -> finish());
    }
}

