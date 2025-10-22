package scr.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Clase que representa un pedido en el sistema.
 * @author Juan Cogua
 * @version 1.0
 */
public class Pedido {
    private int id;
    private Cliente cliente;
    private List<Producto> productos;
    private Date fechaPedido;
    private String estado;
    private double total;

    /**
     * Constructor de la clase Pedido.
     * @param id Identificador único del pedido
     * @param cliente Cliente que realiza el pedido
     */
    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.fechaPedido = new Date();
        this.estado = "Pendiente";
        this.total = 0.0;
    }

    /**
     * Añade un producto al pedido.
     * @param producto Producto a añadir
     * @param cantidad Cantidad del producto
     */
    public void agregarProducto(Producto producto, int cantidad) {
        productos.add(producto);
        total += producto.getPrecio() * cantidad;
    }

    /**
     * Obtiene el ID del pedido.
     * @return ID del pedido
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el cliente del pedido.
     * @return Cliente asociado al pedido
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Obtiene la lista de productos del pedido.
     * @return Lista de productos
     */
    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }

    /**
     * Obtiene la fecha del pedido.
     * @return Fecha de creación del pedido
     */
    public Date getFechaPedido() {
        return fechaPedido;
    }

    /**
     * Obtiene el estado del pedido.
     * @return Estado actual del pedido
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del pedido.
     * @param estado Nuevo estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el total del pedido.
     * @return Total acumulado del pedido
     */
    public double getTotal() {
        return total;
    }
}