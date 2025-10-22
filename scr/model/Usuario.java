package com.shopplus.model;

/**
 * Clase base que representa a un usuario del sistema.
 */
public abstract class Usuario {
    protected String nombre;
    protected String email;
    protected String contraseña;

    /**
     * Constructor de la clase Usuario.
     * @param nombre Nombre del usuario
     * @param email Email del usuario
     * @param contraseña Contraseña del usuario
     */
    public Usuario(String nombre, String email, String contraseña) {
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * Método abstracto que define el comportamiento específico de cada tipo de usuario.
     * @return String que describe la acción realizada
     */
    public abstract String realizarAccion();
}