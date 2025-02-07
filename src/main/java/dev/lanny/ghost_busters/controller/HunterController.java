package dev.lanny.ghost_busters.controller;

import java.util.List;

import dev.lanny.ghost_busters.model.GhostModel;
import dev.lanny.ghost_busters.model.HunterModel;

public class HunterController {
    private HunterModel hunter;

    public HunterController(HunterModel hunter) {
        this.hunter = hunter;
    }

    public void captureGhost(GhostModel ghost) {
        if (ghost != null) {
            hunter.captureGhost(ghost);
        }
    }

    public List<GhostModel> getAllGhosts() {
        return hunter.getAllGhosts();
    }


   
}

