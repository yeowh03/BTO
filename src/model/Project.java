package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Project {
    private String projectName;
    private String neighborhood;
    private Map<String, UnitType> unitTypes;
    private LocalDate openingDate;
    private LocalDate closingDate;
    private Manager assignedManager;
    private int officerSlots;
    private Map<String, Officer> assignedOfficers;
    private boolean isVisible;  // Project visibility

    public Project(String projectName, String neighborhood, LocalDate openingDate, 
                   LocalDate closingDate, Manager assignedManager, int officerSlots) {
        this.projectName = projectName;
        this.neighborhood = neighborhood;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.assignedManager = assignedManager;
        this.officerSlots = officerSlots;
        this.unitTypes = new HashMap<>();
        this.assignedOfficers = new HashMap<>();
        this.isVisible = true;  // Projects are hidden by default
    }

    public void addUnitType(String type, int totalUnits, double price) {
        unitTypes.put(type, new UnitType(type, totalUnits, price));
    }

    public boolean addOfficer(Officer officer) {
        if (assignedOfficers.size() < officerSlots) {
            assignedOfficers.put(officer.getNric(), officer);
            return true;
        }
        return false;
    }

    public boolean removeOfficer(Officer officer) {
        assignedOfficers.remove(officer.getNric());
        return true;
    }

    public boolean hasAvailableUnits(String type) {
        UnitType unitType = unitTypes.get(type);
        return unitType != null && unitType.getAvailableUnits() > 0;
    }

    public boolean isApplicationOpen() {
        LocalDate now = LocalDate.now();
        return !now.isBefore(openingDate) && !now.isAfter(closingDate);
    }

    // Getters
    public String getProjectName() {
        return projectName;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public Map<String, UnitType> getUnitTypes() {
        return new HashMap<>(unitTypes);
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public Manager getAssignedManager() {
        return assignedManager;
    }

    public int getOfficerSlots() {
        return officerSlots;
    }

    public Map<String, Officer> getAssignedOfficers() {
        return this.assignedOfficers;
    }

    public boolean isVisible() {
        return isVisible;
    }

    // Setters
    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public void setAssignedManager(Manager assignedManager) {
        this.assignedManager = assignedManager;
    }

    public void setOfficerSlots(int officerSlots) {
        this.officerSlots = officerSlots;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }
}
