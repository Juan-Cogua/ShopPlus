package com.shopplus.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un proveedor del sistema.
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

    // Getters y setters específicos
    public String getEmpresaNombre() {
        return empresaNombre;
    }

    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

    public List<Producto> getInventario() {
        return new ArrayList<>(inventario);
    }

    public List<Pedido> getEntregasPendientes() {
        return new ArrayList<>(entregasPendientes);
    }

    public void agregarEntregaPendiente(Pedido pedido) {
        entregasPendientes.add(pedido);
    }
}