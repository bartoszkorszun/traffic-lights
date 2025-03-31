package com.example;

import java.util.Scanner;

public class Menu extends Thread {
    
    @Override
    public void run() {
        int selectedOption;

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                printMenu();
                System.out.print("> ");
                try {
                    selectedOption = scanner.nextInt();
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
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.next();
                }
            }
        }
    }

    public void printMenu() {
        for (MenuOptionsEnum option : MenuOptionsEnum.values()) {
            option.printOption();
        }
    }
}
