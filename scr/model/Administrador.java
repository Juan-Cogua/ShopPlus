package com.shopplus.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un administrador del sistema.
 */
public class Administrador extends Usuario {
    private List<Producto> productosGestionados;
    private List<Usuario> usuariosGestionados;

    /**
     * Constructor de la clase Administrador.
     * @param nombre Nombre del administrador
     * @param email Email del administrador
     * @param contraseña Contraseña del administrador
     */
    public Administrador(String nombre, String email, String contraseña) {
        super(nombre, email, contraseña);
        this.productosGestionados = new ArrayList<>();
        this.usuariosGestionados = new ArrayList<>();
    }

    /**
     * Añade un producto al sistema.
     * @param producto Producto a añadir
     */
    public void agregarProducto(Producto producto) {
        productosGestionados.add(producto);
    }

    /**
     * Elimina un producto del sistema.
     * @param producto Producto a eliminar
     */
    public void eliminarProducto(Producto producto) {
        productosGestionados.remove(producto);
    }

    /**
     * Añade un usuario al sistema.
     * @param usuario Usuario a añadir
     */
    public void agregarUsuario(Usuario usuario) {
        usuariosGestionados.add(usuario);
    }

    /**
     * Elimina un usuario del sistema.
     * @param usuario Usuario a eliminar
     */
    public void eliminarUsuario(Usuario usuario) {
        usuariosGestionados.remove(usuario);
    }

    @Override
    public String realizarAccion() {
        return "Administrador: Gestionando productos y usuarios";
    }

    // Getters para las listas
    public List<Producto> getProductosGestionados() {
        return new ArrayList<>(productosGestionados);
    }

    public List<Usuario> getUsuariosGestionados() {
        return new ArrayList<>(usuariosGestionados);
    }
}