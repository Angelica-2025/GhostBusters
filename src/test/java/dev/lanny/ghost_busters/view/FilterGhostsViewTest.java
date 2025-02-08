package dev.lanny.ghost_busters.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

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
    @DisplayName("Configuración inicial para pruebas en FilterGhostsView")
    public void setUp() {
        HunterModel hunterModel = new HunterModel("Egon Spengler", new ArrayList<>());
        hunterController = new HunterController(hunterModel);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("Se valida que se muestra el mensaje adecuado cuando no hay fantasmas de la clase seleccionada")
    public void testFilterGhostsByClass_NoGhosts() {
        String simulatedInput = "2\n"; // Número de la clase en lugar del nombre
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

        String simulatedInput = "2\n"; // Número de la clase
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
        String simulatedInput = "10\n"; // Número fuera de rango
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        scanner = new Scanner(System.in);

        FilterGhostsView.filterGhostsByClass(hunterController, scanner);
        String output = outputStream.toString();

        assertThat(output, containsString("❌ Clase de fantasma no válida. Intente de nuevo."));
    }

    @Test
    @DisplayName("Se valida mensaje cuando no hay fantasmas capturados de la clase seleccionada")
    public void testFilterGhostsByClass_EmptyCapturedList() {
        // Asegurarse de que no haya fantasmas capturados en el controlador
        hunterController = new HunterController(new HunterModel("Egon Spengler", new ArrayList<>()));

        // Entrada simulada para la clase CLASS_I (número 1)
        String simulatedInput = "1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        scanner = new Scanner(System.in);

        // Llamar a la vista para filtrar fantasmas
        FilterGhostsView.filterGhostsByClass(hunterController, scanner);

        // Capturar la salida
        String output = outputStream.toString();

        // Aserciones
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

    assertThat(output, containsString("Ingrese el número correspondiente a la clase: ❌ Entrada inválida. Por favor, ingrese un número válido."));
}


    @Test
    @DisplayName("Se valida mensaje cuando la entrada del usuario contiene solo espacios")
    public void testFilterGhostsByClass_WhitespaceInput() {
        String simulatedInput = "   \n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        scanner = new Scanner(System.in);

        FilterGhostsView.filterGhostsByClass(hunterController, scanner);
        String output = outputStream.toString();

        assertThat(output, containsString("Ingrese el número correspondiente a la clase: ❌ Entrada inválida. Por favor, ingrese un número válido."));
    }

    @Test
    @DisplayName("Se valida que se muestran múltiples fantasmas capturados de la misma clase")
    public void testFilterGhostsByClass_MultipleGhosts() {
        GhostModel ghost1 = new GhostModel("Fantasma 1", GhostClass.CLASS_II, ThreatLevel.MEDIUM, "Habilidad 1",
                "2025-01-01");
        GhostModel ghost2 = new GhostModel("Fantasma 2", GhostClass.CLASS_II, ThreatLevel.HIGH, "Habilidad 2",
                "2025-01-02");
        hunterController.captureGhost(ghost1);
        hunterController.captureGhost(ghost2);

        String simulatedInput = "2\n"; // Número de la clase
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        scanner = new Scanner(System.in);

        FilterGhostsView.filterGhostsByClass(hunterController, scanner);
        String output = outputStream.toString();

        assertThat(output, containsString("Fantasmas de clase CLASS_II - Aparición móvil capturados:"));
        assertThat(output, containsString("- Fantasma 1 (Peligrosidad: MEDIUM - Amenaza de nivel medio)"));
        assertThat(output, containsString("- Fantasma 2 (Peligrosidad: HIGH - Amenaza de nivel alto)"));
    }

    
}
