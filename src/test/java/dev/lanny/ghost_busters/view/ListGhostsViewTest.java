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
import org.junit.jupiter.api.Test;


public class ListGhostsViewTest {

    private HunterController hunterController;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
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
public void testDisplayCapturedGhosts_WithGhosts() {
    
    ListGhostsView.displayCapturedGhosts(hunterController);

    String output = outputStream.toString();

    assertThat(output, containsString("ID Nombre               Clase           Peligro     Fecha"));
    assertThat(output, containsString("Espíritu del Pescador de Lastres"));
    assertThat(output, containsString("Total de fantasmas capturados: 3"));
}

}

