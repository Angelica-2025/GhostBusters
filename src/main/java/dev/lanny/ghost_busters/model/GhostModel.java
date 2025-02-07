package dev.lanny.ghost_busters.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class GhostModel {
    private static int idCounter = 1;

    private final int id;
    private final String name;
    private final GhostClass ghostClass;
    private final ThreatLevel threatLevel;
    private final String specialAbility;
    private final LocalDate captureDate;

    public GhostModel(String name, GhostClass ghostClass, ThreatLevel threatLevel, String specialAbility, String captureDate) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del fantasma no puede estar vacío.");
        }
        if (specialAbility == null || specialAbility.trim().isEmpty()) {
            throw new IllegalArgumentException("La habilidad especial no puede estar vacía.");
        }
        try {
            this.captureDate = LocalDate.parse(captureDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La fecha debe estar en formato YYYY-MM-DD.");
        }

        this.id = idCounter++;
        this.name = name;
        this.ghostClass = ghostClass;
        this.threatLevel = threatLevel;
        this.specialAbility = specialAbility;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GhostClass getGhostClass() {
        return ghostClass;
    }

    public ThreatLevel getThreatLevel() {
        return threatLevel;
    }

    public String getSpecialAbility() {
        return specialAbility;
    }

    public LocalDate getCaptureDate() {
        return captureDate;
    }
}
