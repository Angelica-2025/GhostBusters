package dev.lanny.ghost_busters.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;


public class HunterModelTest {

    private HunterModel hunter;
    private List<GhostModel> ghostList;

    @BeforeEach
    public void setUp() {
        ghostList = new ArrayList<>();
        hunter = new HunterModel("Hunter A", ghostList);
    }

    @Test
    public void testCapturedGhosts_Success() {   
        GhostModel ghost = new GhostModel("Ghost A", GhostClass.CLASS_I, ThreatLevel.LOW, "Ability", "2025-03-15");    
        hunter.capturedGhosts(ghost);
        assertThat(hunter.getGhostList(), hasItem(ghost));
        assertThat(hunter.getGhostList().size(), is(1));
    }

    @Test
    public void testFreeGhost_Success() {        
        GhostModel ghost1 = new GhostModel("Ghost A", GhostClass.CLASS_I, ThreatLevel.LOW, "Ability", "2025-03-15");
        GhostModel ghost2 = new GhostModel("Ghost B", GhostClass.CLASS_II, ThreatLevel.HIGH, "Ability", "2025-03-16");
        hunter.capturedGhosts(ghost1);
        hunter.capturedGhosts(ghost2);
        hunter.freeGhost(ghost1.getId());
        assertThat(hunter.getGhostList(), not(hasItem(ghost1)));
        assertThat(hunter.getGhostList(), hasItem(ghost2));
        assertThat(hunter.getGhostList().size(), is(1));
    }

    @Test
    public void testFreeGhost_InvalidId() {       
        GhostModel ghost = new GhostModel("Ghost A", GhostClass.CLASS_I, ThreatLevel.LOW, "Ability", "2025-03-15");
        hunter.capturedGhosts(ghost);       
        hunter.freeGhost(999);
        assertThat(hunter.getGhostList().size(), is(1));
        assertThat(hunter.getGhostList(), hasItem(ghost));
    }
}


