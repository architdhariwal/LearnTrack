package com.airtribe.learntrack.entity;

/**
 * Represents a Trainer in the LearnTrack system.
 * Extends Person class to demonstrate inheritance hierarchy.
 */
public class Trainer extends Person {
    private String specialization;
    private int yearsOfExperience;

    public Trainer() {
        super();
    }

    public Trainer(int id, String firstName, String lastName, String email, String specialization, int yearsOfExperience) {
        super(id, firstName, lastName, email);
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String getDisplayName() {
        return String.format("Trainer [ID: %d] | %s | Specialization: %s | Experience: %d yrs | Email: %s",
                getId(), getFullName(), specialization, yearsOfExperience, getEmail());
    }
}
