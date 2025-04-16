package model;

import java.util.ArrayList;
import java.util.List;

public class UnitType {
    private String type;
    private int totalUnits;
    private double price;
    private int availableUnits;
    private int assignedUnits;

    public UnitType(String type, int totalUnits, double price) {
        this.type = type;
        this.totalUnits = totalUnits;
        this.price = price;
        this.availableUnits = totalUnits;
        this.assignedUnits = 0;
    }

    public boolean assignUnit() {
        if (availableUnits > 0) {
        	availableUnits -= 1;
        	assignedUnits += 1;
        	return true;
        }return false;
    }

    public boolean unassignUnit() {
        if (assignedUnits > 0) {
        	assignedUnits -= 1;
        	availableUnits += 1;
            return true;
        }
        return false;
    }

    public int getAvailableUnits() {
        return availableUnits;
    }

    public int getTotalUnits() {
        return totalUnits;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}
