package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Socio {
    private int numeroSocio;
    private String nombre;
    // Lista de libros que tiene este socio (forma parte de la colección de colecciones)
    private List<Libro> librosPrestados;

    public Socio(int numeroSocio, String nombre) {
        this.numeroSocio = numeroSocio;
        this.nombre = nombre;
        this.librosPrestados = new ArrayList<>();
    }

    public int getNumeroSocio() {
        return numeroSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    public void tomarPrestado(Libro libro) {
        librosPrestados.add(libro);
        libro.setPrestado(true);
    }

    public boolean devolverLibro(int idLibro) {
        for (Libro libro : librosPrestados) {
            if (libro.getId() == idLibro) {
                libro.setPrestado(false);
                librosPrestados.remove(libro);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Socio N° " + numeroSocio + " - " + nombre + " (Libros en préstamo: " + librosPrestados.size() + ")";
    }
}