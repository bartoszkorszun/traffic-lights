package com.example;

import java.io.IOException;

public class ClearConsole {
    public static void clearConsole() {
        try {
            String operatingSystem = System.getProperty("os.name"); 
            
            if (operatingSystem.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error clearing console: " + e.getMessage());
        }
    }
}
