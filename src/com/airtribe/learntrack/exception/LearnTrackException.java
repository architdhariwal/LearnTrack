package com.airtribe.learntrack.exception;

/**
 * Base custom exception for LearnTrack application.
 */
public class LearnTrackException extends Exception {
    public LearnTrackException(String message) {
        super(message);
    }

    public LearnTrackException(String message, Throwable cause) {
        super(message, cause);
    }
}
