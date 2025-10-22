package scr.model;

/**
 * Clase que representa un producto en el sistema.
 * @author Juan Cogua
 * @version 1.0
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

    /**
     * Obtiene el ID del producto.
     * @return ID del producto
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre del producto.
     * @return Nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * @param nombre Nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del producto.
     * @return Descripción del producto
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del producto.
     * @param descripcion Nueva descripción
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio del producto.
     * @return Precio del producto
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     * @param precio Nuevo precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el stock disponible.
     * @return Cantidad en stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Establece el stock del producto.
     * @param stock Nueva cantidad en stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Obtiene el proveedor asociado.
     * @return Proveedor del producto
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * Establece el proveedor del producto.
     * @param proveedor Nuevo proveedor
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}