package scr;

import scr.controller.ShopPlusController;
import scr.model.*;
import scr.exception.*;
import java.util.Scanner;

/**
 * Clase principal que maneja la interfaz de usuario del sistema ShopPlus.
 * @author Juan Cogua
 * @version 1.0
 */
public class Main {
    /**
     * Controlador principal de la aplicación.
     */
    private static ShopPlusController controller;
    private static Scanner scanner;
    private static Usuario usuarioActual;

    /**
     * Punto de entrada de la aplicación.
     * @param args Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        controller = new ShopPlusController();
        scanner = new Scanner(System.in);

        while (true) {
            mostrarMenuPrincipal();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    System.out.println("¡Gracias por usar ShopPlus!");
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    /**
     * Muestra el menú principal en consola.
     */
    private static void mostrarMenuPrincipal() {
        System.out.println("\n=== ShopPlus - Sistema de Comercio ===");
        System.out.println("1. Registrar nuevo usuario");
        System.out.println("2. Iniciar sesión");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Interfaz para registrar un nuevo usuario.
     */
    private static void registrarUsuario() {
        System.out.println("\n=== Registro de Usuario ===");
        System.out.println("Tipo de usuario:");
        System.out.println("1. Cliente");
        System.out.println("2. Proveedor");
        System.out.print("Seleccione el tipo de usuario: ");
        
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        try {
            switch (tipo) {
                case 1:
                    System.out.print("Dirección de envío: ");
                    String direccion = scanner.nextLine();
                    controller.registrarUsuario(new Cliente(nombre, email, contraseña, direccion));
                    break;
                case 2:
                    System.out.print("Nombre de la empresa: ");
                    String empresa = scanner.nextLine();
                    controller.registrarUsuario(new Proveedor(nombre, email, contraseña, empresa));
                    break;
                default:
                    System.out.println("Tipo de usuario no válido");
                    return;
            }
            System.out.println("Usuario registrado exitosamente");
        } catch (UsuarioInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Interfaz para iniciar sesión.
     */
    private static void iniciarSesion() {
        System.out.print("\nEmail: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        try {
            usuarioActual = controller.autenticarUsuario(email, contraseña);
            System.out.println("Bienvenido, " + usuarioActual.getNombre());
            
            if (usuarioActual instanceof Cliente) {
                menuCliente((Cliente) usuarioActual);
            } else if (usuarioActual instanceof Administrador) {
                menuAdministrador((Administrador) usuarioActual);
            } else if (usuarioActual instanceof Proveedor) {
                menuProveedor((Proveedor) usuarioActual);
            }
        } catch (UsuarioInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Menú para clientes.
     * @param cliente Cliente autenticado
     */
    private static void menuCliente(Cliente cliente) {
        while (true) {
            System.out.println("\n=== Menú Cliente ===");
            System.out.println("1. Realizar compra");
            System.out.println("2. Consultar mis pedidos");
            System.out.println("3. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    cliente.realizarCompra(controller, scanner);
                    break;
                case 2:
                    cliente.mostrarPedidos();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    /**
     * Menú para administradores.
     * @param admin Administrador autenticado
     */
    private static void menuAdministrador(Administrador admin) {
        while (true) {
            System.out.println("\n=== Menú Administrador ===");
            System.out.println("1. Ver todos los productos");
            System.out.println("2. Ver todos los usuarios");
            System.out.println("3. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    controller.mostrarProductosConsole();
                    break;
                case 2:
                    controller.mostrarUsuariosConsole();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    /**
     * Menú para proveedores.
     * @param proveedor Proveedor autenticado
     */
    private static void menuProveedor(Proveedor proveedor) {
        while (true) {
            System.out.println("\n=== Menú Proveedor ===");
            System.out.println("1. Registrar nuevo producto");
            System.out.println("2. Ver mi inventario");
            System.out.println("3. Ver entregas pendientes");
            System.out.println("4. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    proveedor.registrarProducto(controller, scanner);
                    break;
                case 2:
                    proveedor.mostrarInventario();
                    break;
                case 3:
                    proveedor.mostrarEntregasPendientes();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}