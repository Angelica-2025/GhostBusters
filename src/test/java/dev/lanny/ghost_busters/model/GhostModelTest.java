package dev.lanny.ghost_busters.model;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class GhostModelTest {
    @Test
    public void testGhostCreationSuccess() {
        GhostModel ghost = new GhostModel("Phantom", GhostClass.CLASS_I, ThreatLevel.LOW, "Whispering", "2025-02-10");

        assertThat(ghost.getName(), is("Phantom"));
        assertThat(ghost.getGhostClass(), is(GhostClass.CLASS_I));
        assertThat(ghost.getThreatLevel(), is(ThreatLevel.LOW));
        assertThat(ghost.getSpecialAbility(), is("Whispering"));
        assertThat(ghost.getCaptureDate(), is(LocalDate.parse("2025-02-10")));
    }

    @Test
    public void testInvalidGhostCreation_EmptyName() {
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () ->
                new GhostModel("", GhostClass.CLASS_II, ThreatLevel.MEDIUM, "Haunting", "2025-02-10"));

        assertThat(exception.getMessage(), containsString("El nombre del fantasma no puede estar vacío."));
    }

    @Test
    public void testInvalidGhostCreation_InvalidDate() {
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () ->
                new GhostModel("Phantom", GhostClass.CLASS_III, ThreatLevel.HIGH, "Screaming", "INVALID_DATE"));

        assertThat(exception.getMessage(), containsString("La fecha debe estar en formato YYYY-MM-DD."));
    }
}


