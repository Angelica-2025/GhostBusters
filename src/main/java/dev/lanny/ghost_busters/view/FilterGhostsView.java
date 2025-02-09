package dev.lanny.ghost_busters.view;

import java.util.List;
import java.util.Scanner;
import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.GhostClass;
import dev.lanny.ghost_busters.model.GhostModel;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class FilterGhostsView {

    public static void filterGhostsByClass(HunterController hunterController, Scanner scanner) {
        System.out.println("\n============================");
        System.out.println("   Filtrar Fantasmas por Clase    ");
        System.out.println("============================");
        System.out.println("Seleccione la clase de fantasma para filtrar:");

        int index = 1;
        for (GhostClass ghostClass : GhostClass.values()) {
            System.out.println(index + ". " + ghostClass.name());
            index++;
        }

        System.out.print("Ingrese el número correspondiente a la clase: ");
        try {
            int classInput = Integer.parseInt(scanner.nextLine().trim());

            if (classInput < 1 || classInput > GhostClass.values().length) {
                throw new IllegalArgumentException("❌ Clase de fantasma no válida. Intente de nuevo.");
            }

            GhostClass selectedClass = GhostClass.values()[classInput - 1];
            List<GhostModel> filteredGhosts = hunterController.filterGhostsByClass(selectedClass);

            if (filteredGhosts.isEmpty()) {
                System.out.println("❌ No hay fantasmas de esta clase capturados.");
            } else {
                System.out.println("\nFantasmas de clase " + selectedClass + " capturados:");
                for (GhostModel ghost : filteredGhosts) {
                    System.out.println("- " + ghost.getName() + " (Peligrosidad: " + ghost.getThreatLevel() + ")");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida. Por favor, ingrese un número válido.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void filterGhostsByMonth(HunterController hunterController, Scanner scanner) {
        System.out.println("\n============================");
        System.out.println("   Filtrar Fantasmas por Mes y Año    ");
        System.out.println("============================");

        try {
            System.out.print("Ingrese el mes (1-12): ");
            int month = Integer.parseInt(scanner.nextLine().trim());

            if (month < 1 || month > 12) {
                throw new IllegalArgumentException("❌ El mes debe estar entre 1 y 12.");
            }

            System.out.print("Ingrese el año (YYYY): ");
            int year = Integer.parseInt(scanner.nextLine().trim());

            if (year < 1900 || year > 2100) {
                throw new IllegalArgumentException("❌ Año inválido. Intente de nuevo.");
            }

            List<GhostModel> filteredGhosts = hunterController.filterGhostsByMonth(month, year);

            if (filteredGhosts.isEmpty()) {
                String monthName = Month.of(month).getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es"));
                monthName = monthName.substring(0, 1).toUpperCase() + monthName.substring(1);
                System.out.println("❌ No se encontraron fantasmas capturados en " + monthName + " de " + year + ".");
            } else {
                String monthName = Month.of(month).getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es"));
                System.out.println("\n📆 Fantasmas capturados en " + monthName + " de " + year + ":");
                for (GhostModel ghost : filteredGhosts) {
                    System.out.println("- " + ghost.getName() +
                            " (Clase: " + ghost.getGhostClass().name() + " - " + ghost.getGhostClass().getDescription()
                            +
                            ", Peligrosidad: " + ghost.getThreatLevel().name() + " - "
                            + ghost.getThreatLevel().getDescription() + ")");

                }
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida. Por favor, ingrese un mes válido (1-12).");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}
