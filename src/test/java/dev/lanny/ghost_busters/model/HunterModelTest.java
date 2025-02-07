package dev.lanny.ghost_busters.model;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HunterModelTest {
    private HunterModel hunter;
    private GhostModel ghost1;

    @BeforeEach
    void setUp() {
        hunter = new HunterModel("Peter Venkman", new ArrayList<>());
        ghost1 = new GhostModel("Spirit A", GhostClass.CLASS_I, ThreatLevel.LOW, "Whispering", "2025-02-01");
        
    }

    @Test
    void testCaptureGhost() {
        hunter.captureGhost(ghost1);
        List<GhostModel> capturedGhosts = hunter.getCapturedGhosts();
        assertThat(capturedGhosts, hasSize(1));
       assertThat(capturedGhosts.get(0), is(ghost1));
    }

    

    
}
