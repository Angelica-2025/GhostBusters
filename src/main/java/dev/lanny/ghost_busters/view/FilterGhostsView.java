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
        
        // Mostrar opciones con números
        int index = 1;
        for (GhostClass ghostClass : GhostClass.values()) {
            System.out.println(index + ". " + ghostClass.name());
            index++;
        }
    
        System.out.print("Ingrese el número correspondiente a la clase: ");
        try {
            // Leer entrada del usuario como número
            int classInput = Integer.parseInt(scanner.nextLine().trim());
            
            // Validar rango del número ingresado
            if (classInput < 1 || classInput > GhostClass.values().length) {
                throw new IllegalArgumentException("❌ Clase de fantasma no válida. Intente de nuevo.");
            }
    
            // Convertir número en clase
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
    
}
