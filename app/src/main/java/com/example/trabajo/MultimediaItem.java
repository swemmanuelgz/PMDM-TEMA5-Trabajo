// MultimediaItem.java
package com.example.trabajo;

/**
 * Clase que representa un recurso multimedia.
 */
public class MultimediaItem {
    // Enum para definir los tipos de recursos disponibles
    public enum Type {
        VIDEO, AUDIO, WEB
    }

    // Atributos del recurso
    private String title;
    private String url;
    private Type type;

    /**
     * Constructor de la clase.
     *
     * @param title Título del recurso.
     * @param url   URL o nombre del recurso (para vídeo y audio se utiliza el nombre del archivo en res/raw).
     * @param type  Tipo de recurso.
     */
    public MultimediaItem(String title, String url, Type type) {
        this.title = title;
        this.url = url;
        this.type = type;
    }

    // Métodos getter para acceder a los atributos
    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Type getType() {
        return type;
    }
}

