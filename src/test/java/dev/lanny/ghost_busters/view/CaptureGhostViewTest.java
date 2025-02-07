package dev.lanny.ghost_busters.view;

import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.HunterModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class CaptureGhostViewTest {
    private HunterController hunterController;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {     
   
        HunterModel hunterModel = new HunterModel("Egon Spengler", new ArrayList<>());
        hunterController = new HunterController(hunterModel);
        
   
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(System.in); 
        outputStream.reset(); 
    }

    @Test
public void testCaptureGhostSuccess() {

    String simulatedInput = "Phantom A\n1\n2\nHaunting Ability\n2025-03-15\n"; 
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

   
    Scanner scanner = new Scanner(System.in);
    CaptureGhostView.captureGhost(hunterController, scanner);


    String output = outputStream.toString().trim();

    assertThat(output, containsString("✅ Fantasma capturado exitosamente."));
}


    @Test
    public void testInvalidDateInput() {
 
        String userInput = "Phantom A\n2\n2\nHaunting Ability\nINVALID_DATE\n2025-03-15\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        Scanner scanner = new Scanner(System.in);
        CaptureGhostView.captureGhost(hunterController, scanner);

    
        String output = outputStream.toString();
        assertThat(output, containsString("❌ Error: La fecha debe estar en formato YYYY-MM-DD."));
    }

    
}
