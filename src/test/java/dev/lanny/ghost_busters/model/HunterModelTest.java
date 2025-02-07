package dev.lanny.ghost_busters.model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


class HunterModelTest {
    private HunterModel hunter;
    private GhostModel ghost1;
    private GhostModel ghost2;

    @BeforeEach
    void setUp() {
        hunter = new HunterModel("Peter Venkman");
        ghost1 = new GhostModel("Spirit A", GhostClass.CLASS_I, ThreatLevel.LOW, "Whispering", "2025-02-01");
        ghost2 = new GhostModel("Spirit B", GhostClass.CLASS_I, ThreatLevel.LOW, "Whispering", "2025-02-01");
    }

    @Test
    void testCaptureGhost() {
        hunter.captureGhost(ghost1);
        assertEquals(1, hunter.getAllGhosts().size());
        assertTrue(hunter.getAllGhosts().contains(ghost1));
    }

    @Test
    void testGetAllGhosts() {
        hunter.captureGhost(ghost1);
        hunter.captureGhost(ghost2);
        List<GhostModel> ghosts = hunter.getAllGhosts();
        assertEquals(2, ghosts.size());
    }

    
}
