package dev.lanny.ghost_busters.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;


import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.lanny.ghost_busters.model.GhostClass;
import dev.lanny.ghost_busters.model.GhostModel;
import dev.lanny.ghost_busters.model.HunterModel;
import dev.lanny.ghost_busters.model.ThreatLevel;


public class HunterControllerTest {

    private HunterModel hunterModel;
    private HunterController hunterController;
    private GhostModel ghost1;

    @BeforeEach
    public void setUp() {
        hunterModel = new HunterModel("Carlos", new ArrayList<>());
        hunterController = new HunterController(hunterModel);
        ghost1 = new GhostModel("Marinero", GhostClass.CLASS_I, ThreatLevel.MEDIUM, "Aparece en tormentas",
                "2025-02-03");
    }

    @Test
    public void testCaptureGhost_Success() {
        hunterController.captureGhost(ghost1);

        List<GhostModel> capturedGhosts = hunterModel.getCapturedGhosts();
        assertThat(capturedGhosts, hasSize(1));
        assertThat(capturedGhosts.get(0), is(ghost1));
    }

    @Test
    public void testGetCapturedGhostsInitiallyEmpty() {
        List<GhostModel> capturedGhosts = hunterController.getCapturedGhosts();
        assertThat(capturedGhosts, is(empty()));
    }

    @Test
    public void testGetCapturedGhostsAfterCapture() {
        GhostModel ghost = new GhostModel("Marinero", GhostClass.CLASS_I, ThreatLevel.MEDIUM, "Aparece en tormentas",
                "2025-02-03");
        hunterModel.captureGhost(ghost);
        List<GhostModel> capturedGhosts = hunterController.getCapturedGhosts();
        assertThat(capturedGhosts, contains(ghost));
    }

    

    

    
}
