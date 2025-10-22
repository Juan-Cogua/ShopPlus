package scr.exception;

/**
 * Excepción que se lanza cuando no se encuentra un producto en el sistema.
 * @author Juan Cogua
 * @version 1.0
 */
public class ProductoNoEncontradoException extends Exception {
    /**
     * Constructor de la excepción.
     * @param mensaje Mensaje descriptivo del error
     */
    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}