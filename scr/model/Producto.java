package com.shopplus.model;

/**
 * Clase que representa un producto en el sistema.
 */
public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private Proveedor proveedor;

    /**
     * Constructor de la clase Producto.
     * @param id Identificador único del producto
     * @param nombre Nombre del producto
     * @param descripcion Descripción del producto
     * @param precio Precio del producto
     * @param stock Cantidad disponible del producto
     * @param proveedor Proveedor del producto
     */
    public Producto(int id, String nombre, String descripcion, double precio, int stock, Proveedor proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.proveedor = proveedor;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}