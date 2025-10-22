package scr.controller;

import scr.model.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja la persistencia de datos en archivos de texto.
 * @author Juan Cogua
 * @version 1.0
 */
public class PersistenciaManager {
    private static final String PRODUCTOS_FILE = "productos.txt";
    private static final String USUARIOS_FILE = "usuarios.txt";

    /**
     * Guarda la lista de productos en archivo.
     * @param productos Lista de productos a guardar
     */
    public void guardarProductos(List<Producto> productos) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PRODUCTOS_FILE))) {
            for (Producto producto : productos) {
                writer.println(String.format("%d,%s,%s,%.2f,%d,%s",
                    producto.getId(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getStock(),
                    producto.getProveedor().getEmail()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga productos desde archivo relacionándolos con proveedores.
     * @param proveedores Lista de proveedores disponibles
     * @return Lista de productos cargados
     */
    public List<Producto> cargarProductos(List<Proveedor> proveedores) {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PRODUCTOS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    int id = Integer.parseInt(parts[0]);
                    String nombre = parts[1];
                    String descripcion = parts[2];
                    double precio = Double.parseDouble(parts[3]);
                    int stock = Integer.parseInt(parts[4]);
                    String proveedorEmail = parts[5];

                    Proveedor proveedor = buscarProveedor(proveedores, proveedorEmail);
                    if (proveedor != null) {
                        productos.add(new Producto(id, nombre, descripcion, precio, stock, proveedor));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productos;
    }

    /**
     * Guarda la lista de usuarios en archivo.
     * @param usuarios Lista de usuarios a guardar
     */
    public void guardarUsuarios(List<Usuario> usuarios) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USUARIOS_FILE))) {
            for (Usuario usuario : usuarios) {
                String tipo = usuario.getClass().getSimpleName();
                writer.print(String.format("%s,%s,%s,%s",
                    tipo,
                    usuario.getNombre(),
                    usuario.getEmail(),
                    usuario.getContraseña()));

                if (usuario instanceof Cliente) {
                    writer.println("," + ((Cliente) usuario).getDireccionEnvio());
                } else if (usuario instanceof Proveedor) {
                    writer.println("," + ((Proveedor) usuario).getEmpresaNombre());
                } else {
                    writer.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga usuarios desde archivo.
     * @return Lista de usuarios cargados
     */
    public List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USUARIOS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String tipo = parts[0];
                    String nombre = parts[1];
                    String email = parts[2];
                    String contraseña = parts[3];

                    switch (tipo) {
                        case "Cliente":
                            if (parts.length > 4) {
                                usuarios.add(new Cliente(nombre, email, contraseña, parts[4]));
                            }
                            break;
                        case "Administrador":
                            usuarios.add(new Administrador(nombre, email, contraseña));
                            break;
                        case "Proveedor":
                            if (parts.length > 4) {
                                usuarios.add(new Proveedor(nombre, email, contraseña, parts[4]));
                            }
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    /**
     * Busca un proveedor por email en una lista.
     * @param proveedores Lista de proveedores
     * @param email Email buscado
     * @return Proveedor encontrado o null
     */
    private Proveedor buscarProveedor(List<Proveedor> proveedores, String email) {
        return proveedores.stream()
            .filter(p -> p.getEmail().equals(email))
            .findFirst()
            .orElse(null);
    }
}