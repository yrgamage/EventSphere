package org.example;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerClass {

    // ANSI escape codes for colors
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // Create a logger
    private static final Logger logger = Logger.getLogger(LoggerClass.class.getName());

    static {
        // Add a ConsoleHandler to display logs in the console
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
    }

    // Custom log method to add color based on the input string
    public static void log(Level level, String message, String color) {
        String coloredMessage = message;

        // Apply color based on the input string
        switch (color) {
            case "RESET":
                coloredMessage = RESET + message + RESET;
                break;
            case "RED":
                coloredMessage = RED + message + RESET;
                break;
            case "GREEN":
                coloredMessage = GREEN + message + RESET;
                break;
            case "YELLOW":
                coloredMessage = YELLOW + message + RESET;
                break;
            case "BLUE":
                coloredMessage = BLUE + message + RESET;
                break;
            case "MAGENTA":
                coloredMessage = MAGENTA + message + RESET;
                break;
            case "CYAN":
                coloredMessage = CYAN + message + RESET;
                break;
            case "WHITE":
                coloredMessage = WHITE + message + RESET;
                break;
            default:
                coloredMessage = RESET + message + RESET;
                break;
        }

        // Log the colorized message with the appropriate log level
        logger.log(level, coloredMessage);
    }
}
