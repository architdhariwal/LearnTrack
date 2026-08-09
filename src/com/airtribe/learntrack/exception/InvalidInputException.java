package com.airtribe.learntrack.exception;

/**
 * Thrown when invalid parameters or business rule violations occur.
 */
public class InvalidInputException extends LearnTrackException {
    public InvalidInputException(String message) {
        super(message);
    }
}
