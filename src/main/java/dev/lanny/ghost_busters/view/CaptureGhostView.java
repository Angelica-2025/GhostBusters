package dev.lanny.ghost_busters.view;

import java.util.Scanner;

import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.GhostClass;
import dev.lanny.ghost_busters.model.GhostModel;
import dev.lanny.ghost_busters.model.ThreatLevel;

public class CaptureGhostView {
    public static void captureGhost(HunterController hunterController, Scanner scanner) {
        System.out.println("============================================");
        System.out.println("      Capturar un Nuevo Fantasma      ");
        System.out.println("============================================");

        String name = promptForNonEmptyInput(scanner, "Ingrese el nombre del fantasma: ",
                "❌ El nombre no puede estar vacío. Inténtelo de nuevo.");
        GhostClass ghostClass = selectGhostClass(scanner);
        ThreatLevel threatLevel = selectThreatLevel(scanner);
        String specialAbility = promptForNonEmptyInput(scanner, "Ingrese la habilidad especial del fantasma: ",
                "❌ La habilidad no puede estar vacía. Inténtelo de nuevo.");
        String captureDate = validateCaptureDate(scanner);
        int ectoplasmicAffinity = calculateEctoplasmicAffinity();

        try {
            GhostModel capturedGhost = new GhostModel(name, ghostClass, threatLevel, specialAbility, captureDate);
            hunterController.captureGhost(capturedGhost);
            System.out.flush();
            System.out.println("✅ ¡Fantasma capturado satisfactoriamente!");
            System.out.println("Detalles del Fantasma:");
            System.out.println("--------------------------------------------------");
            System.out.printf("Nombre: %s%n", capturedGhost.getName());
            System.out.printf("Clase: %s%n", capturedGhost.getGhostClass().name());
            System.out.printf("Nivel de Peligro: %s%n", capturedGhost.getThreatLevel().name());
            System.out.printf("Habilidad: %s%n", capturedGhost.getSpecialAbility());
            System.out.printf("Fecha de Captura: %s%n", capturedGhost.getCaptureDate());
            System.out.printf("Nivel de Afinidad Ectoplásmica: %d/10%n", ectoplasmicAffinity);
            System.out.println("--------------------------------------------------");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static String promptForNonEmptyInput(Scanner scanner, String prompt, String errorMessage) {
        String input;
        do {
            System.out.print(prompt);
            if (scanner.hasNext()) {
                input = scanner.nextLine().trim();
                if (!input.isEmpty()) {
                    return input;
                }
            } else {
                System.out.println("❌ No se recibió ninguna entrada. Intente de nuevo.");
                scanner.nextLine();
            }
            System.out.println(errorMessage);
        } while (true);
    }

    private static GhostClass selectGhostClass(Scanner scanner) {
        while (true) {
            System.out.println("Seleccione la clase del fantasma:");
            for (GhostClass ghostClass : GhostClass.values()) {
                System.out.println((ghostClass.ordinal() + 1) + ". " + ghostClass.getDescription());
            }

            System.out.print("Ingrese el número de la clase: ");
            if (scanner.hasNext()) {
                try {
                    int classOption = Integer.parseInt(scanner.nextLine());
                    if (classOption >= 1 && classOption <= GhostClass.values().length) {
                        return GhostClass.values()[classOption - 1];
                    }
                } catch (NumberFormatException e) {

                }
            } else {
                System.out.println("❌ No se recibió ninguna entrada válida.");
                scanner.nextLine();
            }
            System.out.println("❌ Opción inválida. Por favor, seleccione un número válido.");
        }
    }

    private static ThreatLevel selectThreatLevel(Scanner scanner) {
        while (true) {
            System.out.println("Seleccione el nivel de peligro:");
            for (ThreatLevel level : ThreatLevel.values()) {
                System.out.println((level.ordinal() + 1) + ". " + level);
            }

            System.out.print("Ingrese el número del nivel de peligro: ");
            if (scanner.hasNext()) {
                try {
                    int levelOption = Integer.parseInt(scanner.nextLine());
                    if (levelOption >= 1 && levelOption <= ThreatLevel.values().length) {
                        return ThreatLevel.values()[levelOption - 1];
                    }
                } catch (NumberFormatException e) {

                }
            } else {
                System.out.println("❌ No se recibió ninguna entrada válida.");
                scanner.nextLine();
            }
            System.out.println("❌ Opción inválida. Por favor, seleccione un número válido.");
        }
    }

    private static String validateCaptureDate(Scanner scanner) {
        while (true) {
            System.out.print("Ingrese la fecha de captura (YYYY-MM-DD): ");
            if (scanner.hasNext()) {
                String captureDate = scanner.nextLine().trim();
                if (captureDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    return captureDate;
                }
            } else {
                System.out.println("❌ No se recibió ninguna entrada válida.");
                scanner.nextLine();
            }
            System.out.println("❌ Formato inválido. Por favor, use el formato YYYY-MM-DD.");
        }
    }

    private static int calculateEctoplasmicAffinity() {
        return (int) (Math.random() * 10) + 1;
    }
}
