import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class bibliotecaarchivo {

    public static void main(String[] args) {
        // Crear una instancia de Biblioteca
        Biblioteca Biblioteca = new Biblioteca();

        // Agregar usuarios a la biblioteca
        Biblioteca.darDeAltaUsuario("Juan", "Pérez", "01/01/1990", "12345678A");
        Biblioteca.darDeAltaUsuario("María", "Gómez", "15/05/1985", "87654321B");
        Biblioteca.darDeAltaUsuario("Pedro", "López", "10/11/2000", "98765432C");

        // Agregar libros a la biblioteca
        Biblioteca.darDeAltaLibro("La aventura comienza", "Autor A", "Aventuras", 10);
        Biblioteca.darDeAltaLibro("El viaje intergaláctico", "Autor B", "Ciencia Ficción", 12);
        Biblioteca.darDeAltaLibro("Amor y pasión", "Autor C", "Romántica", 14);
        Biblioteca.darDeAltaLibro("Historia del mundo antiguo", "Autor D", "Historia", 16);
        Biblioteca.darDeAltaLibro("El arte de pintar", "Autor E", "Arte", 18);

        // Guardar la información de los usuarios y libros en archivos
        guardarUsuariosEnArchivo(Biblioteca, "usuarios.txt");
        guardarLibrosEnArchivo(Biblioteca, "libros.txt");
    }

    public static void guardarUsuariosEnArchivo(Biblioteca Biblioteca, String archivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            for (Usuario usuario : Biblioteca.getUsuarios()) {
                writer.println("Nombre: " + usuario.getNombre());
                writer.println("Apellidos: " + usuario.getApellidos());
                writer.println("Fecha de Nacimiento: " + usuario.getFechaNacimiento());
                writer.println("DNI: " + usuario.getDni());
                writer.println();
            }

            System.out.println("Archivo de usuarios guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de usuarios.");
            e.printStackTrace();
        }
    }

    public static void guardarLibrosEnArchivo(Biblioteca Biblioteca, String archivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            for (Libro libro : Biblioteca.getLibros()) {
                writer.println("ID: " + libro.getId());
                writer.println("Título: " + libro.getTitulo());
                writer.println("Autor/es: " + libro.getAutor());
                writer.println("Categoría: " + libro.getCategoria());
                writer.println("Edad Recomendada: " + libro.getEdadRecomendada());
                writer.println("Prestado: " + (libro.getPrestado() ? "Sí" : "No"));
                if (libro.getPrestado()) {
                    writer.println("Usuario Prestamo: " + libro.getUsuarioPrestamo().getNombre()
                            + " " + libro.getUsuarioPrestamo().getApellidos());
                }
                writer.println();
            }

            System.out.println("Archivo de libros guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de libros.");
            e.printStackTrace();
        }
    }
}
