package com.shopplus.exception;

/**
 * Excepción que se lanza cuando no se encuentra un producto en el sistema.
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