package dev.lanny.ghost_busters.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.lanny.ghost_busters.model.GhostClass;
import dev.lanny.ghost_busters.model.GhostModel;
import dev.lanny.ghost_busters.model.HunterModel;
import dev.lanny.ghost_busters.model.ThreatLevel;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class HunterControllerTest {
    private HunterModel hunter;
    private HunterController controller;
    private GhostModel ghost1;
    private GhostModel ghost2;

    @BeforeEach
    void setUp() {
        hunter = new HunterModel("Peter Venkman");
        controller = new HunterController(hunter);
        ghost1 = new GhostModel("Spirit A", GhostClass.CLASS_I, ThreatLevel.LOW, "Whispering", "2025-02-01");
        ghost2 = new GhostModel("Spirit B", GhostClass.CLASS_I, ThreatLevel.LOW, "Whispering", "2025-02-01");
    }

    @Test
    void testCaptureGhost() {
        controller.captureGhost(ghost1);
        assertEquals(1, controller.getAllGhosts().size());
        assertTrue(controller.getAllGhosts().contains(ghost1));
    }

    @Test
    void testGetAllGhosts() {
        controller.captureGhost(ghost1);
        controller.captureGhost(ghost2);
        List<GhostModel> ghosts = controller.getAllGhosts();
        assertEquals(2, ghosts.size());
    }

    

    

    
}
