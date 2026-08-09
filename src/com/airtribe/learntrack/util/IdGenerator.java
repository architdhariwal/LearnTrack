package com.airtribe.learntrack.util;

/**
 * Utility class to generate auto-incrementing unique IDs for entities.
 * Demonstrates static fields and static methods.
 */
public class IdGenerator {
    // Static counters
    private static int studentIdCounter = 101;
    private static int courseIdCounter = 501;
    private static int enrollmentIdCounter = 1001;
    private static int trainerIdCounter = 201;

    // Private constructor to prevent instantiation of utility class
    private IdGenerator() {
    }

    /**
     * Generates next unique Student ID.
     */
    public static synchronized int getNextStudentId() {
        return studentIdCounter++;
    }

    /**
     * Generates next unique Course ID.
     */
    public static synchronized int getNextCourseId() {
        return courseIdCounter++;
    }

    /**
     * Generates next unique Enrollment ID.
     */
    public static synchronized int getNextEnrollmentId() {
        return enrollmentIdCounter++;
    }

    /**
     * Generates next unique Trainer ID.
     */
    public static synchronized int getNextTrainerId() {
        return trainerIdCounter++;
    }

    /**
     * Resets counters (useful for testing if needed).
     */
    public static synchronized void resetCounters() {
        studentIdCounter = 101;
        courseIdCounter = 501;
        enrollmentIdCounter = 1001;
        trainerIdCounter = 201;
    }
}
