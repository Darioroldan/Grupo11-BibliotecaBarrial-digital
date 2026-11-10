package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {
    // List principal de libros del catálogo
    private List<Libro> catalogo;
    
    // Colección de colecciones: Lista de socios, donde cada socio tiene su List<Libro>
    private List<Socio> socios;

    public BibliotecaService() {
        this.catalogo = new ArrayList<>();
        this.socios = new ArrayList<>();
    }

    // --- MÍNIMO TÉCNICO CATÁLOGO (ALTA, BAJA, BÚSQUEDA, LISTADO) ---

    // Alta de libro validando ID único
    public boolean agregarLibro(Libro nuevoLibro) {
        if (buscarLibroPorId(nuevoLibro.getId()) != null) {
            return false;
        }
        catalogo.add(nuevoLibro);
        return true;
    }

    // Búsqueda de libro por ID
    public Libro buscarLibroPorId(int id) {
        for (Libro l : catalogo) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    // Baja de libro (no permite borrar si está prestado)
    public boolean eliminarLibro(int id) {
        Libro libro = buscarLibroPorId(id);
        if (libro != null && !libro.isPrestado()) {
            return catalogo.remove(libro);
        }
        return false;
    }

    // Listado general del catálogo
    public void listarCatalogo() {
        if (catalogo.isEmpty()) {
            System.out.println("El catálogo está vacío.");
            return;
        }
        System.out.println("\n--- CATÁLOGO DE LA BIBLIOTECA ---");
        for (Libro l : catalogo) {
            System.out.println(l);
        }
    }

    // --- GESTIÓN DE SOCIOS Y PRÉSTAMOS (COLECCIÓN DE COLECCIONES) ---

    public boolean registrarSocio(Socio socio) {
        if (buscarSocioPorNumero(socio.getNumeroSocio()) != null) {
            return false;
        }
        socios.add(socio);
        return true;
    }

    public Socio buscarSocioPorNumero(int numeroSocio) {
        for (Socio s : socios) {
            if (s.getNumeroSocio() == numeroSocio) {
                return s;
            }
        }
        return null;
    }

    public boolean prestarLibro(int numeroSocio, int idLibro) {
        Socio socio = buscarSocioPorNumero(numeroSocio);
        Libro libro = buscarLibroPorId(idLibro);

        if (socio != null && libro != null && !libro.isPrestado()) {
            socio.tomarPrestado(libro);
            return true;
        }
        return false;
    }

    public boolean devolverLibro(int numeroSocio, int idLibro) {
        Socio socio = buscarSocioPorNumero(numeroSocio);
        if (socio != null) {
            return socio.devolverLibro(idLibro);
        }
        return false;
    }

    // Listar todos los préstamos recorriendo la colección de colecciones
    public void listarPrestamosPorSocio() {
        if (socios.isEmpty()) {
            System.out.println("No hay socios registrados.");
            return;
        }
        System.out.println("\n--- REPORTE: LIBROS PRESTADOS POR SOCIO (COLECCIÓN DE COLECCIONES) ---");
        for (Socio socio : socios) {
            System.out.println("\n" + socio);
            List<Libro> prestados = socio.getLibrosPrestados();
            if (prestados.isEmpty()) {
                System.out.println("   (Sin libros en préstamo actualmente)");
            } else {
                for (Libro lib : prestados) {
                    System.out.println("   -> [ID " + lib.getId() + "] " + lib.getTitulo() + " (" + lib.getAutor() + ")");
                }
            }
        }
    }
}
