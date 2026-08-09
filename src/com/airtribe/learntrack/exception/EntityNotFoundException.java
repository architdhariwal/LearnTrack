package com.airtribe.learntrack.exception;

/**
 * Thrown when a requested entity (Student, Course, Enrollment, etc.) cannot be found.
 */
public class EntityNotFoundException extends LearnTrackException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
