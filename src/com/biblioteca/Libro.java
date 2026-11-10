package com.biblioteca;

public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private boolean prestado;

    public Libro(int id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.prestado = false;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    @Override
    public String toString() {
        return String.format("ID: %-4d | Título: %-25s | Autor: %-18s | Estado: %s",
                id, titulo, autor, (prestado ? "PRESTADO" : "DISPONIBLE"));
    }
}