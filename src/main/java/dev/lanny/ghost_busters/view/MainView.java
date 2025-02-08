package dev.lanny.ghost_busters.view;

import java.util.ArrayList;
import java.util.Scanner;
import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.HunterModel;

public class MainView {
    private final HunterController hunterController;
    private final Scanner scanner;

    public MainView(HunterController hunterController, Scanner scanner) {
        this.hunterController = hunterController;
        this.scanner = scanner;
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("============================================");
            System.out.println(" ¡Bienvenido a la Base Ghostbusters Asturias!");
            System.out.println("============================================");
            System.out.println("Gestiona tus fantasmas atrapados y protege la región");
            System.out.println("============================================");
            System.out.println("Opciones:");
            System.out.println("1. Capturar un nuevo fantasma");
            System.out.println("2. Ver lista de fantasmas capturados");
            System.out.println("3. Liberar un fantasma");
            System.out.println("4. Filtrar fantasmas por clase");
            System.out.println("5. Ver fantasmas capturados en un mes");
            System.out.println("6. Salir");
            System.out.print("Por favor, selecciona una opción (1-6): ");

            if (scanner.hasNext()) {
                String input = scanner.nextLine().trim();

                try {
                    int option = Integer.parseInt(input);
                    switch (option) {
                        case 1:
                            CaptureGhostView.captureGhost(hunterController, scanner);
                            break;
                        case 2:
                            ListGhostsView.displayCapturedGhosts(hunterController);
                            break;
                        case 3:
                            System.out.println("Eliminar un fantasma");
                            break;
                        case 4:
                            FilterGhostsView.filterGhostsByClass(hunterController, scanner);
                            break;
                        case 5:
                            System.out.println("Gracias por proteger Asturias. ¡Hasta la próxima!");
                            return;
                        case 6:
                            System.out.println("Gracias por proteger Asturias. ¡Hasta la próxima!");
                            return;
                        default:
                            System.out.println("❌ Opción inválida. Por favor, seleccione un número del 1 al 6.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("❌ Entrada inválida. Por favor, ingrese un número válido.");
                }
            } else {
                System.out.println("❌ No hay más entradas disponibles. Saliendo del programa.");
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HunterModel hunterModel = new HunterModel("Egon Spengler", new ArrayList<>());
        HunterController hunterController = new HunterController(hunterModel);
        MainView mainView = new MainView(hunterController, scanner);
        mainView.showMainMenu();
    }
}
