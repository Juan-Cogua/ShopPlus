package scr.model;

import scr.controller.ShopPlusController;
import scr.exception.ProductoNoEncontradoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que representa a un cliente del sistema.
 * @author Juan Cogua
 * @version 1.0
 */
public class Cliente extends Usuario {
    private List<Pedido> pedidos;
    private String direccionEnvio;

    /**
     * Constructor de la clase Cliente.
     * @param nombre Nombre del cliente
     * @param email Email del cliente
     * @param contraseña Contraseña del cliente
     * @param direccionEnvio Dirección de envío del cliente
     */
    public Cliente(String nombre, String email, String contraseña, String direccionEnvio) {
        super(nombre, email, contraseña);
        this.direccionEnvio = direccionEnvio;
        this.pedidos = new ArrayList<>();
    }

    /**
     * Realiza una compra y la añade a los pedidos del cliente.
     * @param pedido Pedido a realizar
     */
    public void realizarCompra(Pedido pedido) {
        pedidos.add(pedido);
    }

    /**
     * Realiza una compra utilizando el controlador y el escáner para la entrada del usuario.
     * @param controller Controlador de la aplicación
     * @param scanner Escáner para la entrada del usuario
     */
    public void realizarCompra(ShopPlusController controller, Scanner scanner) {
        controller.mostrarProductosConsole();
        System.out.print("Ingrese el ID del producto a comprar: ");
        int productoId = scanner.nextInt();
        System.out.print("Ingrese la cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        try {
            controller.realizarPedido(this, productoId, cantidad);
            System.out.println("Compra realizada exitosamente");
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Consulta los pedidos del cliente.
     * @return Lista de pedidos del cliente
     */
    public List<Pedido> consultarPedidos() {
        return new ArrayList<>(pedidos);
    }

    /**
     * Muestra los pedidos realizados por el cliente.
     */
    public void mostrarPedidos() {
        List<Pedido> pedidos = this.consultarPedidos();
        if (pedidos == null || pedidos.isEmpty()) {
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

    @Override
    public String realizarAccion() {
        return "Cliente: Consultando mis pedidos";
    }

    /**
     * Obtiene la dirección de envío del cliente.
     * @return Dirección de envío
     */
    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    /**
     * Establece la dirección de envío del cliente.
     * @param direccionEnvio Nueva dirección de envío
     */
    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }
}