package com.shopplus.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un cliente del sistema.
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
     * Consulta los pedidos del cliente.
     * @return Lista de pedidos del cliente
     */
    public List<Pedido> consultarPedidos() {
        return new ArrayList<>(pedidos);
    }

    @Override
    public String realizarAccion() {
        return "Cliente: Consultando mis pedidos";
    }

    // Getters y setters específicos
    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }
}