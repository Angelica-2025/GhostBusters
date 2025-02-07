package dev.lanny.ghost_busters.model;

import java.util.List;

public class HunterModel {
    private String name;
    private List<GhostModel> ghostList;

    public HunterModel(String name, List<GhostModel> ghostList) {
        this.name = name;
        this.ghostList = ghostList;
    }

    public String getName() { return name; }
    public List<GhostModel> getGhostList() { return ghostList; }

    public void capturedGhosts(GhostModel ghost) { 
        ghostList.add(ghost);
    }

    public void freeGhost(int id) {
        ghostList.removeIf(g -> g.getId() == id);
    }
}
