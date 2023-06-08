import java.io.*;
import java.util.*;

// Clase principal del programa
public class Biblioteca {

    private List<Libro> libros;
    private List<Usuario> usuarios;

    public Biblioteca() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    // Método para dar de alta un libro
    public void darDeAltaLibro(String titulo, String autor, String categoria, int edadRecomendada) {
        int nuevoId = libros.size() + 1; // Generar un nuevo identificador para el libro
        Libro libro = new Libro(nuevoId, titulo, autor, categoria, edadRecomendada);
        libros.add(libro);
        System.out.println("Libro dado de alta correctamente.");
    }

    // Método para dar de baja un libro
    public void darDeBajaLibro(int id) {
        Libro libro = buscarLibroPorId(id);
        if (libro != null) {
            libros.remove(libro);
            System.out.println("Libro dado de baja correctamente.");
        } else {
            System.out.println("No se encontró ningún libro con el ID especificado.");
        }
    }

    // Método para dar de alta un usuario
    public void darDeAltaUsuario(String nombre, String apellidos, String fechaNacimiento, String dni) {
        Usuario usuario = new Usuario(nombre, apellidos, fechaNacimiento, dni);
        usuarios.add(usuario);
        System.out.println("Usuario dado de alta correctamente.");
    }

    // Método para dar de baja un usuario
    public void darDeBajaUsuario(String dni) {
        Usuario usuario = buscarUsuarioPorDni(dni);
        if (usuario != null) {
            usuarios.remove(usuario);
            System.out.println("Usuario dado de baja correctamente.");
        } else {
            System.out.println("No se encontró ningún usuario con el DNI especificado.");
        }
    }

    // Método para prestar un libro a un usuario
    public void prestarLibro(int idLibro, String dniUsuario) {
        Libro libro = buscarLibroPorId(idLibro);
        Usuario usuario = buscarUsuarioPorDni(dniUsuario);

        if (libro == null) {
            System.out.println("No se encontró ningún libro con el ID especificado.");
        } else if (usuario == null) {
            System.out.println("No se encontró ningún usuario con el DNI especificado.");
        } else if (libro.getPrestado()) {
            System.out.println("El libro ya está prestado.");
        } else if (usuario.getEdad() < libro.getEdadRecomendada()) {
            System.out.println("El usuario no cumple con la edad recomendada para este libro.");
        } else {
            libro.setPrestado(true);
            libro.setUsuarioPrestamo(usuario);
            System.out.println("Libro prestado correctamente.");
        }
    }

    // Método para devolver un libro prestado
    public void devolverLibro(int idLibro) {
        Libro libro = buscarLibroPorId(idLibro);
        if (libro != null && libro.getPrestado()) {
            libro.setPrestado(false);
            libro.setUsuarioPrestamo(null);
            System.out.println("Libro devuelto correctamente.");
        } else {
            System.out.println("No se encontró ningún libro con el ID especificado o el libro no está prestado.");
        }
    }

    // Método para listar libros por título
    public void listarLibrosPorTitulo() {
        Collections.sort(libros, Comparator.comparing(Libro::getTitulo));
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    // Método para listar libros por categoría
    public void listarLibrosPorCategoria(String categoria) {
        for (Libro libro : libros) {
            if (libro.getCategoria().equalsIgnoreCase(categoria)) {
                System.out.println(libro);
            }
        }
    }

    // Método para listar libros prestados
    public void listarLibrosPrestados() {
        for (Libro libro : libros) {
            if (libro.getPrestado()) {
                System.out.println(libro);
            }
        }
    }

    // Método para listar libros disponibles
    public void listarLibrosDisponibles() {
        for (Libro libro : libros) {
            if (!libro.getPrestado()) {
                System.out.println(libro);
            }
        }
    }

    // Método para listar libros por usuario
    public void listarLibrosPorUsuario(String dniUsuario) {
        Usuario usuario = buscarUsuarioPorDni(dniUsuario);
        if (usuario != null) {
            for (Libro libro : libros) {
                if (libro.getPrestado() && libro.getUsuarioPrestamo().equals(usuario)) {
                    System.out.println(libro);
                }
            }
        } else {
            System.out.println("No se encontró ningún usuario con el DNI especificado.");
        }
    }

    // Método para almacenar en un fichero el estado de la biblioteca
    public void almacenarEstadoBiblioteca(String archivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            writer.println("Libros:");
            for (Libro libro : libros) {
                writer.println(libro.getId() + ", " + libro.getTitulo() + ", " + libro.getAutor() + ", " +
                        libro.getCategoria() + ", " + libro.getEdadRecomendada() + ", " + libro.getPrestado());
            }
            writer.println("Usuarios:");
            for (Usuario usuario : usuarios) {
                writer.println(usuario.getNombre() + ", " + usuario.getApellidos() + ", " +
                        usuario.getFechaNacimiento() + ", " + usuario.getDni());
            }
            System.out.println("Estado de la biblioteca almacenado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al almacenar el estado de la biblioteca.");
        }
    }

