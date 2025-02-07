package dev.lanny.ghost_busters.model;
import java.util.ArrayList;
import java.util.List;



public class HunterModel {
    private String name;
    private List<GhostModel> capturedGhosts;

    public HunterModel(String name, ArrayList<GhostModel> capturedGhosts) {
        this.name = name;
        this.capturedGhosts = capturedGhosts != null ? capturedGhosts : new ArrayList<>();
    }

    public void captureGhost(GhostModel ghost) {
        if (ghost != null) {
            capturedGhosts.add(ghost);
        }
    }

    public List<GhostModel> getCapturedGhosts() {
        return new ArrayList<>(capturedGhosts);
    }

    public boolean freeGhost(int ghostId) {
        return capturedGhosts.removeIf(ghost -> ghost.getId() == ghostId);
    }

}

