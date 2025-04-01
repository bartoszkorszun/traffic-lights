package com.example.threads;

import com.example.enums.MenuOptionsEnum;
import com.example.enums.SystemStateEnum;
import com.example.utils.ClearConsole;
import com.example.utils.SharedScanner;
import com.example.utils.SystemState;

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
    @SuppressWarnings("BusyWait")
    public void run() {
        int selectedOption;

        while (true) {

            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                System.out.println("Menu thread interrupted: " + e.getMessage());
            }

            if (isOnDisplay) {
                if (SystemState.getState() == SystemStateEnum.MENU) {
                    ClearConsole.clearConsole();
                    printMenu();
                    
                    System.out.print("Select an option: > ");
                    try {
                        selectedOption = Integer.parseInt(SharedScanner.getScannerInput());
                        switch (selectedOption) {
                            case 1 -> System.out.println("Road added");
                            case 2 -> System.out.println("Road deleted");
                            case 3 -> {
                                SystemState.setState(SystemStateEnum.SYSTEM);
                                isOnDisplay = false;
                                SystemThread.setOnDisplay(true);
                            }
                            case 0 -> {
                                System.out.print("Bye!");
                                System.exit(0);
                            }
                            default -> System.out.println("Invalid option. Please try again.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please try again.");
                    }

                    if (isOnDisplay) {
                        System.out.print("Press Enter to continue...");
                        SharedScanner.getScannerInput(); 
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
