package dev.lanny.ghost_busters.model;
import java.util.ArrayList;
import java.util.List;


public class HunterModel {
    private String name;
    private List<GhostModel> capturedGhosts;

    public HunterModel(String name) {
        this.name = name;
        this.capturedGhosts = new ArrayList<>();
    }

    public void captureGhost(GhostModel ghost) {
        if (ghost != null) {
            capturedGhosts.add(ghost);
            System.out.println("Fantasma capturado: " + ghost.getName());
        }
    }

    public List<GhostModel> getAllGhosts() {
        return new ArrayList<>(capturedGhosts);
    }


    public String getName() {
        return name;
    }
}
