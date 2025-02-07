package dev.lanny.ghost_busters.view;

import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.GhostModel;
import java.util.List;

public class ListGhostsView {

   

    public static void displayCapturedGhosts(HunterController hunterController) {
        // Obtener la lista de fantasmas capturados desde el controlador
        List<GhostModel> ghosts = hunterController.getCapturedGhosts();

        // Mostrar los fantasmas capturados o el mensaje si no hay ninguno
        System.out.println("============================================");
        System.out.println("      Ver Lista de Fantasmas Capturados     ");
        System.out.println("============================================");

        if (ghosts.isEmpty()) {
            System.out.println("❌ No hay fantasmas capturados.");
        } else {
            // Ajustando el espacio entre las columnas (20 caracteres para el nombre, 15 para la clase, etc.)
            System.out.printf("%-5s %-20s %-15s %-12s %-12s%n", "ID", "Nombre", "Clase", "Peligro", "Fecha");
            System.out.println("------------------------------------------------------------");
            for (GhostModel ghost : ghosts) {
                // Ajustando el espacio entre las columnas
                System.out.printf("%-5d %-20s %-15s %-12s %-12s%n",
                        ghost.getId(),
                        ghost.getName(),
                        ghost.getGhostClass().name(),
                        ghost.getThreatLevel().name(),
                        ghost.getCaptureDate());
            }
            System.out.println("------------------------------------------------------------");
            System.out.printf("Total de fantasmas capturados: %d%n", ghosts.size());
        }
    }
}



