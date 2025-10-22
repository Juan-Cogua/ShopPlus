package scr.model;

/**
 * Clase base que representa a un usuario del sistema.
 * @author Juan Cogua
 * @version 1.0
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

    /**
     * Obtiene el nombre del usuario.
     * @return Nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * @param nombre Nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el email del usuario.
     * @return Email del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del usuario.
     * @param email Nuevo email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return Contraseña del usuario
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Establece la contraseña del usuario.
     * @param contraseña Nueva contraseña
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * Método abstracto que define el comportamiento específico de cada tipo de usuario.
     * @return String que describe la acción realizada
     */
    public abstract String realizarAccion();
}