    // Método auxiliar para buscar un libro por su ID
    private Libro buscarLibroPorId(int id) {
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                return libro;
            }
        }
        return null;
    }

    // Método auxiliar para buscar un usuario por su DNI
    private Usuario buscarUsuarioPorDni(String dni) {
        for (Usuario usuario : usuarios) {
            if (usuario.getDni().equalsIgnoreCase(dni)) {
                return usuario;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;
        while (opcion != 9) {
            System.out.println("Bienvenido a la biblioteca");
            System.out.println("1. Dar de alta un libro");
            System.out.println("2. Dar de baja un libro");
            System.out.println("3. Dar de alta un usuario");
            System.out.println("4. Dar de baja un usuario");
            System.out.println("5. Prestar un libro");
            System.out.println("6. Devolver un libro");
            System.out.println("7. Listar libros por título");
            System.out.println("8. Listar libros por categoría");
            System.out.println("9. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese el autor/es del libro: ");
                    String autor = scanner.nextLine();
                    System.out.print("Ingrese la categoría del libro: ");
                    String categoria = scanner.nextLine();
                    System.out.print("Ingrese la edad recomendada de lectura: ");
                    int edadRecomendada = scanner.nextInt();
                    biblioteca.darDeAltaLibro(titulo, autor, categoria, edadRecomendada);
                    break;
                case 2:
                    System.out.print("Ingrese el ID del libro a dar de baja: ");
                    int idBaja = scanner.nextInt();
                    biblioteca.darDeBajaLibro(idBaja);
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del usuario: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese los apellidos del usuario: ");
                    String apellidos = scanner.nextLine();
                    System.out.print("Ingrese la fecha de nacimiento del usuario: ");
                    String fechaNacimiento = scanner.nextLine();
                    System.out.print("Ingrese el DNI del usuario: ");
                    String dni = scanner.nextLine();
                    biblioteca.darDeAltaUsuario(nombre, apellidos, fechaNacimiento, dni);
                    break;
                case 4:
                    System.out.print("Ingrese el DNI del usuario a dar de baja: ");
                    String dniBaja = scanner.nextLine();
                    biblioteca.darDeBajaUsuario(dniBaja);
                    break;
                case 5:
                    System.out.print("Ingrese el ID del libro a prestar: ");
                    int idPrestamo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el DNI del usuario: ");
                    String dniPrestamo = scanner.nextLine();
                    biblioteca.prestarLibro(idPrestamo, dniPrestamo);
                    break;
                case 6:
                    System.out.print("Ingrese el ID del libro a devolver: ");
                    int idDevolucion = scanner.nextInt();
                    biblioteca.devolverLibro(idDevolucion);
                    break;
                case 7:
                    biblioteca.listarLibrosPorTitulo();
                    break;
                case 8:
                    System.out.print("Ingrese la categoría de los libros a listar: ");
                    String categoriaListado = scanner.nextLine();
                    biblioteca.listarLibrosPorCategoria(categoriaListado);
                    break;
                case 9:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
            }
            System.out.println();
        }
        scanner.close();
    }

	public Usuario[] getUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	public Libro[] getLibros() {
		// TODO Auto-generated method stub
		return null;
	}
}

// Clase Libro
class Libro {
    private int id;
    private String titulo;
    private String autor;
    private String categoria;
    private int edadRecomendada;
    private boolean prestado;
    private Usuario usuarioPrestamo;

    public Libro(int id, String titulo, String autor, String categoria, int edadRecomendada) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.edadRecomendada = edadRecomendada;
        this.prestado = false;
        this.usuarioPrestamo = null;
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

    public String getCategoria() {
        return categoria;
    }

    public int getEdadRecomendada() {
        return edadRecomendada;
    }

    public boolean getPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    public Usuario getUsuarioPrestamo() {
        return usuarioPrestamo;
    }

    public void setUsuarioPrestamo(Usuario usuarioPrestamo) {
        this.usuarioPrestamo = usuarioPrestamo;
    }

    @Override
    public String toString() {
        String estadoPrestamo = prestado ? "Prestado a: " + usuarioPrestamo.getNombre() + " " + usuarioPrestamo.getApellidos() : "Disponible";
        return "ID: " + id + ", Título: " + titulo + ", Autor/es: " + autor + ", Categoría: " + categoria + ", Edad Recomendada: " +
                edadRecomendada + ", Estado: " + estadoPrestamo;
    }
}

// Clase Usuario
class Usuario {
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String dni;

    public Usuario(String nombre, String apellidos, String fechaNacimiento, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDni() {
        return dni;
    }

    public int getEdad() {
        // Cálculo de la edad basado en la fecha de nacimiento
        // (asumiendo que la fecha de nacimiento está en formato "dd/mm/yyyy")
        String[] partesFecha = fechaNacimiento.split("/");
        int diaNacimiento = Integer.parseInt(partesFecha[0]);
        int mesNacimiento = Integer.parseInt(partesFecha[1]);
        int anioNacimiento = Integer.parseInt(partesFecha[2]);

        Calendar fechaActual = Calendar.getInstance();
        int diaActual = fechaActual.get(Calendar.DAY_OF_MONTH);
        int mesActual = fechaActual.get(Calendar.MONTH) + 1;
        int anioActual = fechaActual.get(Calendar.YEAR);

        int edad = anioActual - anioNacimiento;
        if (mesActual < mesNacimiento || (mesActual == mesNacimiento && diaActual < diaNacimiento)) {
            edad--;
        }
        return edad;
    }
}
