package dev.lanny.ghost_busters.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import static org.hamcrest.MatcherAssert.assertThat;
import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.HunterModel;
import dev.lanny.ghost_busters.model.GhostModel;
import dev.lanny.ghost_busters.model.GhostClass;
import dev.lanny.ghost_busters.model.ThreatLevel;
import static org.hamcrest.Matchers.containsString;

public class FilterGhostsViewTest {
    private HunterController hunterController;
    private ByteArrayOutputStream outputStream;
    private Scanner scanner;

    @BeforeEach
    public void setUp() {
        HunterModel hunterModel = new HunterModel("Egon Spengler", new ArrayList<>());
        hunterController = new HunterController(hunterModel);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testFilterGhostsByClass_NoGhosts() {
        String simulatedInput = "CLASS_II\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        scanner = new Scanner(System.in);

        FilterGhostsView.filterGhostsByClass(hunterController, scanner);

        String output = outputStream.toString();

        assertThat(output, containsString("❌ No hay fantasmas de esta clase capturados."));
    }

    @Test
    public void testFilterGhostsByClass_WithGhosts() {
        GhostModel ghost = new GhostModel("Marinero", GhostClass.CLASS_II, ThreatLevel.MEDIUM,
                "Aparece en tormentas", "2025-01-26");
        hunterController.captureGhost(ghost);

        String simulatedInput = "CLASS_II\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        scanner = new Scanner(System.in);

        FilterGhostsView.filterGhostsByClass(hunterController, scanner);

        String output = outputStream.toString();

        assertThat(output, containsString("Fantasmas de clase CLASS_II - Aparición móvil capturados:"));
        assertThat(output, containsString("- Marinero (Peligrosidad: MEDIUM - Amenaza de nivel medio)"));
    }

    @Test
    public void testFilterGhostsByClass_InvalidClass() {
        String simulatedInput = "INVALID_CLASS\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        scanner = new Scanner(System.in);

        FilterGhostsView.filterGhostsByClass(hunterController, scanner);
        String output = outputStream.toString();

        assertThat(output, containsString("❌ Clase de fantasma no válida. Intente de nuevo."));
    }
}
