package dev.lanny.ghost_busters.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class GhostModel {
    private static int idCounter = 1;

    private final int id;
    private final String name;
    private final GhostClass ghostClass;
    private final ThreatLevel threatLevel;
    private final String specialAbility;
    private final LocalDate captureDate;

    public GhostModel(final String name, final GhostClass ghostClass, final ThreatLevel threatLevel, final String specialAbility, final String captureDate) {
        this.id = idCounter++;
        this.name = validateNotEmpty(name, "El nombre del fantasma no puede estar vacío.");
        this.ghostClass = Objects.requireNonNull(ghostClass, "La clase del fantasma no puede ser nula.");
        this.threatLevel = Objects.requireNonNull(threatLevel, "El nivel de amenaza no puede ser nulo."); // ✅ Validación corregida
        this.specialAbility = validateNotEmpty(specialAbility, "La habilidad especial no puede estar vacía.");
        this.captureDate = parseCaptureDate(captureDate);
    }

    private String validateNotEmpty(String value, String errorMessage) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        return value;
    }

    private LocalDate parseCaptureDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La fecha debe estar en formato YYYY-MM-DD.");
        }
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public GhostClass getGhostClass() { return ghostClass; }
    public ThreatLevel getThreatLevel() { return threatLevel; }
    public String getSpecialAbility() { return specialAbility; }
    public LocalDate getCaptureDate() { return captureDate; }
}
