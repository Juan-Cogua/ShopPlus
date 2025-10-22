package scr.model;

import scr.controller.ShopPlusController;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que representa a un proveedor del sistema.
 * @author Juan Cogua
 * @version 1.0
 */
public class Proveedor extends Usuario {
    private List<Producto> inventario;
    private List<Pedido> entregasPendientes;
    private String empresaNombre;

    /**
     * Constructor de la clase Proveedor.
     * @param nombre Nombre del proveedor
     * @param email Email del proveedor
     * @param contraseña Contraseña del proveedor
     * @param empresaNombre Nombre de la empresa del proveedor
     */
    public Proveedor(String nombre, String email, String contraseña, String empresaNombre) {
        super(nombre, email, contraseña);
        this.empresaNombre = empresaNombre;
        this.inventario = new ArrayList<>();
        this.entregasPendientes = new ArrayList<>();
    }

    /**
     * Registra un nuevo producto en el inventario.
     * @param producto Producto a registrar
     */
    public void registrarInventario(Producto producto) {
        inventario.add(producto);
    }

    /**
     * Crea y registra un nuevo producto en el inventario.
     * @param id ID del producto
     * @param nombre Nombre del producto
     * @param descripcion Descripción del producto
     * @param precio Precio del producto
     * @param stock Cantidad en stock del producto
     * @return El producto creado y registrado
     */
    public Producto crearProducto(int id, String nombre, String descripcion, double precio, int stock) {
        Producto p = new Producto(id, nombre, descripcion, precio, stock, this);
        registrarInventario(p);
        return p;
    }

    /**
     * Confirma la entrega de un pedido.
     * @param pedido Pedido a confirmar
     */
    public void confirmarEntrega(Pedido pedido) {
        entregasPendientes.remove(pedido);
        pedido.setEstado("Entregado");
    }

    @Override
    public String realizarAccion() {
        return "Proveedor: Actualizando inventario y confirmando entregas";
    }

    /**
     * Registra un nuevo producto a través del controlador ShopPlus.
     * @param controller Controlador de ShopPlus
     * @param scanner Scanner para la entrada de datos
     */
    public void registrarProducto(ShopPlusController controller, Scanner scanner) {
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
        Producto producto = new Producto(id, nombre, descripcion, precio, stock, this);
        controller.agregarProducto(producto);
        this.registrarInventario(producto);
        System.out.println("Producto registrado exitosamente");
    }

    /**
     * Muestra el inventario del proveedor.
     */
    public void mostrarInventario() {
        List<Producto> inventario = this.getInventario();
        if (inventario == null || inventario.isEmpty()) {
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

    /**
     * Muestra las entregas pendientes del proveedor.
     */
    public void mostrarEntregasPendientes() {
        List<Pedido> entregas = this.getEntregasPendientes();
        if (entregas == null || entregas.isEmpty()) {
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

    /**
     * Obtiene el nombre de la empresa del proveedor.
     * @return Nombre de la empresa
     */
    public String getEmpresaNombre() {
        return empresaNombre;
    }

    /**
     * Establece el nombre de la empresa del proveedor.
     * @param empresaNombre Nuevo nombre de la empresa
     */
    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

    /**
     * Obtiene el inventario del proveedor.
     * @return Lista de productos del inventario
     */
    public List<Producto> getInventario() {
        return new ArrayList<>(inventario);
    }

    /**
     * Obtiene las entregas pendientes del proveedor.
     * @return Lista de pedidos pendientes
     */
    public List<Pedido> getEntregasPendientes() {
        return new ArrayList<>(entregasPendientes);
    }

    /**
     * Agrega una entrega pendiente.
     * @param pedido Pedido a agregar
     */
    public void agregarEntregaPendiente(Pedido pedido) {
        entregasPendientes.add(pedido);
    }
}