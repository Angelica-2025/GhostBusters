package dev.lanny.ghost_busters.view;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import dev.lanny.ghost_busters.controller.GhostController;
import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.view.CaptureGhostView;



public class CaptureGhostViewTest {
    private GhostController ghostController;
    private HunterController hunterController;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        ghostController = new GhostController();
        hunterController = new HunterController(hunterController);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
public void testCaptureGhostSuccess() {
    // Simular entrada del usuario para capturar un fantasma
    String simulatedInput = "Phantom A\n1\n2\nHaunting Ability\n2025-03-15\n"; 
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

    // Capturar la salida de la consola
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // Ejecutar la vista de captura
    Scanner scanner = new Scanner(System.in);
    CaptureGhostView.captureGhost(ghostController, hunterController, scanner);

    // Capturar la salida final
    String output = outputStream.toString();

    // Verificar que el fantasma fue capturado y que incluye afinidad ectoplásmica
    assertThat(output, containsString("✅ ¡Fantasma capturado satisfactoriamente!"));
    assertThat(output, containsString("Nombre:          Phantom A"));
    assertThat(output, containsString("Clase:           CLASS_I"));
    assertThat(output, containsString("Nivel de Peligro: MEDIUM"));
    assertThat(output, containsString("Habilidad:       Haunting Ability"));
    assertThat(output, containsString("Fecha de Captura: 2025-03-15"));
    assertThat(output, containsString("Nivel de Afinidad Ectoplásmica:")); // Asegurar que se imprime
}



    @Test
    public void testInvalidDateInput() {
        String userInput = "Phantom A\n2\n2\nHaunting Ability\nINVALID_DATE\n2025-03-15\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        CaptureGhostView.captureGhost(ghostController, hunterController, new Scanner(System.in));

        String output = outputStream.toString();
        assertThat(output, containsString("❌ Formato inválido. Por favor, use el formato YYYY-MM-DD."));
    }

    @Test
    public void testEmptyNameInput() {
        String userInput = "\nPhantom A\n2\n2\nHaunting Ability\n2025-03-15\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        CaptureGhostView.captureGhost(ghostController, hunterController, new Scanner(System.in));

        String output = outputStream.toString();
        assertThat(output, containsString("❌ El nombre no puede estar vacío."));
    }
    
}
