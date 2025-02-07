package dev.lanny.ghost_busters.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;

class HunterModelTest {
    private HunterModel hunter;
    private GhostModel ghost1;
    private GhostModel ghost2;

    @BeforeEach
    void setUp() {
        hunter = new HunterModel(1, "Egon Spengler");
        ghost1 = new GhostModel("Slimer", GhostClass.CLASS_II, ThreatLevel.HIGH, "Sliming", "2025-02-07");
        ghost2 = new GhostModel("Casper", GhostClass.CLASS_I, ThreatLevel.LOW, "Friendliness", "2025-02-08");
    }

    @Test
    void testHunterInitialization() {
        assertEquals(1, hunter.getId());
        assertEquals("Egon Spengler", hunter.getName());
        assertTrue(hunter.getCapturedGhosts().isEmpty());
    }

    @Test
    void testCaptureGhost() {
        hunter.captureGhost(ghost1);
        assertEquals(1, hunter.getCapturedGhosts().size());
    }

    @Test
    void testFreeGhost() {
        hunter.captureGhost(ghost1);
        assertTrue(hunter.freeGhost(ghost1.getId()));
        assertTrue(hunter.getCapturedGhosts().isEmpty());
    }

    @Test
    void testFilterGhostsByClass() {
        hunter.captureGhost(ghost1);
        hunter.captureGhost(ghost2);
        List<GhostModel> poltergeists = hunter.filterGhostsByClass(GhostClass.CLASS_II);
        assertEquals(1, poltergeists.size());
        assertEquals("Slimer", poltergeists.get(0).getName());
    }

    @Test
    void testFilterGhostsByDate() {
        hunter.captureGhost(ghost1);
        hunter.captureGhost(ghost2);
        List<GhostModel> ghostsOnFeb7 = hunter.filterGhostsByDate(LocalDate.of(2025, 2, 7));
        assertEquals(1, ghostsOnFeb7.size());
        assertEquals("Slimer", ghostsOnFeb7.get(0).getName());
    }

    @Test
    void testReleaseLessDangerousGhosts() {
        hunter.captureGhost(ghost1);
        hunter.captureGhost(ghost2);
        int releasedCount = hunter.releaseLessDangerousGhosts();
        assertEquals(1, releasedCount);
        assertEquals(1, hunter.getCapturedGhosts().size());
        assertEquals("Slimer", hunter.getCapturedGhosts().get(0).getName());
    }
}
