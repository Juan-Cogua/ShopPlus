package com.shopplus;

import com.shopplus.controller.ShopPlusController;
import com.shopplus.model.*;
import com.shopplus.exception.*;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que maneja la interfaz de usuario del sistema ShopPlus.
 */
public class Main {
    private static ShopPlusController controller;
    private static Scanner scanner;
    private static Usuario usuarioActual;

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

    private static void mostrarMenuPrincipal() {
        System.out.println("\n=== ShopPlus - Sistema de Comercio ===");
        System.out.println("1. Registrar nuevo usuario");
        System.out.println("2. Iniciar sesión");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

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
                    realizarCompra(cliente);
                    break;
                case 2:
                    mostrarPedidos(cliente);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

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
                    mostrarProductos();
                    break;
                case 2:
                    mostrarUsuarios();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

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
                    registrarProducto(proveedor);
                    break;
                case 2:
                    mostrarInventario(proveedor);
                    break;
                case 3:
                    mostrarEntregasPendientes(proveedor);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private static void realizarCompra(Cliente cliente) {
        mostrarProductos();
        System.out.print("Ingrese el ID del producto a comprar: ");
        int productoId = scanner.nextInt();
        System.out.print("Ingrese la cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        try {
            controller.realizarPedido(cliente, productoId, cantidad);
            System.out.println("Compra realizada exitosamente");
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void mostrarPedidos(Cliente cliente) {
        List<Pedido> pedidos = cliente.consultarPedidos();
        if (pedidos.isEmpty()) {
            System.out.println("No tiene pedidos realizados");
            return;
        }

        System.out.println("\n=== Mis Pedidos ===");
        for (Pedido pedido : pedidos) {
            System.out.println("ID: " + pedido.getId());
            System.out.println("Fecha: " + pedido.getFechaPedido());
            System.out.println("Estado: " + pedido.getEstado());
            System.out.println("Total: $" + pedido.getTotal());
            System.out.println("--------------------");
        }
    }

    private static void mostrarProductos() {
        List<Producto> productos = controller.getProductos();
        System.out.println("\n=== Productos Disponibles ===");
        for (Producto producto : productos) {
            System.out.println("ID: " + producto.getId());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Descripción: " + producto.getDescripcion());
            System.out.println("Precio: $" + producto.getPrecio());
            System.out.println("Stock: " + producto.getStock());
            System.out.println("Proveedor: " + producto.getProveedor().getEmpresaNombre());
            System.out.println("--------------------");
        }
    }

    private static void mostrarUsuarios() {
        List<Usuario> usuarios = controller.getUsuarios();
        System.out.println("\n=== Usuarios Registrados ===");
        for (Usuario usuario : usuarios) {
            System.out.println("Tipo: " + usuario.getClass().getSimpleName());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("--------------------");
        }
    }

    private static void registrarProducto(Proveedor proveedor) {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Stock inicial: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        int id = (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
        Producto producto = new Producto(id, nombre, descripcion, precio, stock, proveedor);
        controller.agregarProducto(producto);
        proveedor.registrarInventario(producto);
        System.out.println("Producto registrado exitosamente");
    }

    private static void mostrarInventario(Proveedor proveedor) {
        List<Producto> inventario = proveedor.getInventario();
        if (inventario.isEmpty()) {
            System.out.println("No tiene productos en su inventario");
            return;
        }

        System.out.println("\n=== Mi Inventario ===");
        for (Producto producto : inventario) {
            System.out.println("ID: " + producto.getId());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Stock: " + producto.getStock());
            System.out.println("--------------------");
        }
    }

    private static void mostrarEntregasPendientes(Proveedor proveedor) {
        List<Pedido> entregas = proveedor.getEntregasPendientes();
        if (entregas.isEmpty()) {
            System.out.println("No tiene entregas pendientes");
            return;
        }

        System.out.println("\n=== Entregas Pendientes ===");
        for (Pedido pedido : entregas) {
            System.out.println("ID Pedido: " + pedido.getId());
            System.out.println("Cliente: " + pedido.getCliente().getNombre());
            System.out.println("Fecha: " + pedido.getFechaPedido());
            System.out.println("--------------------");
        }
    }
}