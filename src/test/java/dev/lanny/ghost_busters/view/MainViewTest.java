package dev.lanny.ghost_busters.view;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dev.lanny.ghost_busters.controller.HunterController;
import dev.lanny.ghost_busters.model.GhostClass;
import dev.lanny.ghost_busters.model.GhostModel;
import dev.lanny.ghost_busters.model.HunterModel;
import dev.lanny.ghost_busters.model.ThreatLevel;


public class MainViewTest {
    private HunterController hunterController;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
@DisplayName("Configuración inicial de MainView para pruebas")
public void setUp() {
    List<GhostModel> capturedGhosts = new ArrayList<>();
    capturedGhosts.add(new GhostModel("Fantasma 1", GhostClass.CLASS_II, ThreatLevel.MEDIUM, "Aparece en tormentas", "2025-02-10"));
    capturedGhosts.add(new GhostModel("Fantasma 2", GhostClass.CLASS_III, ThreatLevel.HIGH, "Ecos fantasmales", "2025-02-20"));

    HunterModel hunterModel = new HunterModel("Egon Spengler", capturedGhosts);
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
        String simulatedInput = "4\n1\n6\n"; 
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Scanner scanner = new Scanner(System.in);
        MainView mainView = new MainView(hunterController, scanner);
        mainView.showMainMenu();

        String output = outputStream.toString();
        assertThat(output, containsString("Seleccione la clase de fantasma para filtrar:"));
        assertThat(output, containsString("Gracias por proteger Asturias. ¡Hasta la próxima!"));
    }

    @Test
    @DisplayName("Validar que se puede filtrar fantasmas por mes desde el menú principal")
    public void testMainMenuOption_FilterGhostsByMonth() {
        String simulatedInput = "5\n2\n2025\n6\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Scanner scanner = new Scanner(System.in);
        MainView mainView = new MainView(hunterController, scanner);
        mainView.showMainMenu();

        String output = outputStream.toString();
        assertThat(output, containsString("Filtrar Fantasmas por Mes y Año"));
        assertThat(output, containsString("Ingrese el mes (1-12):"));
        assertThat(output, containsString("Ingrese el año (YYYY):"));
        assertThat(output, containsString("📆 Fantasmas capturados en febrero de 2025:"));
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
    @DisplayName("Validar opción 5 para ver fantasmas capturados en un mes específico y luego salir")
    public void testMainMenuOption_FilterByMonth_Exit() {
        String simulatedInput = "5\n3\n2025\n6\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Scanner scanner = new Scanner(System.in);
        MainView mainView = new MainView(hunterController, scanner);
        mainView.showMainMenu();

        String output = outputStream.toString();
        assertThat(output, containsString("Ingrese el mes (1-12):"));
        assertThat(output, containsString("Ingrese el año (YYYY):"));
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
