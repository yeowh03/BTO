package model;

public enum ApplicationStatus {
    PENDING("Pending"),
    SUCCESSFUL("Successful"),
    UNSUCCESSFUL("Unsuccessful"),
    BOOKED("Booked"),
	PENDING_CANCEL_BOOKING("Cancelling"),
	PENDING_CANCEL("Cancelling");

    private final String displayName;

    ApplicationStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
