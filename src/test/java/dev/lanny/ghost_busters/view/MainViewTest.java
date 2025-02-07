package dev.lanny.ghost_busters.view;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class MainViewTest {
    
    @Test
    public void testMainMenuOption_CaptureGhostAndExit() {
        // ✅ Simular entrada del usuario para capturar un fantasma y salir
        String simulatedInput = "1\nPhantom A\n1\n2\nHaunting Ability\n2025-03-15\n2\n"; 
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        
        // ✅ Capturar la salida de la consola
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // ✅ Ejecutar `MainView`
        MainView.main(new String[] {});

        // ✅ Capturar la salida generada
        String output = outputStream.toString();

        // ✅ Imprimir la salida capturada para depuración
        System.out.println("🔍 SALIDA CAPTURADA:\n" + output);

        // ✅ Verificar que la salida contiene el mensaje esperado
        assertThat(output, containsString("Bienvenido a la Base Ghostbusters Asturias!"));
        assertThat(output, containsString("Capturar un nuevo fantasma"));
        assertThat(output, containsString("✅ Fantasma capturado exitosamente."));
        assertThat(output, containsString("Gracias por proteger Asturias. ¡Hasta la próxima!"));
    }
}

    


