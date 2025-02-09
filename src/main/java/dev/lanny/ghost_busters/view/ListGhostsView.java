package dev.lanny.ghost_busters.view;

import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.GhostModel;
import java.util.List;

public class ListGhostsView {

    public static void displayCapturedGhosts(HunterController hunterController) {
        List<GhostModel> ghosts = hunterController.getCapturedGhosts();

        System.out.println("============================================");
        System.out.println("      Ver Lista de Fantasmas Capturados     ");
        System.out.println("============================================");

        if (ghosts.isEmpty()) {
            System.out.println("❌ No hay fantasmas capturados.");
        } else {

            System.out.printf("%-5s %-20s %-15s %-12s %-12s%n", "ID", "Nombre", "Clase", "Peligro", "Fecha");
          
            System.out.println("----------------------------------------------------------------------------");
            for (GhostModel ghost : ghosts) {
                System.out.printf("%-5d %-30s %-20s %-15s %-15s%n",
                        ghost.getId(),
                        ghost.getName(),
                        ghost.getGhostClass().name(),
                        ghost.getThreatLevel().name(),
                        ghost.getCaptureDate());
            }
            System.out.println("----------------------------------------------------------------------------");
            System.out.printf("Total de fantasmas capturados: %d%n", ghosts.size());
        }
    }
}
