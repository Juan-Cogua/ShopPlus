package scr.exception;

/**
 * Excepción que se lanza cuando hay un problema con la validación de un usuario.
 * @author Juan Cogua
 * @version 1.0
 */
public class UsuarioInvalidoException extends Exception {
    
    /**
     * Constructor de la excepción.
     * @param mensaje Mensaje descriptivo del error
     */
    public UsuarioInvalidoException(String mensaje) {
        super(mensaje);
    }
}