package scr;

import scr.controller.ShopPlusController;
import scr.model.*;
import scr.exception.*;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que maneja la interfaz de usuario del sistema ShopPlus.
 */
public class Main {
    private static ShopPlusController controller;
    private static Scanner scanner;
    private static Usuario usuarioActual;

    public static void main(String[] args) {
        controller = new ShopPlusController();
        scanner = new Scanner(System.in);

        while (true) {
            mostrarMenuPrincipal();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    System.out.println("¡Gracias por usar ShopPlus!");
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    // ... resto del código igual ...
}