package dev.lanny.ghost_busters.model;

import java.time.LocalDate;

public class GhostModel {

    private String name;
    private GhostClass ghostClass;
    private ThreatLevel threatLevel;
    private String specialAbility;
    private LocalDate captureDate;

    public GhostModel(String name, GhostClass ghostClass, ThreatLevel threatLevel, String specialAbility, String captureDate) {
        this.name = name;
        this.ghostClass = ghostClass;
        this.threatLevel = threatLevel;
        this.specialAbility = specialAbility;
        this.captureDate = LocalDate.parse(captureDate);
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
