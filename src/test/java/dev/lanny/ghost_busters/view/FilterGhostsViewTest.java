package dev.lanny.ghost_busters.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
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
    @DisplayName("Configuración inicial para pruebas en FilterGhostsView")
    public void setUp() {
        List<GhostModel> capturedGhosts = new ArrayList<>();
        hunterController = new HunterController(new HunterModel("Egon Spengler", capturedGhosts));

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Se valida que se muestra el mensaje adecuado cuando no hay fantasmas de la clase seleccionada")
    public void testFilterGhostsByClass_NoGhosts() {
        String simulatedInput = "2\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        scanner = new Scanner(System.in);

        FilterGhostsView.filterGhostsByClass(hunterController, scanner);

        String output = outputStream.toString();
        assertThat(output, containsString("❌ No hay fantasmas de esta clase capturados."));
    }

    @Test
    @DisplayName("Se valida que se muestran los fantasmas capturados de la clase seleccionada")
    public void testFilterGhostsByClass_WithGhosts() {
        GhostModel ghost = new GhostModel("Marinero", GhostClass.CLASS_II, ThreatLevel.MEDIUM,
                "Aparece en tormentas", "2025-01-26");
        hunterController.captureGhost(ghost);

        String simulatedInput = "2\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        scanner = new Scanner(System.in);

        FilterGhostsView.filterGhostsByClass(hunterController, scanner);
        String output = outputStream.toString();

        assertThat(output, containsString("Fantasmas de clase CLASS_II - Aparición móvil capturados:"));
        assertThat(output, containsString("- Marinero (Peligrosidad: MEDIUM - Amenaza de nivel medio)"));
    }

    @Test
    @DisplayName("Se valida que se muestra un mensaje de error cuando la clase ingresada es inválida")
    public void testFilterGhostsByClass_InvalidClass() {
        String simulatedInput = "10\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        scanner = new Scanner(System.in);

        FilterGhostsView.filterGhostsByClass(hunterController, scanner);
        String output = outputStream.toString();

        assertThat(output, containsString("❌ Clase de fantasma no válida. Intente de nuevo."));
    }

    @Test
    @DisplayName("Se valida mensaje cuando no hay fantasmas capturados de la clase seleccionada")
    public void testFilterGhostsByClass_EmptyCapturedList() {
        hunterController = new HunterController(new HunterModel("Egon Spengler", new ArrayList<>()));

        String simulatedInput = "1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        scanner = new Scanner(System.in);

        FilterGhostsView.filterGhostsByClass(hunterController, scanner);

        String output = outputStream.toString();
        assertThat(output, containsString("❌ No hay fantasmas de esta clase capturados."));
    }

    @Test
    @DisplayName("Se valida mensaje cuando la entrada del usuario está vacía")
    public void testFilterGhostsByClass_EmptyInput() {
        String simulatedInput = "\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        scanner = new Scanner(System.in);

        FilterGhostsView.filterGhostsByClass(hunterController, scanner);
        String output = outputStream.toString();

        assertThat(output, containsString(
                "Ingrese el número correspondiente a la clase: ❌ Entrada inválida. Por favor, ingrese un número válido."));
    }

    @Test
    @DisplayName("Se valida que se muestran los fantasmas capturados en un mes específico")
    public void testFilterGhostsByMonth_WithResults() {
        GhostModel ghost1 = new GhostModel("Fantasma 1", GhostClass.CLASS_II, ThreatLevel.MEDIUM, "Habilidad 1", "2025-02-01");
        GhostModel ghost2 = new GhostModel("Fantasma 2", GhostClass.CLASS_III, ThreatLevel.HIGH, "Habilidad 2", "2025-02-15");
        hunterController.captureGhost(ghost1);
        hunterController.captureGhost(ghost2);

        String simulatedInput = "2\n2025\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        scanner = new Scanner(System.in);

        FilterGhostsView.filterGhostsByMonth(hunterController, scanner);
        String output = outputStream.toString();

        assertThat(output, containsString("📆 Fantasmas capturados en febrero de 2025:"));
        assertThat(output, containsString("- Fantasma 1 (Clase: CLASS_II - Aparición móvil, Peligrosidad: MEDIUM - Amenaza de nivel medio"));
        assertThat(output, containsString("- Fantasma 2 (Clase: CLASS_III - Entidad inteligente, Peligrosidad: HIGH - Amenaza de nivel alto"));
    }

    @Test
    @DisplayName("Se valida mensaje cuando no hay fantasmas capturados en el mes especificado")
    public void testFilterGhostsByMonth_NoResults() {
        String simulatedInput = "3\n2025\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        scanner = new Scanner(System.in);

        FilterGhostsView.filterGhostsByMonth(hunterController, scanner);
        String output = outputStream.toString();

        assertThat(output, containsString("❌ No se encontraron fantasmas capturados en Marzo de 2025."));
    }

    @Test
    @DisplayName("Se valida mensaje cuando la entrada del usuario es inválida")
    public void testFilterGhostsByMonth_InvalidInput() {
        String simulatedInput = "abc\n2025\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        scanner = new Scanner(System.in);

        FilterGhostsView.filterGhostsByMonth(hunterController, scanner);
        String output = outputStream.toString();

        assertThat(output, containsString("❌ Entrada inválida. Por favor, ingrese un mes válido (1-12)."));
    }
}
