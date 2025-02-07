package dev.lanny.ghost_busters.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.lanny.ghost_busters.model.GhostClass;
import dev.lanny.ghost_busters.model.GhostModel;
import dev.lanny.ghost_busters.model.HunterModel;
import dev.lanny.ghost_busters.model.ThreatLevel;

import java.util.ArrayList;

class HunterControllerTest {
    private HunterModel hunterModel;
    private HunterController hunterController;
    private GhostModel ghost;

    @BeforeEach
    void setUp() {

        List<GhostModel> ghosts = new ArrayList<>();
        hunterModel = new HunterModel("Egon Spengler", ghosts);
        hunterController = new HunterController(hunterModel);
        ghost = new GhostModel("Slimer", GhostClass.CLASS_II, ThreatLevel.CRITICAL, "Sliming", "2025-02-07");
    }

    @Test
    public void testCaptureGhost_Success() {
        hunterController.captureGhost(ghost);

        assertThat(hunterModel.getGhostList(), hasSize(1));
        assertThat(hunterModel.getGhostList().get(0), is(ghost));
    }

    @Test
    void testCaptureNullGhostThrowsException() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            hunterController.captureGhost(null);
        });
        assertEquals("El fantasma no puede ser nulo.", exception.getMessage());
    }
}
