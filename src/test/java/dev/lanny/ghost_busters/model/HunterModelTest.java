package dev.lanny.ghost_busters.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HunterModelTest {
    private HunterModel hunterModel;
    private GhostModel ghost1;
    private GhostModel ghost2;
    private GhostModel ghost3;

    @BeforeEach
    @DisplayName("Configuración inicial de HunterModel con fantasmas capturados")
    void setUp() {
        ghost1 = new GhostModel("Phantom", GhostClass.CLASS_I, ThreatLevel.LOW, "Whispering", "2025-02-10");
        ghost2 = new GhostModel("Specter", GhostClass.CLASS_II, ThreatLevel.MEDIUM, "Invisibility", "2025-02-12");
        ghost3 = new GhostModel("Wraith", GhostClass.CLASS_I, ThreatLevel.HIGH, "Ethereal", "2025-02-14");
        List<GhostModel> capturedGhosts = new ArrayList<>();
        hunterModel = new HunterModel("Carlos", capturedGhosts);
        capturedGhosts.add(ghost1);
        capturedGhosts.add(ghost2);
        capturedGhosts.add(ghost3);
    }

    @Test
    @DisplayName("Validar que se puede capturar un fantasma")
    void testCaptureGhost() {
        hunterModel.captureGhost(ghost1);
        List<GhostModel> capturedGhosts = hunterModel.getCapturedGhosts();
        assertThat(capturedGhosts, hasSize(4));
        assertThat(capturedGhosts.get(0), is(ghost1));
    }

    @Test
    @DisplayName("Validar que no se puede capturar un fantasma nulo")
    public void testFilterGhostsByClass() {

        List<GhostModel> filteredGhosts = hunterModel.filterGhostsByClass(GhostClass.CLASS_I);
        assertThat(filteredGhosts.size(), is(2));
        assertThat(filteredGhosts.get(0).getName(), is("Phantom"));
        assertThat(filteredGhosts.get(1).getName(), is("Wraith"));
    }

    @Test
    @DisplayName("Validar que se filtran fantasmas por clase sin coincidencias")
    void testFilterGhostsByClass_NoMatches() {
        List<GhostModel> filteredGhosts = hunterModel.filterGhostsByClass(GhostClass.CLASS_III);

        assertThat(filteredGhosts.isEmpty(), is(true));
    }

    @Test
    @DisplayName("Validar que getCapturedGhosts devuelve una copia de la lista")
    void testGetCapturedGhosts_Immutable() {
        List<GhostModel> capturedGhosts = hunterModel.getCapturedGhosts();
        capturedGhosts.clear(); 
        assertThat(hunterModel.getCapturedGhosts(), hasSize(3)); 
    }

    @Test
    @DisplayName("Validar que se inicializa la lista de fantasmas si se pasa null al constructor")
    void testConstructor_NullCapturedGhosts() {
        HunterModel newHunterModel = new HunterModel("New Hunter", null);
        List<GhostModel> capturedGhosts = newHunterModel.getCapturedGhosts();

        assertThat(capturedGhosts.isEmpty(), is(true));
    }
}
