# Diseño del Sistema - Biblioteca Barrial

## 1. Modelo de Clases

### Clase `Libro`
* **Atributos:**
  * `int id`: Identificador numérico único del libro.
  * `String titulo`: Título de la obra.
  * `String autor`: Nombre del autor.
  * `boolean prestado`: Estado del libro (true si está prestado, false si está disponible).
* **Métodos principales:** Getters, setters y `toString()`.

### Clase `Socio`
* **Atributos:**
  * `int numeroSocio`: Identificador único del socio.
  * `String nombre`: Nombre completo.
  * `List<Libro> librosPrestados`: Lista propia de ejemplares retirados por el socio.
* **Métodos principales:** `tomarPrestado(Libro)`, `devolverLibro(int idLibro)` y `getLibrosPrestados()`.

### Clase `BibliotecaService`
* **Colecciones administradas:**
  * `List<Libro> catalogo`: Contiene el inventario total de libros de la biblioteca.
  * `List<Socio> socios`: Contiene todos los socios registrados.
* **Operaciones obligatorias (Clase 6):**
  * `agregarLibro(Libro)` (Alta)
  * `eliminarLibro(int id)` (Baja)
  * `buscarLibroPorId(int id)` (Búsqueda)
  * `listarCatalogo()` (Listado)
  * `prestarLibro(int numSocio, int idLibro)` y `devolverLibro(...)`
  * `listarPrestamosPorSocio()` (Recorrido de la colección de colecciones)

---

## 2. Diagrama de Relaciones (Estructura de Datos)

```text
[ BibliotecaService ]
       │
       ├──> List<Libro> (Catálogo general)
       │         └── Libro (id, titulo, autor, prestado)
       │
       └──> List<Socio> (Registro de socios)
                 └── Socio (numeroSocio, nombre)
                          └── List<Libro> (Colección interna de libros prestados)
