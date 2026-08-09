package com.airtribe.learntrack;

import com.airtribe.learntrack.ui.MenuUI;

/**
 * Entry point for LearnTrack Console Application.
 * Standard Java main method initializing the UI system.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting LearnTrack Console Application...");
        MenuUI ui = new MenuUI();
        ui.start();
    }
}
