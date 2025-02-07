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
            System.out.println("1. Capturar un nuevo fantasma");
            System.out.println("2. Ver lista de fantasmas capturados");
            System.out.println("3. Eliminar un fantasma");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

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
                    System.out.println("Elimina un fantasma");
                        break;
                    case 4:
                        System.out.println("Gracias por proteger Asturias. ¡Hasta la próxima!");
                        return;
                    default:
                        System.out.println("❌ Opción inválida. Por favor, seleccione un número del 1 al 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Por favor, ingrese un número válido.");
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
