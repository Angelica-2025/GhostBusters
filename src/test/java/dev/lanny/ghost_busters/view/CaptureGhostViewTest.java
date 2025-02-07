package dev.lanny.ghost_busters.view;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.HunterModel;


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

    @Test

    public void testCaptureGhostSuccess() {

        String simulatedInput = "asdf\n2\n3\nsfsdfv\n2025-02-03\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Scanner scanner = new Scanner(System.in);
        CaptureGhostView.captureGhost(hunterController, scanner);

        System.out.flush();

        String output = outputStream.toString();
        assertThat(output, containsString("✅ ¡Fantasma capturado satisfactoriamente!"));
        assertThat(output, containsString("Nombre: asdf"));
        assertThat(output, containsString("Clase: CLASS_II"));
        assertThat(output, containsString("Nivel de Peligro: HIGH"));
        assertThat(output, containsString("Habilidad: sfsdfv"));
        assertThat(output, containsString("Fecha de Captura: 2025-02-03"));
    }

    @Test

    public void testInvalidDateInput() {
        String userInput = "Phantom A\n2\n2\nHaunting Ability\nINVALID_DATE\n2025-03-15\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        Scanner scanner = new Scanner(System.in);
        CaptureGhostView.captureGhost(hunterController, scanner);
        System.out.flush();
        String output = outputStream.toString();
        assertThat(output, containsString("❌ Formato inválido. Por favor, use el formato YYYY-MM-DD."));
    }

}
