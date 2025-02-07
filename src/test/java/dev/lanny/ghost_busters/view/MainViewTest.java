package dev.lanny.ghost_busters.view;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.HunterModel;



import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;


public class MainViewTest {
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
    public void testMainMenuOption_DisplayCapturedGhosts() {
      
        String simulatedInput = "2\n4\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Scanner scanner = new Scanner(System.in);
        MainView mainView = new MainView(hunterController, scanner);
        mainView.showMainMenu();

        String output = outputStream.toString();

        assertThat(output, containsString("📜 Lista de Fantasmas Capturados"));
        assertThat(output, containsString("❌ No hay fantasmas capturados."));
    }

   
}




    


