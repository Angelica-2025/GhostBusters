package dev.lanny.ghost_busters.view;

import java.util.List;
import java.util.Scanner;
import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.GhostClass;
import dev.lanny.ghost_busters.model.GhostModel;


public class FilterGhostsView {
    
    public static void filterGhostsByClass(HunterController hunterController, Scanner scanner) {
        System.out.println("\n============================");
        System.out.println("   Filtrar Fantasmas por Clase    ");
        System.out.println("============================");
        System.out.println("Seleccione la clase de fantasma para filtrar:");
        for (GhostClass ghostClass : GhostClass.values()) {
            System.out.println("- " + ghostClass.name());
        }
        
        String classInput = scanner.nextLine().toUpperCase().trim();
        try {
            GhostClass selectedClass = GhostClass.valueOf(classInput);
            List<GhostModel> filteredGhosts = hunterController.filterGhostsByClass(selectedClass);
            
            if (filteredGhosts.isEmpty()) {
                System.out.println("❌ No hay fantasmas de esta clase capturados.");
            } else {
                System.out.println("\nFantasmas de clase " + selectedClass + " capturados:");
                for (GhostModel ghost : filteredGhosts) {
                    System.out.println("- " + ghost.getName() + " (Peligrosidad: " + ghost.getThreatLevel() + ")");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Clase de fantasma no válida. Intente de nuevo.");
        }
    }
}
