package model;

public enum RegistrationStatus {
    PENDING("Pending"),
    SUCCESSFUL("Approved"),
    UNSUCCESSFUL("Rejected");

    private final String displayName;

	RegistrationStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}



