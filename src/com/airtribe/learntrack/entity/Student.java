package com.airtribe.learntrack.entity;

/**
 * Represents a Student in the LearnTrack system.
 * Extends Person class to demonstrate inheritance, constructor overloading, and super keyword.
 */
public class Student extends Person {
    private String batch;
    private boolean active;

    // Default constructor
    public Student() {
        super();
        this.active = true;
    }

    // Full parameterized constructor
    public Student(int id, String firstName, String lastName, String email, String batch, boolean active) {
        super(id, firstName, lastName, email); // Calling superclass constructor
        this.batch = batch;
        this.active = active;
    }

    // Constructor overloading: defaults active to true
    public Student(int id, String firstName, String lastName, String email, String batch) {
        this(id, firstName, lastName, email, batch, true);
    }

    // Constructor overloading: defaults email to "N/A" and active to true
    public Student(int id, String firstName, String lastName, String batch) {
        super(id, firstName, lastName); // Calling superclass overloaded constructor
        this.batch = batch;
        this.active = true;
    }

    // Getters and Setters
    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Method overriding demonstrating polymorphism.
     */
    @Override
    public String getDisplayName() {
        String statusStr = active ? "ACTIVE" : "INACTIVE";
        return String.format("Student [ID: %d] | %s | Email: %s | Batch: %s | Status: %s",
                getId(), getFullName(), getEmail(), batch, statusStr);
    }
}
