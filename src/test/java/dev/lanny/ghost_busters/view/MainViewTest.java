package dev.lanny.ghost_busters.view;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.HunterModel;

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
    @DisplayName("Validar que se muestra la lista de fantasmas capturados desde el menú principal")
    public void testMainMenuOption_DisplayCapturedGhosts() {
               String simulatedInput = "2\n6\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Scanner scanner = new Scanner(System.in);
        MainView mainView = new MainView(hunterController, scanner);
        mainView.showMainMenu();

        String output = outputStream.toString();

        assertThat(output, containsString("Ver Lista de Fantasmas Capturados"));
        assertThat(output, containsString("Gracias por proteger Asturias. ¡Hasta la próxima!"));
    }

    @Test
    @DisplayName("Validar que se puede filtrar fantasmas por clase desde el menú principal")
    public void testMainMenuOption_FilterGhostsByClass() {
  
        String simulatedInput = "4\nCLASS_I\n6\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Scanner scanner = new Scanner(System.in);
        MainView mainView = new MainView(hunterController, scanner);
        mainView.showMainMenu();

        String output = outputStream.toString();

        assertThat(output, containsString("Seleccione la clase de fantasma para filtrar:"));
        assertThat(output, containsString("Gracias por proteger Asturias. ¡Hasta la próxima!"));
    }

    @Test
    @DisplayName("Validar que el programa se cierra correctamente cuando no hay más entradas")
    public void testMainMenu_NoMoreInput() {
 
        String simulatedInput = "";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Scanner scanner = new Scanner(System.in);
        MainView mainView = new MainView(hunterController, scanner);
        mainView.showMainMenu();

        String output = outputStream.toString();

        assertThat(output, containsString("❌ No hay más entradas disponibles. Saliendo del programa."));
    }

    @Test
    @DisplayName("Validar manejo de opción inválida en el menú")
    public void testMainMenuOption_Invalid() {
        String simulatedInput = "7\n6\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Scanner scanner = new Scanner(System.in);
        MainView mainView = new MainView(hunterController, scanner);
        mainView.showMainMenu();

        String output = outputStream.toString();
        assertThat(output, containsString("❌ Opción inválida. Por favor, seleccione un número del 1 al 6."));
    }

    @Test
    @DisplayName("Validar opción 5 para salir del programa")
    public void testMainMenuOption_ExitCase5() {
        String simulatedInput = "5\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Scanner scanner = new Scanner(System.in);
        MainView mainView = new MainView(hunterController, scanner);
        mainView.showMainMenu();

        String output = outputStream.toString();
        assertThat(output, containsString("Gracias por proteger Asturias. ¡Hasta la próxima!"));
    }

    @Test
    @DisplayName("Validar opción 6 para salir del programa")
    public void testMainMenuOption_ExitCase6() {
        String simulatedInput = "6\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Scanner scanner = new Scanner(System.in);
        MainView mainView = new MainView(hunterController, scanner);
        mainView.showMainMenu();

        String output = outputStream.toString();
        assertThat(output, containsString("Gracias por proteger Asturias. ¡Hasta la próxima!"));
    }

}
