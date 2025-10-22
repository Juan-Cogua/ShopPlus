package com.shopplus.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Clase que representa un pedido en el sistema.
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

    // Getters y setters
    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }
}