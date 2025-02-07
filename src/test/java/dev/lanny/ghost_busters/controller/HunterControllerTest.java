package dev.lanny.ghost_busters.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.lanny.ghost_busters.model.GhostClass;
import dev.lanny.ghost_busters.model.GhostModel;
import dev.lanny.ghost_busters.model.HunterModel;
import dev.lanny.ghost_busters.model.ThreatLevel;


class HunterControllerTest {
    private HunterModel hunterModel;
    private HunterController hunterController;
    private GhostModel ghost;
    private List<GhostModel> ghosts;


    @BeforeEach
    void setUp() {
        HunterModel hunterModel = new HunterModel("Egon Spengler", ghosts);        
        hunterController = new HunterController(hunterModel);
        ghost = new GhostModel("Slimer", GhostClass.CLASS_II, ThreatLevel.CRITICAL, "Sliming", "2025-02-07");
    }

    @Test
    public void testCaptureGhost_Success() {
        boolean result = hunterController.captureGhost(hunterModel.getId(), ghost);
        assertThat(result, is(true));
        assertThat(hunterModel.capturedGhosts(), hasSize(1));
    }

    @Test
    void testCaptureNullGhostThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            hunterController.captureGhost(null);
        });
        assertEquals("El fantasma no puede ser nulo.", exception.getMessage());
    }
   
}


