package dev.lanny.ghost_busters.view;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.List;
import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.GhostModel;
import dev.lanny.ghost_busters.model.GhostClass;
import dev.lanny.ghost_busters.model.HunterModel;
import dev.lanny.ghost_busters.model.ThreatLevel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ListGhostsViewTest {

    private HunterController hunterController;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    @DisplayName("Configuración inicial: Crear HunterController con una lista predefinida de fantasmas capturados")
    public void setUp() {

        List<GhostModel> capturedGhosts = new ArrayList<>();
        capturedGhosts.add(new GhostModel("Espíritu del Pescador de Lastres", GhostClass.CLASS_IV, ThreatLevel.LOW,
                "Pesca Fantasmal", "2025-01-26"));
        capturedGhosts.add(new GhostModel("Sombras del Monte Naranco", GhostClass.CLASS_II, ThreatLevel.MEDIUM,
                "Oscuridad", "2025-01-24"));
        capturedGhosts.add(new GhostModel("Entidad de las Minas Abandonadas", GhostClass.CLASS_VI, ThreatLevel.HIGH,
                "Eco", "2025-01-20"));

        HunterModel hunterModel = new HunterModel("Egon Spengler", new ArrayList<>(capturedGhosts));
        hunterController = new HunterController(hunterModel);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Verificar que los fantasmas capturados se muestran correctamente en la lista")
    public void testDisplayCapturedGhosts_WithGhosts() {

        ListGhostsView.displayCapturedGhosts(hunterController);

        String output = outputStream.toString();

        assertThat(output, containsString("Ver Lista de Fantasmas Capturados"));

        assertThat(output, containsString("ID"));
        assertThat(output, containsString("Nombre"));
        assertThat(output, containsString("Clase"));
        assertThat(output, containsString("Peligro"));
        assertThat(output, containsString("Fecha"));

        assertThat(output, containsString("Espíritu del Pescador de Lastres"));
        assertThat(output, containsString("Sombras del Monte Naranco"));
        assertThat(output, containsString("Entidad de las Minas Abandonadas"));

        assertThat(output, containsString("Total de fantasmas capturados: 3"));
    }

    @Test
    @DisplayName("Verificar encabezados de columna en la lista de fantasmas capturados")
    public void testDisplayCapturedGhosts_ColumnHeaders() {
        ListGhostsView.displayCapturedGhosts(hunterController);

        String output = outputStream.toString();

        assertThat(output, containsString("ID    Nombre               Clase           Peligro      Fecha"));
    }

    @Test
    @DisplayName("Validar formato de los datos de fantasmas capturados")
    public void testDisplayCapturedGhosts_Format() {
        ListGhostsView.displayCapturedGhosts(hunterController);

        String output = outputStream.toString();

        assertThat(output, containsString("2     Sombras del Monte Naranco"));
        assertThat(output, containsString("CLASS_II"));
        assertThat(output, containsString("MEDIUM"));
        assertThat(output, containsString("2025-01-24"));
    }

}
