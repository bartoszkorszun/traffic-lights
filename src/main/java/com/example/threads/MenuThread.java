package com.example.threads;

import com.example.ClearConsole;
import com.example.SharedScanner;
import com.example.SystemState;
import com.example.enums.MenuOptionsEnum;
import com.example.enums.SystemStateEnum;

public class MenuThread extends Thread {
    private static boolean isOnDisplay = false;
    private static MenuThread menuThreadInstance = null;

    private MenuThread() {}

    public static MenuThread getInstance() {
        if (menuThreadInstance == null) {
            menuThreadInstance = new MenuThread();
        }
        return menuThreadInstance;
    }

    @Override
    public void run() {
        int selectedOption;

        while (true) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("MenuThread interrupted: " + ex.getMessage());
            }

            ClearConsole.clearConsole();
            if (isOnDisplay) {
                if (SystemState.getState() == SystemStateEnum.MENU) {
                    printMenu();
    
                    try {
                        selectedOption = Integer.parseInt(SharedScanner.getScannerInput());
                        switch (selectedOption) {
                            case 1 -> System.out.println("Road added");
                            case 2 -> System.out.println("Road deleted");
                            case 3 -> System.out.println("System opened");
                            case 0 -> {
                                System.out.println("Bye!");
                                return;
                            }
                            default -> System.out.println("Invalid option. Please try again.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please try again.");
                    }
                }
            }
        }
    }

    public void printMenu() {
        for (MenuOptionsEnum option : MenuOptionsEnum.values()) {
            option.printOption();
        }
    }

    public static void setOnDisplay(boolean onDisplay) {
        isOnDisplay = onDisplay;
    }
}
