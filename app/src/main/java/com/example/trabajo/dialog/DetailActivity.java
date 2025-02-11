package com.example.trabajo.dialog;
import static com.example.trabajo.MultimediaItem.Type.AUDIO;
import static com.example.trabajo.MultimediaItem.Type.VIDEO;
import static com.example.trabajo.MultimediaItem.Type.WEB;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;

import com.example.trabajo.MultimediaItem;
import com.example.trabajo.R;

/**
 * Actividad que muestra el detalle del recurso multimedia seleccionado.
 * Según el tipo, se configuran:
 *  - Vídeo: VideoView con controles.
 *  - Audio: Botón para reproducir el audio.
 *  - Web: WebView para cargar la URL.
 */
public class DetailActivity extends AppCompatActivity {

    private TextView titleTextView, urlTextView, typeTextView;
    private VideoView videoView;
    private WebView webView;
    private Button playAudioButton, backButton;
    private MediaPlayer mediaPlayer; // Para reproducir audio

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Activa el modo edge-to-edge
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        // Aplicar insets al layout principal (con id "detail_main")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencias a las vistas
        titleTextView = findViewById(R.id.detail_title);
        urlTextView = findViewById(R.id.detail_url);
        typeTextView = findViewById(R.id.detail_type);
        videoView = findViewById(R.id.video_view);
        webView = findViewById(R.id.web_view);
        playAudioButton = findViewById(R.id.play_audio_button);
        backButton = findViewById(R.id.back_button);

        // Ocultar inicialmente los controles multimedia
        videoView.setVisibility(VideoView.GONE);
        webView.setVisibility(WebView.GONE);
        playAudioButton.setVisibility(Button.GONE);

        // Recuperar datos del Intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");
        String typeStr = intent.getStringExtra("type");
        MultimediaItem.Type type = MultimediaItem.Type.valueOf(typeStr);

        // Mostrar información básica
        titleTextView.setText(title);
        urlTextView.setText(url);
        typeTextView.setText(type.toString());

        // Configurar la vista según el tipo de recurso
        switch (type) {
            case VIDEO:
                videoView.setVisibility(VideoView.VISIBLE);
                // Configura controles para el VideoView
                MediaController mediaController = new MediaController(this);
                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);

                // Obtiene el ID del recurso de vídeo desde res/raw usando el nombre (url)
                int videoResId = getResources().getIdentifier(url, "raw", getPackageName());
                String videoPath = "android.resource://" + getPackageName() + "/" + videoResId;
                videoView.setVideoURI(Uri.parse(videoPath));
                videoView.requestFocus();
                videoView.start();
                break;

            case AUDIO:
                playAudioButton.setVisibility(Button.VISIBLE);
                playAudioButton.setOnClickListener(v -> {
                    // Obtiene el ID del recurso de audio desde res/raw usando el nombre (url)
                    int audioResId = getResources().getIdentifier(url, "raw", getPackageName());
                    mediaPlayer = MediaPlayer.create(DetailActivity.this, audioResId);
                    mediaPlayer.start();
                });
                break;

            case WEB:
                webView.setVisibility(WebView.VISIBLE);
                webView.getSettings().setJavaScriptEnabled(true);
                // Configura clientes para manejar redirecciones y alertas
                webView.setWebViewClient(new WebViewClient());
                webView.setWebChromeClient(new WebChromeClient());
                webView.loadUrl(url);
                break;
        }

        // Botón para volver a la pantalla anterior (lista)
        backButton.setOnClickListener(v -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Liberar el MediaPlayer si se ha utilizado
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

