package dev.lanny.ghost_busters.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HunterModel {
    private final String name;
    private final List<GhostModel> ghostList;

    public HunterModel(String name, List<GhostModel> ghostList) {
        this.name = Objects.requireNonNull(name, "El nombre del cazador no puede ser nulo.");
        this.ghostList = (ghostList == null) ? new ArrayList<>() : new ArrayList<>(ghostList); // ✅ Se asegura que nunca sea null
    }

    public String getName() { return name; }

    public List<GhostModel> getGhostList() { 
        return new ArrayList<>(ghostList); // ✅ Retorna una copia para evitar modificaciones externas
    }

    public void captureGhost(GhostModel ghost) { 
        Objects.requireNonNull(ghost, "El fantasma no puede ser nulo.");
        ghostList.add(ghost);
    }

    public boolean freeGhost(int id) {
        return ghostList.removeIf(g -> g.getId() == id);
    }
}
