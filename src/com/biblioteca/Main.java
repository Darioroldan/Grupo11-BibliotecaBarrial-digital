package com.biblioteca;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BibliotecaService servicio = new BibliotecaService();
        Scanner sc = new Scanner(System.in);

        // Datos precargados para facilitar pruebas
        servicio.agregarLibro(new Libro(1, "El Principito", "Saint-Exupéry"));
        servicio.agregarLibro(new Libro(2, "Ficciones", "Jorge Luis Borges"));
        servicio.agregarLibro(new Libro(3, "Rayuela", "Julio Cortázar"));
        servicio.registrarSocio(new Socio(10, "Juan Gómez"));
        servicio.registrarSocio(new Socio(20, "Ana Morales"));

        int opcion = 0;
        do {
            System.out.println("\n=== BIBLIOTECA POPULAR COMUNITARIA ===");
            System.out.println("1. Alta de libro al catálogo");
            System.out.println("2. Buscar libro por ID");
            System.out.println("3. Baja de libro del catálogo");
            System.out.println("4. Listar catálogo completo");
            System.out.println("5. Registrar nuevo socio");
            System.out.println("6. Registrar préstamo a socio");
            System.out.println("7. Devolver libro");
            System.out.println("8. Listar préstamos por socio (Colección de Colecciones)");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.print("ID del libro: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Título: ");
                        String tit = sc.nextLine();
                        System.out.print("Autor: ");
                        String aut = sc.nextLine();
                        if (servicio.agregarLibro(new Libro(id, tit, aut))) {
                            System.out.println("Libro agregado exitosamente.");
                        } else {
                            System.out.println("Error: Ya existe un libro con ese ID.");
                        }
                        break;
                    case 2:
                        System.out.print("Ingrese ID a buscar: ");
                        int idBuscar = Integer.parseInt(sc.nextLine());
                        Libro encontrado = servicio.buscarLibroPorId(idBuscar);
                        if (encontrado != null) {
                            System.out.println("Resultado: " + encontrado);
                        } else {
                            System.out.println("Libro no encontrado.");
                        }
                        break;
                    case 3:
                        System.out.print("ID del libro a eliminar: ");
                        int idEliminar = Integer.parseInt(sc.nextLine());
                        if (servicio.eliminarLibro(idEliminar)) {
                            System.out.println("Libro eliminado del catálogo.");
                        } else {
                            System.out.println("No se pudo eliminar (no existe o se encuentra prestado).");
                        }
                        break;
                    case 4:
                        servicio.listarCatalogo();
                        break;
                    case 5:
                        System.out.print("Número de socio: ");
                        int numSocio = Integer.parseInt(sc.nextLine());
                        System.out.print("Nombre completo: ");
                        String nomSocio = sc.nextLine();
                        if (servicio.registrarSocio(new Socio(numSocio, nomSocio))) {
                            System.out.println("Socio registrado exitosamente.");
                        } else {
                            System.out.println("Error: Ya existe un socio con ese número.");
                        }
                        break;
                    case 6:
                        System.out.print("Número de socio: ");
                        int ns = Integer.parseInt(sc.nextLine());
                        System.out.print("ID del libro: ");
                        int il = Integer.parseInt(sc.nextLine());
                        if (servicio.prestarLibro(ns, il)) {
                            System.out.println("Préstamo registrado.");
                        } else {
                            System.out.println("Error: Datos no válidos o el libro ya está prestado.");
                        }
                        break;
                    case 7:
                        System.out.print("Número de socio: ");
                        int nsDev = Integer.parseInt(sc.nextLine());
                        System.out.print("ID del libro: ");
                        int ilDev = Integer.parseInt(sc.nextLine());
                        if (servicio.devolverLibro(nsDev, ilDev)) {
                            System.out.println("Libro devuelto con éxito.");
                        } else {
                            System.out.println("Error: El socio no tiene ese libro prestado.");
                        }
                        break;
                    case 8:
                        servicio.listarPrestamosPorSocio();
                        break;
                    case 9:
                        System.out.println("Cerrando el sistema...");
                        break;
                    default:
                        System.out.println("Opción fuera de rango.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número válido.");
            }
        } while (opcion != 9);

        sc.close();
    }
}