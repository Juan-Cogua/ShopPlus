package scr.controller;

import scr.model.*;
import scr.persistence.PersistenciaManager;
import scr.exception.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador principal del sistema ShopPlus.
 */
public class ShopPlusController {
    private List<Usuario> usuarios;
    private List<Producto> productos;
    private PersistenciaManager persistencia;

    /**
     * Constructor del controlador.
     */
    public ShopPlusController() {
        this.usuarios = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.persistencia = new PersistenciaManager();
        cargarDatos();
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * @param usuario Usuario a registrar
     * @throws UsuarioInvalidoException si el email ya existe
     */
    public void registrarUsuario(Usuario usuario) throws UsuarioInvalidoException {
        if (usuarios.stream().anyMatch(u -> u.getEmail().equals(usuario.getEmail()))) {
            throw new UsuarioInvalidoException("El email ya está registrado");
        }
        usuarios.add(usuario);
        persistencia.guardarUsuarios(usuarios);
    }

    /**
     * Autentica un usuario en el sistema.
     * @param email Email del usuario
     * @param contraseña Contraseña del usuario
     * @return Usuario autenticado
     * @throws UsuarioInvalidoException si las credenciales son inválidas
     */
    public Usuario autenticarUsuario(String email, String contraseña) throws UsuarioInvalidoException {
        return usuarios.stream()
            .filter(u -> u.getEmail().equals(email) && u.getContraseña().equals(contraseña))
            .findFirst()
            .orElseThrow(() -> new UsuarioInvalidoException("Credenciales inválidas"));
    }

    /**
     * Agrega un nuevo producto al sistema.
     * @param producto Producto a agregar
     */
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        persistencia.guardarProductos(productos);
    }

    /**
     * Busca un producto por su ID.
     * @param id ID del producto
     * @return Producto encontrado
     * @throws ProductoNoEncontradoException si el producto no existe
     */
    public Producto buscarProducto(int id) throws ProductoNoEncontradoException {
        return productos.stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .orElseThrow(() -> new ProductoNoEncontradoException("Producto no encontrado"));
    }

    /**
     * Realiza un pedido.
     * @param cliente Cliente que realiza el pedido
     * @param productoId ID del producto
     * @param cantidad Cantidad del producto
     * @throws ProductoNoEncontradoException si el producto no existe
     */
    public void realizarPedido(Cliente cliente, int productoId, int cantidad) 
            throws ProductoNoEncontradoException {
        Producto producto = buscarProducto(productoId);
        if (producto.getStock() >= cantidad) {
            Pedido pedido = new Pedido(generarPedidoId(), cliente);
            pedido.agregarProducto(producto, cantidad);
            producto.setStock(producto.getStock() - cantidad);
            cliente.realizarCompra(pedido);
            producto.getProveedor().agregarEntregaPendiente(pedido);
            persistencia.guardarProductos(productos);
        } else {
            throw new ProductoNoEncontradoException("Stock insuficiente");
        }
    }

    /**
     * Obtiene la lista de productos disponibles.
     * @return Lista de productos
     */
    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }

    /**
     * Obtiene la lista de usuarios.
     * @return Lista de usuarios
     */
    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    private void cargarDatos() {
        List<Usuario> usuariosCargados = persistencia.cargarUsuarios();
        List<Proveedor> proveedores = new ArrayList<>();
        for (Usuario usuario : usuariosCargados) {
            if (usuario instanceof Proveedor) {
                proveedores.add((Proveedor) usuario);
            }
        }
        usuarios.addAll(usuariosCargados);
        productos.addAll(persistencia.cargarProductos(proveedores));
    }

    private int generarPedidoId() {
        // Implementación simple para generar IDs únicos
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }
}